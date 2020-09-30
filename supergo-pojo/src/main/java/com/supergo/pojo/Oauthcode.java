package com.supergo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Arrays;

@ApiModel(value="com.supergo.pojo.Oauthcode")
@Table(name = "oauth_code")
public class Oauthcode implements Serializable {
    @ApiModelProperty(value="code")
    private String code;

    @ApiModelProperty(value="authentication")
    private byte[] authentication;

    private static final long serialVersionUID = 1L;

    public Oauthcode(String code) {
        this.code = code;
    }

    public Oauthcode(String code, byte[] authentication) {
        this.code = code;
        this.authentication = authentication;
    }

    public Oauthcode() {
        super();
    }

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return authentication
     */
    public byte[] getAuthentication() {
        return authentication;
    }

    /**
     * @param authentication
     */
    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code=").append(code);
        sb.append(", authentication=").append(authentication);
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
        Oauthcode other = (Oauthcode) that;
        return (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (Arrays.equals(this.getAuthentication(), other.getAuthentication()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + (Arrays.hashCode(getAuthentication()));
        return result;
    }
}