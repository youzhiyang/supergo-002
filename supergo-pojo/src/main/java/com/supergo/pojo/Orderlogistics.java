package com.supergo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="com.supergo.pojo.Orderlogistics")
@Table(name = "order_logistics")
public class Orderlogistics implements Serializable {
    @Id
    @ApiModelProperty(value="id")
    private Long id;

    /**
     * 订单标号
     */
    @Column(name = "order_number")
    @ApiModelProperty(value="orderNumber订单标号")
    private Integer orderNumber;

    /**
     * 物流公司id
     */
    @Column(name = "logistics_id")
    @ApiModelProperty(value="logisticsId物流公司id")
    private Integer logisticsId;

    /**
     * 物流公司编号
     */
    @Column(name = "logistics_number")
    @ApiModelProperty(value="logisticsNumber物流公司编号")
    private Integer logisticsNumber;

    /**
     * 快递单号
     */
    @Column(name = "express_number")
    @ApiModelProperty(value="expressNumber快递单号")
    private Integer expressNumber;

    /**
     * 发货时间
     */
    @Column(name = "consign_time")
    @ApiModelProperty(value="consignTime发货时间")
    private Date consignTime;

    /**
     * 收货时间
     */
    @Column(name = "receive_time")
    @ApiModelProperty(value="receiveTime收货时间")
    private Date receiveTime;

    private static final long serialVersionUID = 1L;

    public Orderlogistics(Long id, Integer orderNumber, Integer logisticsId, Integer logisticsNumber, Integer expressNumber, Date consignTime, Date receiveTime) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.logisticsId = logisticsId;
        this.logisticsNumber = logisticsNumber;
        this.expressNumber = expressNumber;
        this.consignTime = consignTime;
        this.receiveTime = receiveTime;
    }

    public Orderlogistics() {
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
     * 获取订单标号
     *
     * @return order_number - 订单标号
     */
    public Integer getOrderNumber() {
        return orderNumber;
    }

    /**
     * 设置订单标号
     *
     * @param orderNumber 订单标号
     */
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * 获取物流公司id
     *
     * @return logistics_id - 物流公司id
     */
    public Integer getLogisticsId() {
        return logisticsId;
    }

    /**
     * 设置物流公司id
     *
     * @param logisticsId 物流公司id
     */
    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    /**
     * 获取物流公司编号
     *
     * @return logistics_number - 物流公司编号
     */
    public Integer getLogisticsNumber() {
        return logisticsNumber;
    }

    /**
     * 设置物流公司编号
     *
     * @param logisticsNumber 物流公司编号
     */
    public void setLogisticsNumber(Integer logisticsNumber) {
        this.logisticsNumber = logisticsNumber;
    }

    /**
     * 获取快递单号
     *
     * @return express_number - 快递单号
     */
    public Integer getExpressNumber() {
        return expressNumber;
    }

    /**
     * 设置快递单号
     *
     * @param expressNumber 快递单号
     */
    public void setExpressNumber(Integer expressNumber) {
        this.expressNumber = expressNumber;
    }

    /**
     * 获取发货时间
     *
     * @return consign_time - 发货时间
     */
    public Date getConsignTime() {
        return consignTime;
    }

    /**
     * 设置发货时间
     *
     * @param consignTime 发货时间
     */
    public void setConsignTime(Date consignTime) {
        this.consignTime = consignTime;
    }

    /**
     * 获取收货时间
     *
     * @return receive_time - 收货时间
     */
    public Date getReceiveTime() {
        return receiveTime;
    }

    /**
     * 设置收货时间
     *
     * @param receiveTime 收货时间
     */
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", logisticsId=").append(logisticsId);
        sb.append(", logisticsNumber=").append(logisticsNumber);
        sb.append(", expressNumber=").append(expressNumber);
        sb.append(", consignTime=").append(consignTime);
        sb.append(", receiveTime=").append(receiveTime);
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
        Orderlogistics other = (Orderlogistics) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNumber() == null ? other.getOrderNumber() == null : this.getOrderNumber().equals(other.getOrderNumber()))
            && (this.getLogisticsId() == null ? other.getLogisticsId() == null : this.getLogisticsId().equals(other.getLogisticsId()))
            && (this.getLogisticsNumber() == null ? other.getLogisticsNumber() == null : this.getLogisticsNumber().equals(other.getLogisticsNumber()))
            && (this.getExpressNumber() == null ? other.getExpressNumber() == null : this.getExpressNumber().equals(other.getExpressNumber()))
            && (this.getConsignTime() == null ? other.getConsignTime() == null : this.getConsignTime().equals(other.getConsignTime()))
            && (this.getReceiveTime() == null ? other.getReceiveTime() == null : this.getReceiveTime().equals(other.getReceiveTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderNumber() == null) ? 0 : getOrderNumber().hashCode());
        result = prime * result + ((getLogisticsId() == null) ? 0 : getLogisticsId().hashCode());
        result = prime * result + ((getLogisticsNumber() == null) ? 0 : getLogisticsNumber().hashCode());
        result = prime * result + ((getExpressNumber() == null) ? 0 : getExpressNumber().hashCode());
        result = prime * result + ((getConsignTime() == null) ? 0 : getConsignTime().hashCode());
        result = prime * result + ((getReceiveTime() == null) ? 0 : getReceiveTime().hashCode());
        return result;
    }
}