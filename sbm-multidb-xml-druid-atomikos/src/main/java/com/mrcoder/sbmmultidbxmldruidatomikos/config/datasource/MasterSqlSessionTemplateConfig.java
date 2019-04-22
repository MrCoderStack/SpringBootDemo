package com.mrcoder.sbmmultidbxmldruidatomikos.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * MasterSqlSessionTemplateConfig配置类
 */
@Configuration
//下面的sqlSessionTemplateRef 值需要和生成的SqlSessionTemplate bean name相同，如果没有指定name,那么就是方法名
@MapperScan(basePackages = {"com.mrcoder.sbmmultidbxmldruidatomikos.mapper.master"}, sqlSessionTemplateRef = "masterSqlSessionTemplate")
public class MasterSqlSessionTemplateConfig {

    @Value("${mybatis.mapper-locations}")
    private String mapper_location;

    @Value("${mybatis.type-aliases-package}")
    private String type_aliases_package;

    @Value("${mybatis.configuration.map-underscore-to-camel-case}")
    private boolean mapUnderscoreToCamelCase;

    //将MybatisConfig类中初始化的对象注入进来
    @Autowired
    private ConfigurationCustomizer customizer;

    private Logger logger = LoggerFactory.getLogger(MasterSqlSessionTemplateConfig.class);

    /**
     * 自定义sqlSessionFactory配置（因为没有用到MybatisAutoConfiguration自动配置类，需要手动配置）
     */
    @Bean
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        logger.info("mapper文件地址为：{}", mapper_location);
        //在基本的 MyBatis 中,session 工厂可以使用 SqlSessionFactoryBuilder 来创建。
        //而在 MyBatis-spring 中,则使用SqlSessionFactoryBean 来替代：
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //如果重写了 SqlSessionFactory 需要在初始化的时候手动将 mapper 地址 set到 factory 中，否则会报错：
        //org.apache.ibatis.binding.BindingException: Invalid bound statement (not found)
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapper_location));
        //下面这个setTypeAliasesPackage无效，是mybatis集成springBoot的一个bug，暂时未能解决
        bean.setTypeAliasesPackage(type_aliases_package);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        logger.info("mybatis配置驼峰转换为：{}", mapUnderscoreToCamelCase);
        configuration.setMapUnderscoreToCamelCase(mapUnderscoreToCamelCase);
        //因为没有用mybatis-springBoot自动装配，所以需要手动将configuration装配进去，要不然自定义的map key驼峰转换不起作用
        customizer.customize(configuration);
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    /**
     * SqlSessionTemplate 是 SqlSession接口的实现类，是spring-mybatis中的，实现了SqlSession线程安全
     */
    @Bean
    public SqlSessionTemplate masterSqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
        return template;
    }
}
