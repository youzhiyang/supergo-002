package com.supergo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value="com.supergo.pojo.Orderinvoice")
@Table(name = "order_invoice")
public class Orderinvoice implements Serializable {
    @Id
    @ApiModelProperty(value="id")
    private Long id;

    @Column(name = "invoice_type")
    @ApiModelProperty(value="invoiceType")
    private String invoiceType;

    /**
     * 发票抬头
     */
    @Column(name = "invoice_up")
    @ApiModelProperty(value="invoiceUp发票抬头")
    private String invoiceUp;

    /**
     * 发票内容
     */
    @Column(name = "invoice_content")
    @ApiModelProperty(value="invoiceContent发票内容")
    private String invoiceContent;

    /**
     * 发票代码
     */
    @Column(name = "invoice_code")
    @ApiModelProperty(value="invoiceCode发票代码")
    private Integer invoiceCode;

    /**
     * 发票号码
     */
    @Column(name = "invoice_number")
    @ApiModelProperty(value="invoiceNumber发票号码")
    private Integer invoiceNumber;

    /**
     * 开票日期
     */
    @Column(name = "invoice_time")
    @ApiModelProperty(value="invoiceTime开票日期")
    private Date invoiceTime;

    /**
     * 校验码
     */
    @ApiModelProperty(value="captcha校验码")
    private String captcha;

    /**
     * 密码区
     */
    @Column(name = "password_area")
    @ApiModelProperty(value="passwordArea密码区")
    private String passwordArea;

    /**
     * 服务名称
     */
    @Column(name = "service_name")
    @ApiModelProperty(value="serviceName服务名称")
    private String serviceName;

    /**
     * 规格型号
     */
    @Column(name = "spec_large")
    @ApiModelProperty(value="specLarge规格型号")
    private String specLarge;

    /**
     * 数量
     */
    @ApiModelProperty(value="number数量")
    private Integer number;

    /**
     * 单价
     */
    @ApiModelProperty(value="price单价")
    private BigDecimal price;

    /**
     * 金额
     */
    @ApiModelProperty(value="money金额")
    private BigDecimal money;

    /**
     * 合计
     */
    @ApiModelProperty(value="total合计")
    private BigDecimal total;

    @Column(name = "tax_rate")
    @ApiModelProperty(value="taxRate")
    private Float taxRate;

    /**
     * 税额
     */
    @Column(name = "rate_cap")
    @ApiModelProperty(value="rateCap税额")
    private Float rateCap;

    /**
     * 销售方名称
     */
    @Column(name = "seller_name")
    @ApiModelProperty(value="sellerName销售方名称")
    private String sellerName;

    /**
     * 销售方纳税人识别号
     */
    @ApiModelProperty(value="identifier销售方纳税人识别号")
    private Integer identifier;

    /**
     * 销售方地址电话
     */
    @ApiModelProperty(value="phone销售方地址电话")
    private String phone;

    /**
     * 销售方开户行及账号
     */
    @ApiModelProperty(value="account销售方开户行及账号")
    private Integer account;

    /**
     * 备注
     */
    @ApiModelProperty(value="remark备注")
    private String remark;

    /**
     * 收入款人
     */
    @ApiModelProperty(value="payee收入款人")
    private String payee;

    /**
     * 开票人
     */
    @ApiModelProperty(value="issuer开票人")
    private String issuer;

    private static final long serialVersionUID = 1L;

    public Orderinvoice(Long id, String invoiceType, String invoiceUp, String invoiceContent, Integer invoiceCode, Integer invoiceNumber, Date invoiceTime, String captcha, String passwordArea, String serviceName, String specLarge, Integer number, BigDecimal price, BigDecimal money, BigDecimal total, Float taxRate, Float rateCap, String sellerName, Integer identifier, String phone, Integer account, String remark, String payee, String issuer) {
        this.id = id;
        this.invoiceType = invoiceType;
        this.invoiceUp = invoiceUp;
        this.invoiceContent = invoiceContent;
        this.invoiceCode = invoiceCode;
        this.invoiceNumber = invoiceNumber;
        this.invoiceTime = invoiceTime;
        this.captcha = captcha;
        this.passwordArea = passwordArea;
        this.serviceName = serviceName;
        this.specLarge = specLarge;
        this.number = number;
        this.price = price;
        this.money = money;
        this.total = total;
        this.taxRate = taxRate;
        this.rateCap = rateCap;
        this.sellerName = sellerName;
        this.identifier = identifier;
        this.phone = phone;
        this.account = account;
        this.remark = remark;
        this.payee = payee;
        this.issuer = issuer;
    }

