package com.supergo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="com.supergo.pojo.Orderinfo")
@Table(name = "order_info")
public class Orderinfo implements Serializable {
    @Id
    @ApiModelProperty(value="id")
    private Long id;

    /**
     * 订单编号
     */
    @Column(name = "order_number")
    @ApiModelProperty(value="orderNumber订单编号")
    private Integer orderNumber;

    /**
     * 商品项数量，不是商品个数，比如手机*2，鼠标*1，这里应该是2
     */
    @Column(name = "itemCount")
    @ApiModelProperty(value="itemcount商品项数量，不是商品个数，比如手机*2，鼠标*1，这里应该是2")
    private Integer itemcount;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    @ApiModelProperty(value="userId用户id")
    private Integer userId;

    /**
     * 店铺id
     */
    @Column(name = "seller_id")
    @ApiModelProperty(value="sellerId店铺id")
    private Integer sellerId;

    /**
     * 下单时间
     */
    @Column(name = "order_time")
    @ApiModelProperty(value="orderTime下单时间")
    private Date orderTime;

    /**
     * 支付方式（可用数字表示，如1：支付宝，2：微信，3：银行卡...）
     */
    @Column(name = "pay_method")
    @ApiModelProperty(value="payMethod支付方式（可用数字表示，如1：支付宝，2：微信，3：银行卡...）")
    private Integer payMethod;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    @ApiModelProperty(value="payTime支付时间")
    private Date payTime;

    /**
     * 支付宝订单号
     */
    @Column(name = "outTradeNo")
    @ApiModelProperty(value="outtradeno支付宝订单号")
    private Integer outtradeno;

    /**
     * 配送方式（1、中通2、圆通、3、申通）
     */
    @Column(name = "distribute_method")
    @ApiModelProperty(value="distributeMethod配送方式（1、中通2、圆通、3、申通）")
    private Integer distributeMethod;

    /**
     * 期望配送日期
     */
    @Column(name = "expect_time")
    @ApiModelProperty(value="expectTime期望配送日期")
    private Date expectTime;

    /**
     * 商品总额
     */
    @Column(name = "total_price")
    @ApiModelProperty(value="totalPrice商品总额")
    private Double totalPrice;

    /**
     * 运费
     */
    @Column(name = "carriage_money")
    @ApiModelProperty(value="carriageMoney运费")
    private Double carriageMoney;

    /**
     * 实际付款
     */
    @ApiModelProperty(value="pay实际付款")
    private String pay;

    /**
     * 订单状态(1、待付款 2、待发货 3、已付款 4、已发货 5、已关闭 6、退款)
     */
    @ApiModelProperty(value="state订单状态(1、待付款 2、待发货 3、已付款 4、已发货 5、已关闭 6、退款)")
    private Integer state;

    private static final long serialVersionUID = 1L;

