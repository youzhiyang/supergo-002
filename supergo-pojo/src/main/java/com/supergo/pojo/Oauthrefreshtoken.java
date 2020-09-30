package com.supergo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Arrays;

@ApiModel(value="com.supergo.pojo.Oauthrefreshtoken")
@Table(name = "oauth_refresh_token")
public class Oauthrefreshtoken implements Serializable {
    @Column(name = "token_id")
    @ApiModelProperty(value="tokenId")
    private String tokenId;

    @ApiModelProperty(value="token")
    private byte[] token;

    @ApiModelProperty(value="authentication")
    private byte[] authentication;

    private static final long serialVersionUID = 1L;

    public Oauthrefreshtoken(String tokenId) {
        this.tokenId = tokenId;
    }

    public Oauthrefreshtoken(String tokenId, byte[] token, byte[] authentication) {
        this.tokenId = tokenId;
        this.token = token;
        this.authentication = authentication;
    }

    public Oauthrefreshtoken() {
        super();
    }

    /**
     * @return token_id
     */
    public String getTokenId() {
        return tokenId;
    }

    /**
     * @param tokenId
     */
    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    /**
     * @return token
     */
    public byte[] getToken() {
        return token;
    }

    /**
     * @param token
     */
    public void setToken(byte[] token) {
        this.token = token;
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
        sb.append(", tokenId=").append(tokenId);
        sb.append(", token=").append(token);
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
        Oauthrefreshtoken other = (Oauthrefreshtoken) that;
        return (this.getTokenId() == null ? other.getTokenId() == null : this.getTokenId().equals(other.getTokenId()))
            && (Arrays.equals(this.getToken(), other.getToken()))
            && (Arrays.equals(this.getAuthentication(), other.getAuthentication()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTokenId() == null) ? 0 : getTokenId().hashCode());
        result = prime * result + (Arrays.hashCode(getToken()));
        result = prime * result + (Arrays.hashCode(getAuthentication()));
        return result;
    }
}