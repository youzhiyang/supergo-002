package com.supergo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@ApiModel(value="com.supergo.pojo.Provinces")
@Table(name = "tb_provinces")
public class Provinces implements Serializable {
    /**
     * 唯一ID
     */
    @Id
    @ApiModelProperty(value="id唯一ID")
    private Integer id;

    /**
     * 省份ID
     */
    @ApiModelProperty(value="provinceid省份ID")
    private String provinceid;

    /**
     * 省份名称
     */
    @ApiModelProperty(value="province省份名称")
    private String province;

    /**
     * 城市列表
     */
    @ApiModelProperty(value="城市列表")
    private List<Cities> citiesList;

    private static final long serialVersionUID = 1L;

    public Provinces(Integer id, String provinceid, String province) {
        this.id = id;
        this.provinceid = provinceid;
        this.province = province;
    }

    public Provinces() {
        super();
    }

    public List<Cities> getCitiesList() {
        return citiesList;
    }

    public void setCitiesList(List<Cities> citiesList) {
        this.citiesList = citiesList;
    }

    /**
     * 获取唯一ID
     *
     * @return id - 唯一ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置唯一ID
     *
     * @param id 唯一ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取省份ID
     *
     * @return provinceid - 省份ID
     */
    public String getProvinceid() {
        return provinceid;
    }

    /**
     * 设置省份ID
     *
     * @param provinceid 省份ID
     */
    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
    }

    /**
     * 获取省份名称
     *
     * @return province - 省份名称
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省份名称
     *
     * @param province 省份名称
     */
    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", provinceid=").append(provinceid);
        sb.append(", province=").append(province);
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
        Provinces other = (Provinces) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProvinceid() == null ? other.getProvinceid() == null : this.getProvinceid().equals(other.getProvinceid()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProvinceid() == null) ? 0 : getProvinceid().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        return result;
    }
}