    public Orderinfo(Long id, Integer orderNumber, Integer itemcount, Integer userId, Integer sellerId, Date orderTime, Integer payMethod, Date payTime, Integer outtradeno, Integer distributeMethod, Date expectTime, Double totalPrice, Double carriageMoney, String pay, Integer state) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.itemcount = itemcount;
        this.userId = userId;
        this.sellerId = sellerId;
        this.orderTime = orderTime;
        this.payMethod = payMethod;
        this.payTime = payTime;
        this.outtradeno = outtradeno;
        this.distributeMethod = distributeMethod;
        this.expectTime = expectTime;
        this.totalPrice = totalPrice;
        this.carriageMoney = carriageMoney;
        this.pay = pay;
        this.state = state;
    }

    public Orderinfo() {
        super();
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取订单编号
     *
     * @return order_number - 订单编号
     */
    public Integer getOrderNumber() {
        return orderNumber;
    }

    /**
     * 设置订单编号
     *
     * @param orderNumber 订单编号
     */
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * 获取商品项数量，不是商品个数，比如手机*2，鼠标*1，这里应该是2
     *
     * @return itemCount - 商品项数量，不是商品个数，比如手机*2，鼠标*1，这里应该是2
     */
    public Integer getItemcount() {
        return itemcount;
    }

    /**
     * 设置商品项数量，不是商品个数，比如手机*2，鼠标*1，这里应该是2
     *
     * @param itemcount 商品项数量，不是商品个数，比如手机*2，鼠标*1，这里应该是2
     */
    public void setItemcount(Integer itemcount) {
        this.itemcount = itemcount;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取店铺id
     *
     * @return seller_id - 店铺id
     */
    public Integer getSellerId() {
        return sellerId;
    }

    /**
     * 设置店铺id
     *
     * @param sellerId 店铺id
     */
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取下单时间
     *
     * @return order_time - 下单时间
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * 设置下单时间
     *
     * @param orderTime 下单时间
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * 获取支付方式（可用数字表示，如1：支付宝，2：微信，3：银行卡...）
     *
     * @return pay_method - 支付方式（可用数字表示，如1：支付宝，2：微信，3：银行卡...）
     */
    public Integer getPayMethod() {
        return payMethod;
    }

    /**
     * 设置支付方式（可用数字表示，如1：支付宝，2：微信，3：银行卡...）
     *
     * @param payMethod 支付方式（可用数字表示，如1：支付宝，2：微信，3：银行卡...）
     */
    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    /**
     * 获取支付时间
     *
     * @return pay_time - 支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置支付时间
     *
     * @param payTime 支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取支付宝订单号
     *
     * @return outTradeNo - 支付宝订单号
     */
    public Integer getOuttradeno() {
        return outtradeno;
    }

    /**
     * 设置支付宝订单号
     *
     * @param outtradeno 支付宝订单号
     */
    public void setOuttradeno(Integer outtradeno) {
        this.outtradeno = outtradeno;
    }

    /**
     * 获取配送方式（1、中通2、圆通、3、申通）
     *
     * @return distribute_method - 配送方式（1、中通2、圆通、3、申通）
     */
    public Integer getDistributeMethod() {
        return distributeMethod;
    }

    /**
     * 设置配送方式（1、中通2、圆通、3、申通）
     *
     * @param distributeMethod 配送方式（1、中通2、圆通、3、申通）
     */
    public void setDistributeMethod(Integer distributeMethod) {
        this.distributeMethod = distributeMethod;
    }

    /**
     * 获取期望配送日期
     *
     * @return expect_time - 期望配送日期
     */
    public Date getExpectTime() {
        return expectTime;
    }

    /**
     * 设置期望配送日期
     *
     * @param expectTime 期望配送日期
     */
    public void setExpectTime(Date expectTime) {
        this.expectTime = expectTime;
    }

    /**
     * 获取商品总额
     *
     * @return total_price - 商品总额
     */
    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * 设置商品总额
     *
     * @param totalPrice 商品总额
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * 获取运费
     *
     * @return carriage_money - 运费
     */
    public Double getCarriageMoney() {
        return carriageMoney;
    }

    /**
     * 设置运费
     *
     * @param carriageMoney 运费
     */
    public void setCarriageMoney(Double carriageMoney) {
        this.carriageMoney = carriageMoney;
    }

    /**
     * 获取实际付款
     *
     * @return pay - 实际付款
     */
    public String getPay() {
        return pay;
    }

    /**
     * 设置实际付款
     *
     * @param pay 实际付款
     */
    public void setPay(String pay) {
        this.pay = pay;
    }

    /**
     * 获取订单状态(1、待付款 2、待发货 3、已付款 4、已发货 5、已关闭 6、退款)
     *
     * @return state - 订单状态(1、待付款 2、待发货 3、已付款 4、已发货 5、已关闭 6、退款)
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置订单状态(1、待付款 2、待发货 3、已付款 4、已发货 5、已关闭 6、退款)
     *
     * @param state 订单状态(1、待付款 2、待发货 3、已付款 4、已发货 5、已关闭 6、退款)
     */
    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", itemcount=").append(itemcount);
        sb.append(", userId=").append(userId);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", orderTime=").append(orderTime);
        sb.append(", payMethod=").append(payMethod);
        sb.append(", payTime=").append(payTime);
        sb.append(", outtradeno=").append(outtradeno);
        sb.append(", distributeMethod=").append(distributeMethod);
        sb.append(", expectTime=").append(expectTime);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", carriageMoney=").append(carriageMoney);
        sb.append(", pay=").append(pay);
        sb.append(", state=").append(state);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Orderinfo other = (Orderinfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNumber() == null ? other.getOrderNumber() == null : this.getOrderNumber().equals(other.getOrderNumber()))
            && (this.getItemcount() == null ? other.getItemcount() == null : this.getItemcount().equals(other.getItemcount()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getSellerId() == null ? other.getSellerId() == null : this.getSellerId().equals(other.getSellerId()))
            && (this.getOrderTime() == null ? other.getOrderTime() == null : this.getOrderTime().equals(other.getOrderTime()))
            && (this.getPayMethod() == null ? other.getPayMethod() == null : this.getPayMethod().equals(other.getPayMethod()))
            && (this.getPayTime() == null ? other.getPayTime() == null : this.getPayTime().equals(other.getPayTime()))
            && (this.getOuttradeno() == null ? other.getOuttradeno() == null : this.getOuttradeno().equals(other.getOuttradeno()))
            && (this.getDistributeMethod() == null ? other.getDistributeMethod() == null : this.getDistributeMethod().equals(other.getDistributeMethod()))
            && (this.getExpectTime() == null ? other.getExpectTime() == null : this.getExpectTime().equals(other.getExpectTime()))
            && (this.getTotalPrice() == null ? other.getTotalPrice() == null : this.getTotalPrice().equals(other.getTotalPrice()))
            && (this.getCarriageMoney() == null ? other.getCarriageMoney() == null : this.getCarriageMoney().equals(other.getCarriageMoney()))
            && (this.getPay() == null ? other.getPay() == null : this.getPay().equals(other.getPay()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderNumber() == null) ? 0 : getOrderNumber().hashCode());
        result = prime * result + ((getItemcount() == null) ? 0 : getItemcount().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getSellerId() == null) ? 0 : getSellerId().hashCode());
        result = prime * result + ((getOrderTime() == null) ? 0 : getOrderTime().hashCode());
        result = prime * result + ((getPayMethod() == null) ? 0 : getPayMethod().hashCode());
        result = prime * result + ((getPayTime() == null) ? 0 : getPayTime().hashCode());
        result = prime * result + ((getOuttradeno() == null) ? 0 : getOuttradeno().hashCode());
        result = prime * result + ((getDistributeMethod() == null) ? 0 : getDistributeMethod().hashCode());
        result = prime * result + ((getExpectTime() == null) ? 0 : getExpectTime().hashCode());
        result = prime * result + ((getTotalPrice() == null) ? 0 : getTotalPrice().hashCode());
        result = prime * result + ((getCarriageMoney() == null) ? 0 : getCarriageMoney().hashCode());
        result = prime * result + ((getPay() == null) ? 0 : getPay().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        return result;
    }
}