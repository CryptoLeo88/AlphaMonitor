package com.block.config;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CORSAuthenticationFilter extends FormAuthenticationFilter {


    public CORSAuthenticationFilter() {
        super();
    }

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //Always return true if the request's method is OPTIONS
        if (request instanceof HttpServletRequest) {
            if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
                return true;
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 错误异常提示
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        setHeader(httpRequest,httpResponse);
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json");
        Map<String, Object> map = new HashMap<>();
        map.put("code", 202);
        map.put("msg", "未登录");
        httpResponse.getWriter().write(JSON.toJSONString(map));
        return false;
    }

    /**
     * 为response设置header，实现跨域
     */
    private void setHeader(HttpServletRequest request, HttpServletResponse response) {
        //跨域的header设置
        response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", request.getMethod());
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        //防止乱码，适用于传输JSON数据
        //Content-Type, Content-Length, Authorization, Accept, X-Requested-With , yourHeaderFeild
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
    }
}
