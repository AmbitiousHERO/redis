package online.shixun.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import online.shixun.pojo.SeckillUser;


@Mapper
public interface SeckillUserDao {
	
	@Select("select * from seckill_user where id = #{id}")
	public SeckillUser getById(@Param("id")long id);

	@Select("select su.* from eshop.qsx_member eq,seckill.seckill_user su WHERE su.nickname = #{userName} AND eq.userName = #{userName}")
	public SeckillUser getByName(@Param("userName")String userName);

}
