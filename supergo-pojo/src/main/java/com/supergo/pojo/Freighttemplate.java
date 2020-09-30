package com.supergo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="com.supergo.pojo.Freighttemplate")
@Table(name = "tb_freight_template")
public class Freighttemplate implements Serializable {
    @Id
    @ApiModelProperty(value="id")
    private Long id;

    /**
     * 商家ID
     */
    @Column(name = "seller_id")
    @ApiModelProperty(value="sellerId商家ID")
    private String sellerId;

    /**
     * 是否默认   （‘Y’是   'N'否）
     */
    @Column(name = "is_default")
    @ApiModelProperty(value="isDefault是否默认   （‘Y’是   'N'否）")
    private String isDefault;

    /**
     * 模版名称
     */
    @ApiModelProperty(value="name模版名称")
    private String name;

    /**
     * 发货时间（1:12h  2:24h  3:48h  4:72h  5:7d 6:15d ）
     */
    @Column(name = "send_time_type")
    @ApiModelProperty(value="sendTimeType发货时间（1:12h  2:24h  3:48h  4:72h  5:7d 6:15d ）")
    private String sendTimeType;

    /**
     * 统一价格
     */
    @ApiModelProperty(value="price统一价格")
    private Long price;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @ApiModelProperty(value="createTime创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Freighttemplate(Long id, String sellerId, String isDefault, String name, String sendTimeType, Long price, Date createTime) {
        this.id = id;
        this.sellerId = sellerId;
        this.isDefault = isDefault;
        this.name = name;
        this.sendTimeType = sendTimeType;
        this.price = price;
        this.createTime = createTime;
    }

    public Freighttemplate() {
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
     * 获取商家ID
     *
     * @return seller_id - 商家ID
     */
    public String getSellerId() {
        return sellerId;
    }

    /**
     * 设置商家ID
     *
     * @param sellerId 商家ID
     */
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取是否默认   （‘Y’是   'N'否）
     *
     * @return is_default - 是否默认   （‘Y’是   'N'否）
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * 设置是否默认   （‘Y’是   'N'否）
     *
     * @param isDefault 是否默认   （‘Y’是   'N'否）
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 获取模版名称
     *
     * @return name - 模版名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置模版名称
     *
     * @param name 模版名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取发货时间（1:12h  2:24h  3:48h  4:72h  5:7d 6:15d ）
     *
     * @return send_time_type - 发货时间（1:12h  2:24h  3:48h  4:72h  5:7d 6:15d ）
     */
    public String getSendTimeType() {
        return sendTimeType;
    }

    /**
     * 设置发货时间（1:12h  2:24h  3:48h  4:72h  5:7d 6:15d ）
     *
     * @param sendTimeType 发货时间（1:12h  2:24h  3:48h  4:72h  5:7d 6:15d ）
     */
    public void setSendTimeType(String sendTimeType) {
        this.sendTimeType = sendTimeType;
    }

    /**
     * 获取统一价格
     *
     * @return price - 统一价格
     */
    public Long getPrice() {
        return price;
    }

    /**
     * 设置统一价格
     *
     * @param price 统一价格
     */
    public void setPrice(Long price) {
        this.price = price;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", isDefault=").append(isDefault);
        sb.append(", name=").append(name);
        sb.append(", sendTimeType=").append(sendTimeType);
        sb.append(", price=").append(price);
        sb.append(", createTime=").append(createTime);
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
        Freighttemplate other = (Freighttemplate) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSellerId() == null ? other.getSellerId() == null : this.getSellerId().equals(other.getSellerId()))
            && (this.getIsDefault() == null ? other.getIsDefault() == null : this.getIsDefault().equals(other.getIsDefault()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getSendTimeType() == null ? other.getSendTimeType() == null : this.getSendTimeType().equals(other.getSendTimeType()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSellerId() == null) ? 0 : getSellerId().hashCode());
        result = prime * result + ((getIsDefault() == null) ? 0 : getIsDefault().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSendTimeType() == null) ? 0 : getSendTimeType().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}