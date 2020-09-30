package com.supergo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="com.supergo.pojo.Attribute")
@Table(name = "tb_attribute")
public class Attribute implements Serializable {
    /**
     * id
     */
    @Id
    @Column(name = "attribute_id")
    @ApiModelProperty(value="attributeIdid")
    private Integer attributeId;

    /**
     * 属性名
     */
    @Column(name = "attribute_name")
    @ApiModelProperty(value="attributeName属性名")
    private String attributeName;

    @Column(name = "attribute_key")
    @ApiModelProperty(value="attributeKey")
    private String attributeKey;

    @Column(name = "create_time")
    @ApiModelProperty(value="createTime")
    private Date createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value="updateTime")
    private Date updateTime;

    @Column(name = "is_delete")
    @ApiModelProperty(value="isDelete")
    private String isDelete;

    @ApiModelProperty(value="status")
    private Integer status;

    @ApiModelProperty(value="bak1")
    private String bak1;

    @ApiModelProperty(value="bak2")
    private String bak2;

    @ApiModelProperty(value="bak3")
    private String bak3;

    @Column(name = "attribute_options")
    @ApiModelProperty(value="attributeOptions")
    private String attributeOptions;

    private static final long serialVersionUID = 1L;

    public Attribute(Integer attributeId, String attributeName, String attributeKey, Date createTime, Date updateTime, String isDelete, Integer status, String bak1, String bak2, String bak3) {
        this.attributeId = attributeId;
        this.attributeName = attributeName;
        this.attributeKey = attributeKey;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isDelete = isDelete;
        this.status = status;
        this.bak1 = bak1;
        this.bak2 = bak2;
        this.bak3 = bak3;
    }

    public Attribute(Integer attributeId, String attributeName, String attributeKey, Date createTime, Date updateTime, String isDelete, Integer status, String bak1, String bak2, String bak3, String attributeOptions) {
        this.attributeId = attributeId;
        this.attributeName = attributeName;
        this.attributeKey = attributeKey;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isDelete = isDelete;
        this.status = status;
        this.bak1 = bak1;
        this.bak2 = bak2;
        this.bak3 = bak3;
        this.attributeOptions = attributeOptions;
    }

    public Attribute() {
        super();
    }

    /**
     * 获取id
     *
     * @return attribute_id - id
     */
    public Integer getAttributeId() {
        return attributeId;
    }

    /**
     * 设置id
     *
     * @param attributeId id
     */
    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    /**
     * 获取属性名
     *
     * @return attribute_name - 属性名
     */
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * 设置属性名
     *
     * @param attributeName 属性名
     */
    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    /**
     * @return attribute_key
     */
    public String getAttributeKey() {
        return attributeKey;
    }

    /**
     * @param attributeKey
     */
    public void setAttributeKey(String attributeKey) {
        this.attributeKey = attributeKey;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return is_delete
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * @param isDelete
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return bak1
     */
    public String getBak1() {
        return bak1;
    }

    /**
     * @param bak1
     */
    public void setBak1(String bak1) {
        this.bak1 = bak1;
    }

    /**
     * @return bak2
     */
    public String getBak2() {
        return bak2;
    }

    /**
     * @param bak2
     */
    public void setBak2(String bak2) {
        this.bak2 = bak2;
    }

    /**
     * @return bak3
     */
    public String getBak3() {
        return bak3;
    }

    /**
     * @param bak3
     */
    public void setBak3(String bak3) {
        this.bak3 = bak3;
    }

    /**
     * @return attribute_options
     */
    public String getAttributeOptions() {
        return attributeOptions;
    }

    /**
     * @param attributeOptions
     */
    public void setAttributeOptions(String attributeOptions) {
        this.attributeOptions = attributeOptions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", attributeId=").append(attributeId);
        sb.append(", attributeName=").append(attributeName);
        sb.append(", attributeKey=").append(attributeKey);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", status=").append(status);
        sb.append(", bak1=").append(bak1);
        sb.append(", bak2=").append(bak2);
        sb.append(", bak3=").append(bak3);
        sb.append(", attributeOptions=").append(attributeOptions);
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
        Attribute other = (Attribute) that;
        return (this.getAttributeId() == null ? other.getAttributeId() == null : this.getAttributeId().equals(other.getAttributeId()))
            && (this.getAttributeName() == null ? other.getAttributeName() == null : this.getAttributeName().equals(other.getAttributeName()))
            && (this.getAttributeKey() == null ? other.getAttributeKey() == null : this.getAttributeKey().equals(other.getAttributeKey()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getBak1() == null ? other.getBak1() == null : this.getBak1().equals(other.getBak1()))
            && (this.getBak2() == null ? other.getBak2() == null : this.getBak2().equals(other.getBak2()))
            && (this.getBak3() == null ? other.getBak3() == null : this.getBak3().equals(other.getBak3()))
            && (this.getAttributeOptions() == null ? other.getAttributeOptions() == null : this.getAttributeOptions().equals(other.getAttributeOptions()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAttributeId() == null) ? 0 : getAttributeId().hashCode());
        result = prime * result + ((getAttributeName() == null) ? 0 : getAttributeName().hashCode());
        result = prime * result + ((getAttributeKey() == null) ? 0 : getAttributeKey().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getBak1() == null) ? 0 : getBak1().hashCode());
        result = prime * result + ((getBak2() == null) ? 0 : getBak2().hashCode());
        result = prime * result + ((getBak3() == null) ? 0 : getBak3().hashCode());
        result = prime * result + ((getAttributeOptions() == null) ? 0 : getAttributeOptions().hashCode());
        return result;
    }
}