package com.supergo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel(value="com.supergo.mapper.Ordercart")
@Table(name = "order_cart")
public class Ordercart implements Serializable {
    /**
     * 主键id
     */
    @Id
    @ApiModelProperty(value="id主键id")
    private Long id;

    /**
     * 库存id
     */
    @Column(name = "item_id")
    @ApiModelProperty(value="itemId库存id")
    private Long itemId;

    /**
     * 商品id
     */
    @Column(name = "goods_id")
    @ApiModelProperty(value="goodsId商品id")
    private Long goodsId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    @ApiModelProperty(value="userId用户id")
    private Long userId;

    /**
     * 店铺id
     */
    @Column(name = "seller_id")
    @ApiModelProperty(value="sellerId店铺id")
    private Long sellerId;

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
     * 商品状态(0:未下单，1:已下单)
     */
    @ApiModelProperty(value="status商品状态(0:未下单，1:已下单)")
    private Integer status;

    private static final long serialVersionUID = 1L;

    public Ordercart(Long id, Long itemId, Long goodsId, Long userId, Long sellerId, String goodsName, Integer num, BigDecimal price, Integer status) {
        this.id = id;
        this.itemId = itemId;
        this.goodsId = goodsId;
        this.userId = userId;
        this.sellerId = sellerId;
        this.goodsName = goodsName;
        this.num = num;
        this.price = price;
        this.status = status;
    }

    public Ordercart() {
        super();
    }

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取库存id
     *
     * @return item_id - 库存id
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * 设置库存id
     *
     * @param itemId 库存id
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * 获取商品id
     *
     * @return goods_id - 商品id
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品id
     *
     * @param goodsId 商品id
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取店铺id
     *
     * @return seller_id - 店铺id
     */
    public Long getSellerId() {
        return sellerId;
    }

    /**
     * 设置店铺id
     *
     * @param sellerId 店铺id
     */
    public void setSellerId(Long sellerId) {
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
     * 获取商品状态(0:未下单，1:已下单)
     *
     * @return status - 商品状态(0:未下单，1:已下单)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置商品状态(0:未下单，1:已下单)
     *
     * @param status 商品状态(0:未下单，1:已下单)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", itemId=").append(itemId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", userId=").append(userId);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", num=").append(num);
        sb.append(", price=").append(price);
        sb.append(", status=").append(status);
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
            && (this.getItemId() == null ? other.getItemId() == null : this.getItemId().equals(other.getItemId()))
            && (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getSellerId() == null ? other.getSellerId() == null : this.getSellerId().equals(other.getSellerId()))
            && (this.getGoodsName() == null ? other.getGoodsName() == null : this.getGoodsName().equals(other.getGoodsName()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getItemId() == null) ? 0 : getItemId().hashCode());
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getSellerId() == null) ? 0 : getSellerId().hashCode());
        result = prime * result + ((getGoodsName() == null) ? 0 : getGoodsName().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}