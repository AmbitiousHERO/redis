package online.shixun.pojo.vo;

import java.util.Date;

import online.shixun.pojo.Goods;

public class GoodsVo extends Goods{
	/**
	 *  秒杀商品基本信息
	 */
	private static final long serialVersionUID = -1886994340106357330L;
	private Double seckillPrice;
	private Integer stockCount;
	private Date startDate;
	private Date endDate;
	
	public Double getSeckillPrice() {
		return seckillPrice;
	}
	public void setSeckillPrice(Double seckillPrice) {
		this.seckillPrice = seckillPrice;
	}
	public Integer getStockCount() {
		return stockCount;
	}
	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
