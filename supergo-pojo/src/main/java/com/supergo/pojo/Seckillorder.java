package com.supergo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value="com.supergo.pojo.Seckillorder")
@Table(name = "tb_seckill_order")
public class Seckillorder implements Serializable {
    /**
     * 主键
     */
    @Id
    @ApiModelProperty(value="id主键")
    private Long id;

    /**
     * 秒杀商品ID
     */
    @Column(name = "seckill_id")
    @ApiModelProperty(value="seckillId秒杀商品ID")
    private Long seckillId;

    /**
     * 支付金额
     */
    @ApiModelProperty(value="money支付金额")
    private BigDecimal money;

    /**
     * 用户
     */
    @Column(name = "user_id")
    @ApiModelProperty(value="userId用户")
    private String userId;

    /**
     * 商家
     */
    @Column(name = "seller_id")
    @ApiModelProperty(value="sellerId商家")
    private String sellerId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value="createTime创建时间")
    private Date createTime;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    @ApiModelProperty(value="payTime支付时间")
    private Date payTime;

    /**
     * 状态
     */
    @ApiModelProperty(value="status状态")
    private String status;

    /**
     * 收货人地址
     */
    @Column(name = "receiver_address")
    @ApiModelProperty(value="receiverAddress收货人地址")
    private String receiverAddress;

    /**
     * 收货人电话
     */
    @Column(name = "receiver_mobile")
    @ApiModelProperty(value="receiverMobile收货人电话")
    private String receiverMobile;

    /**
     * 收货人
     */
    @ApiModelProperty(value="receiver收货人")
    private String receiver;

    /**
     * 交易流水
     */
    @Column(name = "transaction_id")
    @ApiModelProperty(value="transactionId交易流水")
    private String transactionId;

    private static final long serialVersionUID = 1L;

    public Seckillorder(Long id, Long seckillId, BigDecimal money, String userId, String sellerId, Date createTime, Date payTime, String status, String receiverAddress, String receiverMobile, String receiver, String transactionId) {
        this.id = id;
        this.seckillId = seckillId;
        this.money = money;
        this.userId = userId;
        this.sellerId = sellerId;
        this.createTime = createTime;
        this.payTime = payTime;
        this.status = status;
        this.receiverAddress = receiverAddress;
        this.receiverMobile = receiverMobile;
        this.receiver = receiver;
        this.transactionId = transactionId;
    }

    public Seckillorder() {
        super();
    }

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取秒杀商品ID
     *
     * @return seckill_id - 秒杀商品ID
     */
    public Long getSeckillId() {
        return seckillId;
    }

    /**
     * 设置秒杀商品ID
     *
     * @param seckillId 秒杀商品ID
     */
    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    /**
     * 获取支付金额
     *
     * @return money - 支付金额
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 设置支付金额
     *
     * @param money 支付金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 获取用户
     *
     * @return user_id - 用户
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户
     *
     * @param userId 用户
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取商家
     *
     * @return seller_id - 商家
     */
    public String getSellerId() {
        return sellerId;
    }

    /**
     * 设置商家
     *
     * @param sellerId 商家
     */
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
     * 获取状态
     *
     * @return status - 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取收货人地址
     *
     * @return receiver_address - 收货人地址
     */
    public String getReceiverAddress() {
        return receiverAddress;
    }

    /**
     * 设置收货人地址
     *
     * @param receiverAddress 收货人地址
     */
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    /**
     * 获取收货人电话
     *
     * @return receiver_mobile - 收货人电话
     */
    public String getReceiverMobile() {
        return receiverMobile;
    }

    /**
     * 设置收货人电话
     *
     * @param receiverMobile 收货人电话
     */
    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    /**
     * 获取收货人
     *
     * @return receiver - 收货人
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * 设置收货人
     *
     * @param receiver 收货人
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * 获取交易流水
     *
     * @return transaction_id - 交易流水
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * 设置交易流水
     *
     * @param transactionId 交易流水
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", seckillId=").append(seckillId);
        sb.append(", money=").append(money);
        sb.append(", userId=").append(userId);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", createTime=").append(createTime);
        sb.append(", payTime=").append(payTime);
        sb.append(", status=").append(status);
        sb.append(", receiverAddress=").append(receiverAddress);
        sb.append(", receiverMobile=").append(receiverMobile);
        sb.append(", receiver=").append(receiver);
        sb.append(", transactionId=").append(transactionId);
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
        Seckillorder other = (Seckillorder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSeckillId() == null ? other.getSeckillId() == null : this.getSeckillId().equals(other.getSeckillId()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getSellerId() == null ? other.getSellerId() == null : this.getSellerId().equals(other.getSellerId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getPayTime() == null ? other.getPayTime() == null : this.getPayTime().equals(other.getPayTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getReceiverAddress() == null ? other.getReceiverAddress() == null : this.getReceiverAddress().equals(other.getReceiverAddress()))
            && (this.getReceiverMobile() == null ? other.getReceiverMobile() == null : this.getReceiverMobile().equals(other.getReceiverMobile()))
            && (this.getReceiver() == null ? other.getReceiver() == null : this.getReceiver().equals(other.getReceiver()))
            && (this.getTransactionId() == null ? other.getTransactionId() == null : this.getTransactionId().equals(other.getTransactionId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSeckillId() == null) ? 0 : getSeckillId().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getSellerId() == null) ? 0 : getSellerId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getPayTime() == null) ? 0 : getPayTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getReceiverAddress() == null) ? 0 : getReceiverAddress().hashCode());
        result = prime * result + ((getReceiverMobile() == null) ? 0 : getReceiverMobile().hashCode());
        result = prime * result + ((getReceiver() == null) ? 0 : getReceiver().hashCode());
        result = prime * result + ((getTransactionId() == null) ? 0 : getTransactionId().hashCode());
        return result;
    }
}