    public Orderinvoice() {
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
     * @return invoice_type
     */
    public String getInvoiceType() {
        return invoiceType;
    }

    /**
     * @param invoiceType
     */
    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    /**
     * 获取发票抬头
     *
     * @return invoice_up - 发票抬头
     */
    public String getInvoiceUp() {
        return invoiceUp;
    }

    /**
     * 设置发票抬头
     *
     * @param invoiceUp 发票抬头
     */
    public void setInvoiceUp(String invoiceUp) {
        this.invoiceUp = invoiceUp;
    }

    /**
     * 获取发票内容
     *
     * @return invoice_content - 发票内容
     */
    public String getInvoiceContent() {
        return invoiceContent;
    }

    /**
     * 设置发票内容
     *
     * @param invoiceContent 发票内容
     */
    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    /**
     * 获取发票代码
     *
     * @return invoice_code - 发票代码
     */
    public Integer getInvoiceCode() {
        return invoiceCode;
    }

    /**
     * 设置发票代码
     *
     * @param invoiceCode 发票代码
     */
    public void setInvoiceCode(Integer invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    /**
     * 获取发票号码
     *
     * @return invoice_number - 发票号码
     */
    public Integer getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * 设置发票号码
     *
     * @param invoiceNumber 发票号码
     */
    public void setInvoiceNumber(Integer invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    /**
     * 获取开票日期
     *
     * @return invoice_time - 开票日期
     */
    public Date getInvoiceTime() {
        return invoiceTime;
    }

    /**
     * 设置开票日期
     *
     * @param invoiceTime 开票日期
     */
    public void setInvoiceTime(Date invoiceTime) {
        this.invoiceTime = invoiceTime;
    }

    /**
     * 获取校验码
     *
     * @return captcha - 校验码
     */
    public String getCaptcha() {
        return captcha;
    }

    /**
     * 设置校验码
     *
     * @param captcha 校验码
     */
    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    /**
     * 获取密码区
     *
     * @return password_area - 密码区
     */
    public String getPasswordArea() {
        return passwordArea;
    }

    /**
     * 设置密码区
     *
     * @param passwordArea 密码区
     */
    public void setPasswordArea(String passwordArea) {
        this.passwordArea = passwordArea;
    }

    /**
     * 获取服务名称
     *
     * @return service_name - 服务名称
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * 设置服务名称
     *
     * @param serviceName 服务名称
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * 获取规格型号
     *
     * @return spec_large - 规格型号
     */
    public String getSpecLarge() {
        return specLarge;
    }

    /**
     * 设置规格型号
     *
     * @param specLarge 规格型号
     */
    public void setSpecLarge(String specLarge) {
        this.specLarge = specLarge;
    }

    /**
     * 获取数量
     *
     * @return number - 数量
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置数量
     *
     * @param number 数量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取单价
     *
     * @return price - 单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置单价
     *
     * @param price 单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取金额
     *
     * @return money - 金额
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 设置金额
     *
     * @param money 金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 获取合计
     *
     * @return total - 合计
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * 设置合计
     *
     * @param total 合计
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return tax_rate
     */
    public Float getTaxRate() {
        return taxRate;
    }

    /**
     * @param taxRate
     */
    public void setTaxRate(Float taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * 获取税额
     *
     * @return rate_cap - 税额
     */
    public Float getRateCap() {
        return rateCap;
    }

    /**
     * 设置税额
     *
     * @param rateCap 税额
     */
    public void setRateCap(Float rateCap) {
        this.rateCap = rateCap;
    }

    /**
     * 获取销售方名称
     *
     * @return seller_name - 销售方名称
     */
    public String getSellerName() {
        return sellerName;
    }

    /**
     * 设置销售方名称
     *
     * @param sellerName 销售方名称
     */
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    /**
     * 获取销售方纳税人识别号
     *
     * @return identifier - 销售方纳税人识别号
     */
    public Integer getIdentifier() {
        return identifier;
    }

    /**
     * 设置销售方纳税人识别号
     *
     * @param identifier 销售方纳税人识别号
     */
    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    /**
     * 获取销售方地址电话
     *
     * @return phone - 销售方地址电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置销售方地址电话
     *
     * @param phone 销售方地址电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取销售方开户行及账号
     *
     * @return account - 销售方开户行及账号
     */
    public Integer getAccount() {
        return account;
    }

    /**
     * 设置销售方开户行及账号
     *
     * @param account 销售方开户行及账号
     */
    public void setAccount(Integer account) {
        this.account = account;
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

    /**
     * 获取收入款人
     *
     * @return payee - 收入款人
     */
    public String getPayee() {
        return payee;
    }

    /**
     * 设置收入款人
     *
     * @param payee 收入款人
     */
    public void setPayee(String payee) {
        this.payee = payee;
    }

    /**
     * 获取开票人
     *
     * @return issuer - 开票人
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * 设置开票人
     *
     * @param issuer 开票人
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", invoiceType=").append(invoiceType);
        sb.append(", invoiceUp=").append(invoiceUp);
        sb.append(", invoiceContent=").append(invoiceContent);
        sb.append(", invoiceCode=").append(invoiceCode);
        sb.append(", invoiceNumber=").append(invoiceNumber);
        sb.append(", invoiceTime=").append(invoiceTime);
        sb.append(", captcha=").append(captcha);
        sb.append(", passwordArea=").append(passwordArea);
        sb.append(", serviceName=").append(serviceName);
        sb.append(", specLarge=").append(specLarge);
        sb.append(", number=").append(number);
        sb.append(", price=").append(price);
        sb.append(", money=").append(money);
        sb.append(", total=").append(total);
        sb.append(", taxRate=").append(taxRate);
        sb.append(", rateCap=").append(rateCap);
        sb.append(", sellerName=").append(sellerName);
        sb.append(", identifier=").append(identifier);
        sb.append(", phone=").append(phone);
        sb.append(", account=").append(account);
        sb.append(", remark=").append(remark);
        sb.append(", payee=").append(payee);
        sb.append(", issuer=").append(issuer);
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
        Orderinvoice other = (Orderinvoice) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getInvoiceType() == null ? other.getInvoiceType() == null : this.getInvoiceType().equals(other.getInvoiceType()))
            && (this.getInvoiceUp() == null ? other.getInvoiceUp() == null : this.getInvoiceUp().equals(other.getInvoiceUp()))
            && (this.getInvoiceContent() == null ? other.getInvoiceContent() == null : this.getInvoiceContent().equals(other.getInvoiceContent()))
            && (this.getInvoiceCode() == null ? other.getInvoiceCode() == null : this.getInvoiceCode().equals(other.getInvoiceCode()))
            && (this.getInvoiceNumber() == null ? other.getInvoiceNumber() == null : this.getInvoiceNumber().equals(other.getInvoiceNumber()))
            && (this.getInvoiceTime() == null ? other.getInvoiceTime() == null : this.getInvoiceTime().equals(other.getInvoiceTime()))
            && (this.getCaptcha() == null ? other.getCaptcha() == null : this.getCaptcha().equals(other.getCaptcha()))
            && (this.getPasswordArea() == null ? other.getPasswordArea() == null : this.getPasswordArea().equals(other.getPasswordArea()))
            && (this.getServiceName() == null ? other.getServiceName() == null : this.getServiceName().equals(other.getServiceName()))
            && (this.getSpecLarge() == null ? other.getSpecLarge() == null : this.getSpecLarge().equals(other.getSpecLarge()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getTotal() == null ? other.getTotal() == null : this.getTotal().equals(other.getTotal()))
            && (this.getTaxRate() == null ? other.getTaxRate() == null : this.getTaxRate().equals(other.getTaxRate()))
            && (this.getRateCap() == null ? other.getRateCap() == null : this.getRateCap().equals(other.getRateCap()))
            && (this.getSellerName() == null ? other.getSellerName() == null : this.getSellerName().equals(other.getSellerName()))
            && (this.getIdentifier() == null ? other.getIdentifier() == null : this.getIdentifier().equals(other.getIdentifier()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getPayee() == null ? other.getPayee() == null : this.getPayee().equals(other.getPayee()))
            && (this.getIssuer() == null ? other.getIssuer() == null : this.getIssuer().equals(other.getIssuer()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getInvoiceType() == null) ? 0 : getInvoiceType().hashCode());
        result = prime * result + ((getInvoiceUp() == null) ? 0 : getInvoiceUp().hashCode());
        result = prime * result + ((getInvoiceContent() == null) ? 0 : getInvoiceContent().hashCode());
        result = prime * result + ((getInvoiceCode() == null) ? 0 : getInvoiceCode().hashCode());
        result = prime * result + ((getInvoiceNumber() == null) ? 0 : getInvoiceNumber().hashCode());
        result = prime * result + ((getInvoiceTime() == null) ? 0 : getInvoiceTime().hashCode());
        result = prime * result + ((getCaptcha() == null) ? 0 : getCaptcha().hashCode());
        result = prime * result + ((getPasswordArea() == null) ? 0 : getPasswordArea().hashCode());
        result = prime * result + ((getServiceName() == null) ? 0 : getServiceName().hashCode());
        result = prime * result + ((getSpecLarge() == null) ? 0 : getSpecLarge().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getTotal() == null) ? 0 : getTotal().hashCode());
        result = prime * result + ((getTaxRate() == null) ? 0 : getTaxRate().hashCode());
        result = prime * result + ((getRateCap() == null) ? 0 : getRateCap().hashCode());
        result = prime * result + ((getSellerName() == null) ? 0 : getSellerName().hashCode());
        result = prime * result + ((getIdentifier() == null) ? 0 : getIdentifier().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getPayee() == null) ? 0 : getPayee().hashCode());
        result = prime * result + ((getIssuer() == null) ? 0 : getIssuer().hashCode());
        return result;
    }
}