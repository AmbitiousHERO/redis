package online.shixun.controller;

import online.shixun.mq.TransmitOrder;
import online.shixun.pojo.SeckillUser;
import online.shixun.service.SeckillUserService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import online.shixun.pojo.OrderInfo;
import online.shixun.pojo.SeckillOrder;
import online.shixun.pojo.vo.GoodsVo;
import online.shixun.result.CodeMsg;
import online.shixun.service.GoodsService;
import online.shixun.service.OrderService;
import online.shixun.service.SeckillService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


@Controller
@RequestMapping("/seckill")
public class SeckillController {

	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	SeckillService seckillService;

	@Autowired
	SeckillUserService seckillUserService;

	@Autowired
	AmqpTemplate amqpTemplate;

	/**
	 * 
	 * */
    @RequestMapping("/do_seckill")
    public String list(Model model,@RequestParam("userId") long userId,@RequestParam("goodsId") long goodsId) {
    	if(userId == 0) {
    		return "login";
    	}
    	//判断库存
    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    	int stock = goods.getStockCount();
    	if(stock <= 0) {
    		model.addAttribute("errmsg", CodeMsg.SECKILL_OVER.getMsg());
    		return "seckill_fail";
    	}
    	//判断是否已经秒杀到了
    	SeckillOrder order = orderService.getseckillOrderByUserIdGoodsId(userId, goodsId);
    	if(order != null) {
    		model.addAttribute("errmsg", CodeMsg.REPEATE_seckill.getMsg());
    		return "seckill_fail";
    	}
    	//减库存 下订单 写入秒杀订单
    	OrderInfo orderInfo = seckillService.seckill(userId, goods);
    	model.addAttribute("orderInfo", orderInfo);



    	model.addAttribute("goods", goods);
        return "order_detail";
    }

    //将对象转为字节码
	private byte[] getBytesFromObject(TransmitOrder transmitOrder)  {

    	if (transmitOrder==null){
    		return null;
		}

		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = null;

		try {
			oo = new ObjectOutputStream(bo);
			oo.writeObject(transmitOrder);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return bo.toByteArray();
	}

	@RequestMapping(value = "/status/{orderId}", method = RequestMethod.POST)
    public int updateSeckillOrder(Model model,@PathVariable long orderId){
		int num = orderService.updateSeckillOrder(orderId);
		OrderInfo orderInfo = orderService.getOrderInfoByOrderId(orderId);
		SeckillUser seckillUser = seckillUserService.getById(orderInfo.getUserId());
		//构造传递类
		TransmitOrder transmitOrder = new TransmitOrder(orderInfo);

		transmitOrder.setUserName(seckillUser.getNickname());
		//转为字节码
		byte[] bytes = getBytesFromObject(transmitOrder);

		amqpTemplate.convertAndSend("exchange","transmit",bytes);

		return num;
	}
}
