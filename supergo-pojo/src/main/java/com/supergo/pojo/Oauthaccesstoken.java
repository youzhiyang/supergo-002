package com.supergo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Arrays;

@ApiModel(value="com.supergo.pojo.Oauthaccesstoken")
@Table(name = "oauth_access_token")
public class Oauthaccesstoken implements Serializable {
    @Id
    @Column(name = "authentication_id")
    @ApiModelProperty(value="authenticationId")
    private String authenticationId;

    @Column(name = "token_id")
    @ApiModelProperty(value="tokenId")
    private String tokenId;

    @Column(name = "user_name")
    @ApiModelProperty(value="userName")
    private String userName;

    @Column(name = "client_id")
    @ApiModelProperty(value="clientId")
    private String clientId;

    @Column(name = "refresh_token")
    @ApiModelProperty(value="refreshToken")
    private String refreshToken;

    @ApiModelProperty(value="token")
    private byte[] token;

    @ApiModelProperty(value="authentication")
    private byte[] authentication;

    private static final long serialVersionUID = 1L;

    public Oauthaccesstoken(String authenticationId, String tokenId, String userName, String clientId, String refreshToken) {
        this.authenticationId = authenticationId;
        this.tokenId = tokenId;
        this.userName = userName;
        this.clientId = clientId;
        this.refreshToken = refreshToken;
    }

    public Oauthaccesstoken(String authenticationId, String tokenId, String userName, String clientId, String refreshToken, byte[] token, byte[] authentication) {
        this.authenticationId = authenticationId;
        this.tokenId = tokenId;
        this.userName = userName;
        this.clientId = clientId;
        this.refreshToken = refreshToken;
        this.token = token;
        this.authentication = authentication;
    }

    public Oauthaccesstoken() {
        super();
    }

    /**
     * @return authentication_id
     */
    public String getAuthenticationId() {
        return authenticationId;
    }

    /**
     * @param authenticationId
     */
    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
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
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return client_id
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * @return refresh_token
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * @param refreshToken
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
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
        sb.append(", authenticationId=").append(authenticationId);
        sb.append(", tokenId=").append(tokenId);
        sb.append(", userName=").append(userName);
        sb.append(", clientId=").append(clientId);
        sb.append(", refreshToken=").append(refreshToken);
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
        Oauthaccesstoken other = (Oauthaccesstoken) that;
        return (this.getAuthenticationId() == null ? other.getAuthenticationId() == null : this.getAuthenticationId().equals(other.getAuthenticationId()))
            && (this.getTokenId() == null ? other.getTokenId() == null : this.getTokenId().equals(other.getTokenId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getClientId() == null ? other.getClientId() == null : this.getClientId().equals(other.getClientId()))
            && (this.getRefreshToken() == null ? other.getRefreshToken() == null : this.getRefreshToken().equals(other.getRefreshToken()))
            && (Arrays.equals(this.getToken(), other.getToken()))
            && (Arrays.equals(this.getAuthentication(), other.getAuthentication()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAuthenticationId() == null) ? 0 : getAuthenticationId().hashCode());
        result = prime * result + ((getTokenId() == null) ? 0 : getTokenId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getClientId() == null) ? 0 : getClientId().hashCode());
        result = prime * result + ((getRefreshToken() == null) ? 0 : getRefreshToken().hashCode());
        result = prime * result + (Arrays.hashCode(getToken()));
        result = prime * result + (Arrays.hashCode(getAuthentication()));
        return result;
    }
}