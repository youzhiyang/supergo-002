package com.supergo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@ApiModel(value="com.supergo.pojo.Brand")
@Table(name = "tb_brand")
public class Brand implements Serializable {
    @Column(name = "br_id")
    @ApiModelProperty(value="brId")
    private Integer brId;

    /**
     * 课室id
     */
    @Column(name = "br_roomid")
    @ApiModelProperty(value="brRoomid课室id")
    private Integer brRoomid;

    /**
     * 风格样式flag
     */
    @Column(name = "br_styleid")
    @ApiModelProperty(value="brStyleid风格样式flag")
    private Integer brStyleid;

    private static final long serialVersionUID = 1L;

    public Brand(Integer brId, Integer brRoomid, Integer brStyleid) {
        this.brId = brId;
        this.brRoomid = brRoomid;
        this.brStyleid = brStyleid;
    }

    public Brand() {
        super();
    }

    /**
     * @return br_id
     */
    public Integer getBrId() {
        return brId;
    }

    /**
     * @param brId
     */
    public void setBrId(Integer brId) {
        this.brId = brId;
    }

    /**
     * 获取课室id
     *
     * @return br_roomid - 课室id
     */
    public Integer getBrRoomid() {
        return brRoomid;
    }

    /**
     * 设置课室id
     *
     * @param brRoomid 课室id
     */
    public void setBrRoomid(Integer brRoomid) {
        this.brRoomid = brRoomid;
    }

    /**
     * 获取风格样式flag
     *
     * @return br_styleid - 风格样式flag
     */
    public Integer getBrStyleid() {
        return brStyleid;
    }

    /**
     * 设置风格样式flag
     *
     * @param brStyleid 风格样式flag
     */
    public void setBrStyleid(Integer brStyleid) {
        this.brStyleid = brStyleid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", brId=").append(brId);
        sb.append(", brRoomid=").append(brRoomid);
        sb.append(", brStyleid=").append(brStyleid);
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
        Brand other = (Brand) that;
        return (this.getBrId() == null ? other.getBrId() == null : this.getBrId().equals(other.getBrId()))
            && (this.getBrRoomid() == null ? other.getBrRoomid() == null : this.getBrRoomid().equals(other.getBrRoomid()))
            && (this.getBrStyleid() == null ? other.getBrStyleid() == null : this.getBrStyleid().equals(other.getBrStyleid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBrId() == null) ? 0 : getBrId().hashCode());
        result = prime * result + ((getBrRoomid() == null) ? 0 : getBrRoomid().hashCode());
        result = prime * result + ((getBrStyleid() == null) ? 0 : getBrStyleid().hashCode());
        return result;
    }
}