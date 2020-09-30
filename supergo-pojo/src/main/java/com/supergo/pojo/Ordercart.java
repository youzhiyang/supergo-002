package com.supergo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel(value="com.supergo.pojo.Ordercart")
@Table(name = "order_cart")
public class Ordercart implements Serializable {
    @Id
    @ApiModelProperty(value="id")
    private Long id;

    /**
     * 库存id
     */
    @Column(name = "skuId")
    @ApiModelProperty(value="skuid库存id")
    private Integer skuid;

    /**
     * 商品id
     */
    @Column(name = "spuId")
    @ApiModelProperty(value="spuid商品id")
    private Integer spuid;

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
     * 商品价格
     */
    @ApiModelProperty(value="price商品价格")
    private BigDecimal price;

    /**
     * 状态(1、已下单  2、未下单)
     */
    @ApiModelProperty(value="state状态(1、已下单  2、未下单)")
    private String state;

    private static final long serialVersionUID = 1L;

    public Ordercart(Long id, Integer skuid, Integer spuid, Integer userId, Integer sellerId, String goodsName, Integer num, BigDecimal price, String state) {
        this.id = id;
        this.skuid = skuid;
        this.spuid = spuid;
        this.userId = userId;
        this.sellerId = sellerId;
        this.goodsName = goodsName;
        this.num = num;
        this.price = price;
        this.state = state;
    }

    public Ordercart() {
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
     * 获取商品价格
     *
     * @return price - 商品价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置商品价格
     *
     * @param price 商品价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取状态(1、已下单  2、未下单)
     *
     * @return state - 状态(1、已下单  2、未下单)
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态(1、已下单  2、未下单)
     *
     * @param state 状态(1、已下单  2、未下单)
     */
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", skuid=").append(skuid);
        sb.append(", spuid=").append(spuid);
        sb.append(", userId=").append(userId);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", num=").append(num);
        sb.append(", price=").append(price);
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
        Ordercart other = (Ordercart) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSkuid() == null ? other.getSkuid() == null : this.getSkuid().equals(other.getSkuid()))
            && (this.getSpuid() == null ? other.getSpuid() == null : this.getSpuid().equals(other.getSpuid()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getSellerId() == null ? other.getSellerId() == null : this.getSellerId().equals(other.getSellerId()))
            && (this.getGoodsName() == null ? other.getGoodsName() == null : this.getGoodsName().equals(other.getGoodsName()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSkuid() == null) ? 0 : getSkuid().hashCode());
        result = prime * result + ((getSpuid() == null) ? 0 : getSpuid().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getSellerId() == null) ? 0 : getSellerId().hashCode());
        result = prime * result + ((getGoodsName() == null) ? 0 : getGoodsName().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        return result;
    }
}