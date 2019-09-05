package online.shixun.controller;

import java.util.List;

import online.shixun.pojo.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import online.shixun.pojo.vo.GoodsVo;
import online.shixun.service.GoodsService;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	GoodsService goodsService;
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	/**
	 * 
	 * */
    @RequestMapping(value="/to_list")
    public String list(Model model) {
    	List<GoodsVo> goodsList = goodsService.listGoodsVo();
    	model.addAttribute("goodsList", goodsList);
    	 return "goods_list";
    }
    
    @RequestMapping("/to_detail/{goodsId}")
    public String detail(Model model,@PathVariable("goodsId")long goodsId) {
    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    	model.addAttribute("goods", goods);
    	
    	long startAt = goods.getStartDate().getTime();
    	long endAt = goods.getEndDate().getTime();
    	long now = System.currentTimeMillis();
    	
    	int seckillStatus = 0;
    	int remainSeconds = 0;
    	if(now < startAt ) {//秒杀还没开始，倒计时
    		seckillStatus = 0;
    		remainSeconds = (int)((startAt - now )/1000);
    	}else  if(now > endAt){//秒杀已经结束
    		seckillStatus = 2;
    		remainSeconds = -1;
    	}else {//秒杀进行中
    		seckillStatus = 1;
    		remainSeconds = 0;
    	}
    	model.addAttribute("seckillStatus", seckillStatus);
    	model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }

    @RequestMapping("/{userId}")
    public String getOrderByUserId(Model model,@PathVariable("userId")long userId){
    	List<OrderInfo> orderList = null;
    	List<GoodsVo> goodsList = null;
		orderList = goodsService.getOrderByUserId(userId);
		goodsList = goodsService.listGoodsVo();
		model.addAttribute("orderList",orderList);
		model.addAttribute("goodsList",goodsList);
    	return "order_list";
	}
}
