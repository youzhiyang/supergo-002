package com.supergo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@ApiModel(value="com.supergo.pojo.Orderlogisticsflow")
@Table(name = "order_logistics_flow")
public class Orderlogisticsflow implements Serializable {
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
     * 物流公司标号
     */
    @Column(name = "logistics_number")
    @ApiModelProperty(value="logisticsNumber物流公司标号")
    private Integer logisticsNumber;

    /**
     * 快递单号
     */
    @Column(name = "express_number")
    @ApiModelProperty(value="expressNumber快递单号")
    private Integer expressNumber;

    /**
     * （根据第三方接口返回来的结果信息）
     */
    @ApiModelProperty(value="remark（根据第三方接口返回来的结果信息）")
    private String remark;

    private static final long serialVersionUID = 1L;

    public Orderlogisticsflow(Long id, Integer orderNumber, Integer logisticsNumber, Integer expressNumber, String remark) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.logisticsNumber = logisticsNumber;
        this.expressNumber = expressNumber;
        this.remark = remark;
    }

    public Orderlogisticsflow() {
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
     * 获取物流公司标号
     *
     * @return logistics_number - 物流公司标号
     */
    public Integer getLogisticsNumber() {
        return logisticsNumber;
    }

    /**
     * 设置物流公司标号
     *
     * @param logisticsNumber 物流公司标号
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
     * 获取（根据第三方接口返回来的结果信息）
     *
     * @return remark - （根据第三方接口返回来的结果信息）
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置（根据第三方接口返回来的结果信息）
     *
     * @param remark （根据第三方接口返回来的结果信息）
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", logisticsNumber=").append(logisticsNumber);
        sb.append(", expressNumber=").append(expressNumber);
        sb.append(", remark=").append(remark);
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
        Orderlogisticsflow other = (Orderlogisticsflow) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNumber() == null ? other.getOrderNumber() == null : this.getOrderNumber().equals(other.getOrderNumber()))
            && (this.getLogisticsNumber() == null ? other.getLogisticsNumber() == null : this.getLogisticsNumber().equals(other.getLogisticsNumber()))
            && (this.getExpressNumber() == null ? other.getExpressNumber() == null : this.getExpressNumber().equals(other.getExpressNumber()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderNumber() == null) ? 0 : getOrderNumber().hashCode());
        result = prime * result + ((getLogisticsNumber() == null) ? 0 : getLogisticsNumber().hashCode());
        result = prime * result + ((getExpressNumber() == null) ? 0 : getExpressNumber().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }
}