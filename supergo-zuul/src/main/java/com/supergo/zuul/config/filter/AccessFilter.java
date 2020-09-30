package com.supergo.zuul.config.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.supergo.sso.feign.ApiSsoFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * Zuul过滤器，实现了路由检查
 */
@Component
public class AccessFilter extends ZuulFilter {


    @Autowired
    private ApiSsoFeign apiSsoFeign;

    /**
     * 通过int值来定义过滤器的执行顺序
     */
    @Override
    public int filterOrder() {
        // PreDecoration之前运行
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    /**
     * 过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型：
     * public static final String ERROR_TYPE = "error";
     * public static final String POST_TYPE = "post";
     * public static final String PRE_TYPE = "pre";
     * public static final String ROUTE_TYPE = "route";
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 过滤器的具体逻辑
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();

        //1、获取请求url
        String url = request.getRequestURL().toString();
        System.out.println("url:   " + url);

        //2、判断cookie中是否存在token，存在获取token
        //从cookie里面取值（Zuul丢失Cookie的解决方案：https://blog.csdn.net/lindan1984/article/details/79308396）
        String accessToken = request.getParameter("accessToken");
        Cookie[] cookies = request.getCookies();
        if(null != cookies){
            for (Cookie cookie : cookies) {
                if ("accessToken".equals(cookie.getName())) {
                    accessToken = cookie.getValue();
                }
            }
        }

        //3、不存在，设置默认值
        if(accessToken == null) {
            //防止 apiSsoFeign.hasKey(accessToken) 传参为null
            accessToken = "0";
        }

        //4、对静态文件、登录请求放行，登入成功后访问请求
        //过滤规则：cookie有令牌且存在于Redis，或者访问的是登录页面、登录请求则放行
        //& apiSsoFeign.hasKey(accessToken)
        if (url.contains(".html") | url.contains(".js") | url.contains("min.map") | url.contains("min.data") |
                url.contains(".css") | url.endsWith(".jpg")  | url.endsWith(".png") |
                url.contains("sso/user/")  | url.contains("/api/page/") |
                (!StringUtils.isEmpty(accessToken) & apiSsoFeign.hasKey(accessToken))) {
            System.out.println("11111111");
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(200);
            return null;
        } else { //其他请求重定向到登入页
            System.out.println("22222222");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                //如果不通过过滤器请求，重定向到登入页
                response.sendRedirect("http://localhost:9999/api/sso/user/loginPage");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * 返回一个boolean类型来判断该过滤器是否要执行
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }
}
