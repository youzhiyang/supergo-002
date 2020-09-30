package com.supergo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@ApiModel(value="com.supergo.pojo.Oauthapprovals")
@Table(name = "oauth_approvals")
public class Oauthapprovals implements Serializable {
    @Column(name = "userId")
    @ApiModelProperty(value="userid")
    private String userid;

    @Column(name = "clientId")
    @ApiModelProperty(value="clientid")
    private String clientid;

    @ApiModelProperty(value="scope")
    private String scope;

    @ApiModelProperty(value="status")
    private String status;

    @Column(name = "expiresAt")
    @ApiModelProperty(value="expiresat")
    private Date expiresat;

    @Column(name = "lastModifiedAt")
    @ApiModelProperty(value="lastmodifiedat")
    private Date lastmodifiedat;

    private static final long serialVersionUID = 1L;

    public Oauthapprovals(String userid, String clientid, String scope, String status, Date expiresat, Date lastmodifiedat) {
        this.userid = userid;
        this.clientid = clientid;
        this.scope = scope;
        this.status = status;
        this.expiresat = expiresat;
        this.lastmodifiedat = lastmodifiedat;
    }

    public Oauthapprovals() {
        super();
    }

    /**
     * @return userId
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return clientId
     */
    public String getClientid() {
        return clientid;
    }

    /**
     * @param clientid
     */
    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    /**
     * @return scope
     */
    public String getScope() {
        return scope;
    }

    /**
     * @param scope
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return expiresAt
     */
    public Date getExpiresat() {
        return expiresat;
    }

    /**
     * @param expiresat
     */
    public void setExpiresat(Date expiresat) {
        this.expiresat = expiresat;
    }

    /**
     * @return lastModifiedAt
     */
    public Date getLastmodifiedat() {
        return lastmodifiedat;
    }

    /**
     * @param lastmodifiedat
     */
    public void setLastmodifiedat(Date lastmodifiedat) {
        this.lastmodifiedat = lastmodifiedat;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userid=").append(userid);
        sb.append(", clientid=").append(clientid);
        sb.append(", scope=").append(scope);
        sb.append(", status=").append(status);
        sb.append(", expiresat=").append(expiresat);
        sb.append(", lastmodifiedat=").append(lastmodifiedat);
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
        Oauthapprovals other = (Oauthapprovals) that;
        return (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getClientid() == null ? other.getClientid() == null : this.getClientid().equals(other.getClientid()))
            && (this.getScope() == null ? other.getScope() == null : this.getScope().equals(other.getScope()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getExpiresat() == null ? other.getExpiresat() == null : this.getExpiresat().equals(other.getExpiresat()))
            && (this.getLastmodifiedat() == null ? other.getLastmodifiedat() == null : this.getLastmodifiedat().equals(other.getLastmodifiedat()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getClientid() == null) ? 0 : getClientid().hashCode());
        result = prime * result + ((getScope() == null) ? 0 : getScope().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getExpiresat() == null) ? 0 : getExpiresat().hashCode());
        result = prime * result + ((getLastmodifiedat() == null) ? 0 : getLastmodifiedat().hashCode());
        return result;
    }
}