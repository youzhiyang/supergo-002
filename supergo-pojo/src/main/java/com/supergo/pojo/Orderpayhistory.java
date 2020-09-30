package com.supergo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@ApiModel(value="com.supergo.pojo.Orderpayhistory")
@Table(name = "order_pay_history")
public class Orderpayhistory implements Serializable {
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
     * （可用数字表示，如1：支付宝，2：微信，3：银行卡...）
     */
    @Column(name = "pay_method")
    @ApiModelProperty(value="payMethod（可用数字表示，如1：支付宝，2：微信，3：银行卡...）")
    private Integer payMethod;

    /**
     * 订单总额
     */
    @ApiModelProperty(value="total订单总额")
    private Float total;

    /**
     * 支付金额
     */
    @Column(name = "pay_money")
    @ApiModelProperty(value="payMoney支付金额")
    private Float payMoney;

    /**
     * （第三方支付平台参数信息，可使用json方式保存）
     */
    @Column(name = "pay_json")
    @ApiModelProperty(value="payJson（第三方支付平台参数信息，可使用json方式保存）")
    private String payJson;

    /**
     * 备注
     */
    @ApiModelProperty(value="remark备注")
    private String remark;

    private static final long serialVersionUID = 1L;

    public Orderpayhistory(Long id, Integer orderNumber, Integer payMethod, Float total, Float payMoney, String payJson, String remark) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.payMethod = payMethod;
        this.total = total;
        this.payMoney = payMoney;
        this.payJson = payJson;
        this.remark = remark;
    }

    public Orderpayhistory() {
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
     * 获取（可用数字表示，如1：支付宝，2：微信，3：银行卡...）
     *
     * @return pay_method - （可用数字表示，如1：支付宝，2：微信，3：银行卡...）
     */
    public Integer getPayMethod() {
        return payMethod;
    }

    /**
     * 设置（可用数字表示，如1：支付宝，2：微信，3：银行卡...）
     *
     * @param payMethod （可用数字表示，如1：支付宝，2：微信，3：银行卡...）
     */
    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    /**
     * 获取订单总额
     *
     * @return total - 订单总额
     */
    public Float getTotal() {
        return total;
    }

    /**
     * 设置订单总额
     *
     * @param total 订单总额
     */
    public void setTotal(Float total) {
        this.total = total;
    }

    /**
     * 获取支付金额
     *
     * @return pay_money - 支付金额
     */
    public Float getPayMoney() {
        return payMoney;
    }

    /**
     * 设置支付金额
     *
     * @param payMoney 支付金额
     */
    public void setPayMoney(Float payMoney) {
        this.payMoney = payMoney;
    }

    /**
     * 获取（第三方支付平台参数信息，可使用json方式保存）
     *
     * @return pay_json - （第三方支付平台参数信息，可使用json方式保存）
     */
    public String getPayJson() {
        return payJson;
    }

    /**
     * 设置（第三方支付平台参数信息，可使用json方式保存）
     *
     * @param payJson （第三方支付平台参数信息，可使用json方式保存）
     */
    public void setPayJson(String payJson) {
        this.payJson = payJson;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
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
        sb.append(", payMethod=").append(payMethod);
        sb.append(", total=").append(total);
        sb.append(", payMoney=").append(payMoney);
        sb.append(", payJson=").append(payJson);
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
        Orderpayhistory other = (Orderpayhistory) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNumber() == null ? other.getOrderNumber() == null : this.getOrderNumber().equals(other.getOrderNumber()))
            && (this.getPayMethod() == null ? other.getPayMethod() == null : this.getPayMethod().equals(other.getPayMethod()))
            && (this.getTotal() == null ? other.getTotal() == null : this.getTotal().equals(other.getTotal()))
            && (this.getPayMoney() == null ? other.getPayMoney() == null : this.getPayMoney().equals(other.getPayMoney()))
            && (this.getPayJson() == null ? other.getPayJson() == null : this.getPayJson().equals(other.getPayJson()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderNumber() == null) ? 0 : getOrderNumber().hashCode());
        result = prime * result + ((getPayMethod() == null) ? 0 : getPayMethod().hashCode());
        result = prime * result + ((getTotal() == null) ? 0 : getTotal().hashCode());
        result = prime * result + ((getPayMoney() == null) ? 0 : getPayMoney().hashCode());
        result = prime * result + ((getPayJson() == null) ? 0 : getPayJson().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }
}