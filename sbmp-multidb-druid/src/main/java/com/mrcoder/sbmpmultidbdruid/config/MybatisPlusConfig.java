package com.mrcoder.sbmpmultidbdruid.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@EnableTransactionManagement
@Configuration
@MapperScan("com.mrcoder.webapi.mapper")
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        //paginationInterceptor.setLocalPage(true);
        return paginationInterceptor;
    }

    @Bean(name = "db1")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db1")
    public DataSource db1() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "db2")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db2")
    public DataSource db2() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 动态数据源配置
     *
     * @return
     */
    @Bean
    @Primary
    public DataSource multipleDataSource(@Qualifier("db1") DataSource db1,
                                         @Qualifier("db2") DataSource db2) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBTypeEnum.db1.getValue(), db1);
        targetDataSources.put(DBTypeEnum.db2.getValue(), db2);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(db1);
        return dynamicDataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(multipleDataSource(db1(), db2()));

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        //PerformanceInterceptor(),OptimisticLockerInterceptor()
        //添加分页功能
        sqlSessionFactory.setPlugins(new Interceptor[]{
                paginationInterceptor()
        });
//        sqlSessionFactory.setGlobalConfig(globalConfiguration());
        return sqlSessionFactory.getObject();
    }

 /*   @Bean
    public GlobalConfiguration globalConfiguration() {
        GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
        conf.setLogicDeleteValue("-1");
        conf.setLogicNotDeleteValue("1");
        conf.setIdType(0);
        conf.setMetaObjectHandler(new MyMetaObjectHandler());
        conf.setDbColumnUnderline(true);
        conf.setRefresh(true);
        return conf;
    }*/
}