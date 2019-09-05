package online.shixun.service;

import java.util.List;

import online.shixun.pojo.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import online.shixun.dao.GoodsDao;
import online.shixun.pojo.SeckillGoods;
import online.shixun.pojo.vo.GoodsVo;


@Service
public class GoodsService {
	
	@Autowired
	GoodsDao goodsDao;
	
	public List<GoodsVo> listGoodsVo(){
		return goodsDao.listGoodsVo();
	}
    /**
     *  @Cacheable 应用到读取数据的方法上，先从缓存中读取，如果没有再从DB获取数据，然后把数据添加到缓存中
     *            key 缓存在redis中的key
     *            unless 表示条件表达式成立的话不放入缓存
     * @param id 主键id
     * @return
     */
    @Cacheable(value = "goodsVo", key = "#root.args[0]", unless = "#result eq null ")
	public GoodsVo getGoodsVoByGoodsId(long goodsId) {
		return goodsDao.getGoodsVoByGoodsId(goodsId);
	}

	public void reduceStock(GoodsVo goods) {
		SeckillGoods g = new SeckillGoods();
		g.setGoodsId(goods.getId());
		goodsDao.reduceStock(g);
	}
	
	public List<OrderInfo> getOrderByUserId(long userId){
    	return goodsDao.getOrderByUserId(userId);
	}
	
}
