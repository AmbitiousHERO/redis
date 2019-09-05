package online.shixun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import online.shixun.pojo.OrderInfo;
import online.shixun.pojo.SeckillUser;
import online.shixun.pojo.vo.GoodsVo;


@Service
public class SeckillService {
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;

	@Transactional
	public OrderInfo seckill(long userId, GoodsVo goods) {
		//减库存 下订单 写入秒杀订单
		goodsService.reduceStock(goods);
		//order_info maiosha_order
		return orderService.createOrder(userId, goods);
	}
	
}
