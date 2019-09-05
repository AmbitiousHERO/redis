package online.shixun.mq;

import online.shixun.pojo.OrderInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Cy
 * @data 2019/9/4 - 17:18
 */
public class TransmitOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private Long goodsId;
    private Integer goodsCount;
    private Double goodsPrice;
    private Integer orderChannel;
    private Integer status;
    private Date createDate;
    private Date payDate;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public TransmitOrder() {
    }

    public TransmitOrder(OrderInfo orderInfo) {
        userId = orderInfo.getUserId();
        goodsId = orderInfo.getGoodsId();
        goodsCount = orderInfo.getGoodsCount();
        goodsPrice = orderInfo.getGoodsPrice();
        orderChannel = orderInfo.getOrderChannel();
        status = orderInfo.getStatus();
        createDate = orderInfo.getCreateDate();
        payDate = orderInfo.getPayDate();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(Integer orderChannel) {
        this.orderChannel = orderChannel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    @Override
    public String toString() {
        return "TransmitOrder{" +
                "userId=" + userId +
                ", goodsId=" + goodsId +
                ", goodsCount=" + goodsCount +
                ", goodsPrice=" + goodsPrice +
                ", orderChannel=" + orderChannel +
                ", status=" + status +
                ", createDate=" + createDate +
                ", payDate=" + payDate +
                '}';
    }
}
