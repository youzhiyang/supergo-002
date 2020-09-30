package com.supergo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel(value="com.supergo.pojo.Orderproduct")
@Table(name = "order_product")
public class Orderproduct implements Serializable {
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
     * 商品id
     */
    @Column(name = "spuId")
    @ApiModelProperty(value="spuid商品id")
    private Integer spuid;

    /**
     * 库存id
     */
    @Column(name = "skuId")
    @ApiModelProperty(value="skuid库存id")
    private Integer skuid;

    /**
     * 店铺id
     */
    @Column(name = "seller_id")
    @ApiModelProperty(value="sellerId店铺id")
    private Integer sellerId;

    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    @ApiModelProperty(value="goodsName商品名称")
    private String goodsName;

    /**
     * 商品数量
     */
    @ApiModelProperty(value="num商品数量")
    private Integer num;

    /**
     * 价格
     */
    @ApiModelProperty(value="price价格")
    private BigDecimal price;

    private static final long serialVersionUID = 1L;

    public Orderproduct(Long id, Integer orderNumber, Integer spuid, Integer skuid, Integer sellerId, String goodsName, Integer num, BigDecimal price) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.spuid = spuid;
        this.skuid = skuid;
        this.sellerId = sellerId;
        this.goodsName = goodsName;
        this.num = num;
        this.price = price;
    }

    public Orderproduct() {
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
     * 获取商品id
     *
     * @return spuId - 商品id
     */
    public Integer getSpuid() {
        return spuid;
    }

    /**
     * 设置商品id
     *
     * @param spuid 商品id
     */
    public void setSpuid(Integer spuid) {
        this.spuid = spuid;
    }

    /**
     * 获取库存id
     *
     * @return skuId - 库存id
     */
    public Integer getSkuid() {
        return skuid;
    }

    /**
     * 设置库存id
     *
     * @param skuid 库存id
     */
    public void setSkuid(Integer skuid) {
        this.skuid = skuid;
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
     * 获取商品名称
     *
     * @return goods_name - 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置商品名称
     *
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取商品数量
     *
     * @return num - 商品数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置商品数量
     *
     * @param num 商品数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取价格
     *
     * @return price - 价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", spuid=").append(spuid);
        sb.append(", skuid=").append(skuid);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", num=").append(num);
        sb.append(", price=").append(price);
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
        Orderproduct other = (Orderproduct) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNumber() == null ? other.getOrderNumber() == null : this.getOrderNumber().equals(other.getOrderNumber()))
            && (this.getSpuid() == null ? other.getSpuid() == null : this.getSpuid().equals(other.getSpuid()))
            && (this.getSkuid() == null ? other.getSkuid() == null : this.getSkuid().equals(other.getSkuid()))
            && (this.getSellerId() == null ? other.getSellerId() == null : this.getSellerId().equals(other.getSellerId()))
            && (this.getGoodsName() == null ? other.getGoodsName() == null : this.getGoodsName().equals(other.getGoodsName()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderNumber() == null) ? 0 : getOrderNumber().hashCode());
        result = prime * result + ((getSpuid() == null) ? 0 : getSpuid().hashCode());
        result = prime * result + ((getSkuid() == null) ? 0 : getSkuid().hashCode());
        result = prime * result + ((getSellerId() == null) ? 0 : getSellerId().hashCode());
        result = prime * result + ((getGoodsName() == null) ? 0 : getGoodsName().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        return result;
    }
}