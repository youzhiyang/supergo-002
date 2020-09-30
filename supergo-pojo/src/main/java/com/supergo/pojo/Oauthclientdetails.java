package com.supergo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@ApiModel(value="com.supergo.pojo.Oauthclientdetails")
@Table(name = "oauth_client_details")
public class Oauthclientdetails implements Serializable {
    @Id
    @Column(name = "client_id")
    @ApiModelProperty(value="clientId")
    private String clientId;

    @Column(name = "resource_ids")
    @ApiModelProperty(value="resourceIds")
    private String resourceIds;

    @Column(name = "client_secret")
    @ApiModelProperty(value="clientSecret")
    private String clientSecret;

    @ApiModelProperty(value="scope")
    private String scope;

    @Column(name = "authorized_grant_types")
    @ApiModelProperty(value="authorizedGrantTypes")
    private String authorizedGrantTypes;

    @Column(name = "web_server_redirect_uri")
    @ApiModelProperty(value="webServerRedirectUri")
    private String webServerRedirectUri;

    @ApiModelProperty(value="authorities")
    private String authorities;

    @Column(name = "access_token_validity")
    @ApiModelProperty(value="accessTokenValidity")
    private Integer accessTokenValidity;

    @Column(name = "refresh_token_validity")
    @ApiModelProperty(value="refreshTokenValidity")
    private Integer refreshTokenValidity;

    @Column(name = "additional_information")
    @ApiModelProperty(value="additionalInformation")
    private String additionalInformation;

    @ApiModelProperty(value="autoapprove")
    private String autoapprove;

    private static final long serialVersionUID = 1L;

    public Oauthclientdetails(String clientId, String resourceIds, String clientSecret, String scope, String authorizedGrantTypes, String webServerRedirectUri, String authorities, Integer accessTokenValidity, Integer refreshTokenValidity, String additionalInformation, String autoapprove) {
        this.clientId = clientId;
        this.resourceIds = resourceIds;
        this.clientSecret = clientSecret;
        this.scope = scope;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.webServerRedirectUri = webServerRedirectUri;
        this.authorities = authorities;
        this.accessTokenValidity = accessTokenValidity;
        this.refreshTokenValidity = refreshTokenValidity;
        this.additionalInformation = additionalInformation;
        this.autoapprove = autoapprove;
    }

    public Oauthclientdetails() {
        super();
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
     * @return resource_ids
     */
    public String getResourceIds() {
        return resourceIds;
    }

    /**
     * @param resourceIds
     */
    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    /**
     * @return client_secret
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * @param clientSecret
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
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
     * @return authorized_grant_types
     */
    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    /**
     * @param authorizedGrantTypes
     */
    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    /**
     * @return web_server_redirect_uri
     */
    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    /**
     * @param webServerRedirectUri
     */
    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    /**
     * @return authorities
     */
    public String getAuthorities() {
        return authorities;
    }

    /**
     * @param authorities
     */
    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    /**
     * @return access_token_validity
     */
    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    /**
     * @param accessTokenValidity
     */
    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    /**
     * @return refresh_token_validity
     */
    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    /**
     * @param refreshTokenValidity
     */
    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    /**
     * @return additional_information
     */
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    /**
     * @param additionalInformation
     */
    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    /**
     * @return autoapprove
     */
    public String getAutoapprove() {
        return autoapprove;
    }

    /**
     * @param autoapprove
     */
    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", clientId=").append(clientId);
        sb.append(", resourceIds=").append(resourceIds);
        sb.append(", clientSecret=").append(clientSecret);
        sb.append(", scope=").append(scope);
        sb.append(", authorizedGrantTypes=").append(authorizedGrantTypes);
        sb.append(", webServerRedirectUri=").append(webServerRedirectUri);
        sb.append(", authorities=").append(authorities);
        sb.append(", accessTokenValidity=").append(accessTokenValidity);
        sb.append(", refreshTokenValidity=").append(refreshTokenValidity);
        sb.append(", additionalInformation=").append(additionalInformation);
        sb.append(", autoapprove=").append(autoapprove);
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
        Oauthclientdetails other = (Oauthclientdetails) that;
        return (this.getClientId() == null ? other.getClientId() == null : this.getClientId().equals(other.getClientId()))
            && (this.getResourceIds() == null ? other.getResourceIds() == null : this.getResourceIds().equals(other.getResourceIds()))
            && (this.getClientSecret() == null ? other.getClientSecret() == null : this.getClientSecret().equals(other.getClientSecret()))
            && (this.getScope() == null ? other.getScope() == null : this.getScope().equals(other.getScope()))
            && (this.getAuthorizedGrantTypes() == null ? other.getAuthorizedGrantTypes() == null : this.getAuthorizedGrantTypes().equals(other.getAuthorizedGrantTypes()))
            && (this.getWebServerRedirectUri() == null ? other.getWebServerRedirectUri() == null : this.getWebServerRedirectUri().equals(other.getWebServerRedirectUri()))
            && (this.getAuthorities() == null ? other.getAuthorities() == null : this.getAuthorities().equals(other.getAuthorities()))
            && (this.getAccessTokenValidity() == null ? other.getAccessTokenValidity() == null : this.getAccessTokenValidity().equals(other.getAccessTokenValidity()))
            && (this.getRefreshTokenValidity() == null ? other.getRefreshTokenValidity() == null : this.getRefreshTokenValidity().equals(other.getRefreshTokenValidity()))
            && (this.getAdditionalInformation() == null ? other.getAdditionalInformation() == null : this.getAdditionalInformation().equals(other.getAdditionalInformation()))
            && (this.getAutoapprove() == null ? other.getAutoapprove() == null : this.getAutoapprove().equals(other.getAutoapprove()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getClientId() == null) ? 0 : getClientId().hashCode());
        result = prime * result + ((getResourceIds() == null) ? 0 : getResourceIds().hashCode());
        result = prime * result + ((getClientSecret() == null) ? 0 : getClientSecret().hashCode());
        result = prime * result + ((getScope() == null) ? 0 : getScope().hashCode());
        result = prime * result + ((getAuthorizedGrantTypes() == null) ? 0 : getAuthorizedGrantTypes().hashCode());
        result = prime * result + ((getWebServerRedirectUri() == null) ? 0 : getWebServerRedirectUri().hashCode());
        result = prime * result + ((getAuthorities() == null) ? 0 : getAuthorities().hashCode());
        result = prime * result + ((getAccessTokenValidity() == null) ? 0 : getAccessTokenValidity().hashCode());
        result = prime * result + ((getRefreshTokenValidity() == null) ? 0 : getRefreshTokenValidity().hashCode());
        result = prime * result + ((getAdditionalInformation() == null) ? 0 : getAdditionalInformation().hashCode());
        result = prime * result + ((getAutoapprove() == null) ? 0 : getAutoapprove().hashCode());
        return result;
    }
}