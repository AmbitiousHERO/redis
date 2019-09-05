package online.shixun.dao;

import java.util.List;

import online.shixun.pojo.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import online.shixun.pojo.SeckillGoods;
import online.shixun.pojo.vo.GoodsVo;


@Mapper
public interface GoodsDao {
	
	@Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.seckill_price from seckill_goods mg left join goods g on mg.goods_id = g.id")
	public List<GoodsVo> listGoodsVo();

	@Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.seckill_price from seckill_goods mg left join goods g on mg.goods_id = g.id where g.id = #{goodsId}")
	public GoodsVo getGoodsVoByGoodsId(@Param("goodsId")long goodsId);

	@Update("update seckill_goods set stock_count = stock_count - 1 where goods_id = #{goodsId}")
	public int reduceStock(SeckillGoods g);

	@Select("SELECT * FROM order_info WHERE user_id = #{userId}")
	public List<OrderInfo> getOrderByUserId(@Param("userId")long userId);
}
