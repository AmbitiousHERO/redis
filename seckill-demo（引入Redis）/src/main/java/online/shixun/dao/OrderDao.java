package online.shixun.dao;

import org.apache.ibatis.annotations.*;

import online.shixun.pojo.OrderInfo;
import online.shixun.pojo.SeckillOrder;


@Mapper
public interface OrderDao {
	
	@Select("select * from seckill_order where user_id=#{userId} and goods_id=#{goodsId}")
	public SeckillOrder getSeckillOrderByUserIdGoodsId(@Param("userId")long userId, @Param("goodsId")long goodsId);

	@Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
			+ "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
	@SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
	public long insert(OrderInfo orderInfo);
	
	@Insert("insert into seckill_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
	public int insertSeckillOrder(SeckillOrder seckillOrder);

	@Update("UPDATE  order_info SET status = 1, pay_date=NOW() WHERE id = #{orderId}")
	public int updateSeckillOrder(@Param("orderId")long orderId);

	@Select("SELECT * FROM order_info WHERE id = #{orderId}")
	public OrderInfo getOrderInfoByOrderId(@Param("orderId")long orderId);
}
