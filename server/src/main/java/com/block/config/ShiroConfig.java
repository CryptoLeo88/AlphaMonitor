package com.block.config;

import com.block.realm.MyRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //Swagger2的所有请求都不需要拦截
        filterChainDefinitionMap.put("/swagger/**", "anon");
        filterChainDefinitionMap.put("/v3/api-docs", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-ui/*", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/captcha.jpg", "anon");
        filterChainDefinitionMap.put("/csrf", "anon");

        //文件访问
        filterChainDefinitionMap.put("/file/**", "anon");

        //文件上传
        filterChainDefinitionMap.put("/files/**", "anon");

        //空投相关
        filterChainDefinitionMap.put("/airdrop/findAll", "anon");

        //登录
        filterChainDefinitionMap.put("/login", "anon");
        // 注册
        filterChainDefinitionMap.put("/user/add", "anon");
        //退出
        filterChainDefinitionMap.put("/logout", "anon");


        filterChainDefinitionMap.put("/**", "corsAuthenticationFilter");
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        //自定义过滤器
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("corsAuthenticationFilter", corsAuthenticationFilter());
        factoryBean.setFilters(filterMap);

        return factoryBean;
    }

    public CORSAuthenticationFilter corsAuthenticationFilter() {
        return new CORSAuthenticationFilter();
    }


    @Bean
    public DefaultWebSecurityManager securityManager(@Qualifier("myRealm") MyRealm myRealm, @Qualifier("sessionManager") SessionManager sessionManager) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setSessionManager(sessionManager);
        manager.setRealm(myRealm);
        return manager;
    }

    @Bean
    public SessionManager sessionManager() {
        //将我们继承后重写的shiro session 注册
        MySessionManager shiroSession = new MySessionManager();
        //如果后续考虑多tomcat部署应用，可以使用shiro-redis开源插件来做session 的控制，或者nginx 的负载均衡
        shiroSession.setSessionDAO(new EnterpriseCacheSessionDAO());
        //单位为毫秒，600000毫秒为1个小时
        shiroSession.setSessionValidationInterval(3600000 * 12);
        //3600000 milliseconds = 1 hour
        shiroSession.setGlobalSessionTimeout(3600000 * 12);
        //是否删除无效的，默认也是开启
        shiroSession.setDeleteInvalidSessions(true);
        return shiroSession;
    }

    @Bean
    public MyRealm myRealm() {
        return new MyRealm();
    }
}
