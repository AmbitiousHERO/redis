package online.shixun.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import online.shixun.dao.OrderDao;
import online.shixun.pojo.OrderInfo;
import online.shixun.pojo.SeckillOrder;
import online.shixun.pojo.SeckillUser;
import online.shixun.pojo.vo.GoodsVo;


@Service
public class OrderService {
	
	@Autowired
	OrderDao orderDao;
	
	public SeckillOrder getseckillOrderByUserIdGoodsId(long userId, long goodsId) {
		return orderDao.getSeckillOrderByUserIdGoodsId(userId, goodsId);
	}

	@Transactional
	public OrderInfo createOrder(long userId, GoodsVo goods) {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setCreateDate(new Date());
		orderInfo.setDeliveryAddrId(0L);
		orderInfo.setGoodsCount(1);
		orderInfo.setGoodsId(goods.getId());
		orderInfo.setGoodsName(goods.getGoodsName());
		orderInfo.setGoodsPrice(goods.getSeckillPrice());
		orderInfo.setOrderChannel(1);
		orderInfo.setStatus(0);
		orderInfo.setUserId(userId);
		long orderId = orderDao.insert(orderInfo);
		SeckillOrder seckillOrder = new SeckillOrder();
		seckillOrder.setGoodsId(goods.getId());
		seckillOrder.setOrderId(orderId);
		seckillOrder.setUserId(userId);
		orderDao.insertSeckillOrder(seckillOrder);
		return orderInfo;
	}

	public int updateSeckillOrder(long orderId){
		return orderDao.updateSeckillOrder(orderId);
	}

	public OrderInfo getOrderInfoByOrderId(long orderId){
		return orderDao.getOrderInfoByOrderId(orderId);
	}
}
