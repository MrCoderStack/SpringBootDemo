package com.mrcoder.sbmmultidbxmldruidatomikos.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置 Druid登陆配置（白名单等）
 */
@Configuration
public class DruidConfig {

    @Value("${spring.druid.name}")
    private String name;
    @Value("${spring.druid.pass}")
    private String pass;

    private Logger logger = LoggerFactory.getLogger(DruidConfig.class);

    /**
     * 配置 Druid控制台 白名单、黑名单、用户名、密码等
     * 访问地址  ip+port/projectContextPath/druid/index.html
     */
    @Bean
    public ServletRegistrationBean DruidStatViewServlet(){
        logger.info("servletRegistrationBean configure is starting...");
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //添加初始化参数
        servletRegistrationBean.addInitParameter("allow","*");
        servletRegistrationBean.addInitParameter("loginUsername", name);
        servletRegistrationBean.addInitParameter("loginPassword", pass);
        //是否可以重置
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }

    /**
     * 注册一个：filterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean druidStatFilter2(){
        logger.info("filterRegistrationBean configure is starting...");
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
