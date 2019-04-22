package com.mrcoder.sbmmultidbxmldruidatomikos.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * SlaveSqlSessionTemplateConfig配置类
 */
@Configuration
@MapperScan(basePackages = {"com.mrcoder.sbmmultidbxmldruidatomikos.mapper.slave"}, sqlSessionTemplateRef = "slaveSqlSessionTemplate")
public class SlaveSqlSessionTemplateConfig {

    @Value("${mybatis.mapper-locations}")
    private String mapper_location;

    @Bean
    public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slaveDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //如果重写了 SqlSessionFactory 需要在初始化的时候手动将 mapper 地址 set到 factory 中，否则会报错：
        //org.apache.ibatis.binding.BindingException: Invalid bound statement (not found)
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapper_location));
        return bean.getObject();
    }

    @Bean
    public SqlSessionTemplate slaveSqlSessionTemplate(@Qualifier("slaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
        return template;
    }
}
