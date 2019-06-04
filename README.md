# SpringBootDemo



    
Spring Boot 学习示例
=========================

![Spring Boot 2.1.3.RELEASE](https://img.shields.io/badge/Spring%20Boot-2.1.3.RELEASE-brightgreen.svg)
![Mysql 5.6](https://img.shields.io/badge/Mysql-5.6-blue.svg)
![JDK 1.8](https://img.shields.io/badge/JDK-1.8-brightgreen.svg)
![Maven](https://img.shields.io/badge/Maven-3.6.0-yellowgreen.svg)
![license](https://img.shields.io/badge/license-MPL--2.0-blue.svg)  


## 介绍   

本项目基于springboot最新版2.1.3RELEASE
收集记录学习spring的点点滴滴，通过每一个小demo，一步步进阶，逐步完善。

实际开发过程很少碰到单模块的项目，所以该项目使用多模块开发，更贴合实际开发要求。
所以检出项目时请检出整个目录，而不是只检出某个demo。

当然考虑到多模块加载依赖问题，也可以只检出某个模块的demo，只是运行前请更改子模块pom.xml:

更改前：
```
    <parent>
        <groupId>com.mrcoder</groupId>
        <artifactId>SpringBootDemo</artifactId>
        <version>1.0.0</version>
        <relativePath>../pom.xml</relativePath>
    </parent>
```

更改后：

```
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.3.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
```
    
## 子模块简写

sb    =》 spring boot

sbm   =》 spring boot mybatis

sbmp  =》 spring boot mybatis plus


## 案例

- [sb-helloword](https://github.com/MrCoderStack/SpringBootDemo/tree/master/sb-helloword)：IDEA创建spring boot项目
- [sb-thymeleaf](https://github.com/MrCoderStack/SpringBootDemo/tree/master/sb-thymeleaf)：springboot 结合thymeleaf模板使用
- [sb-jdbc](https://github.com/MrCoderStack/SpringBootDemo/tree/master/sb-jdbc)：jdbc访问mysql
- [sb-jdbc-multidb](https://github.com/MrCoderStack/SpringBootDemo/tree/master/sb-jdbc-multidb)：jdbc 多数据源案例
- [sb-jdbc-multidb-atomikos](https://github.com/MrCoderStack/SpringBootDemo/tree/master/sb-jdbc-multidb-atomikos)：jdbc+atomikos 多数据源分布式事务处理案例
- [sb-jpa](https://github.com/MrCoderStack/SpringBootDemo/tree/master/sb-jpa)：jpa方式访问mysql
- [sb-jpa-multidb](https://github.com/MrCoderStack/SpringBootDemo/tree/master/sb-jpa-multidb)：jpa 多数据源案例
- [sb-jpa-multidb-atomikos](https://github.com/MrCoderStack/SpringBootDemo/tree/master/sb-jpa-multidb-atomikos)：jpa+atomikos 多数据源分布式事务处理案例
- [sb-jsp](https://github.com/MrCoderStack/SpringBootDemo/tree/master/sb-jsp)：整合jsp
- [sb-mail](https://github.com/MrCoderStack/SpringBootDemo/tree/master/sb-mail)：整合mail
- [sb-redis-annotations](https://github.com/MrCoderStack/SpringBootDemo/tree/master/sb-redis-annotations)：注解操作redis
- [sb-redis-template](https://github.com/MrCoderStack/SpringBootDemo/tree/master/sb-redis-template)：redisTemplate操作redis
- [sb-swagger2](https://github.com/MrCoderStack/SpringBootDemo/tree/master/sb-swagger2)：swagger2使用案例
- [sbm-annotations](https://github.com/MrCoderStack/SpringBootDemo/tree/master/sbm-annotations)：注解方式访问mysql
- [sbm-common-mapper](https://github.com/MrCoderStack/SpringBootDemo/tree/master/sbm-common-mapper)：通用mapper的使用
- [sbm-excel](https://github.com/MrCoderStack/SpringBootDemo/tree/master/sbm-excel)：excel导入(反射实体);excel载入模版导出;
- [sbm-generator](https://github.com/MrCoderStack/SpringBootDemo/tree/master/sbm-generator)：mapper.xml方式操作数据，mybatis-generator-maven-plugin自动生成代码，集成pagehelper-spring-boot-starter分页控件来使用，附带非分页方式
- [sbm-multidb-annotations](https://github.com/MrCoderStack/SpringBootDemo/tree/master/sbm-multidb-annotations)：mybatis多数据源案例，使用注解方式操作mysql
- [sbm-multidb-xml](https://github.com/MrCoderStack/SpringBootDemo/tree/master/sbm-multidb-xml)：mybatis多数据源案例，使用mapper.xml方式操作mysql
- [sbmp-multidb](https://github.com/MrCoderStack/SpringBootDemo/tree/master/sbmp-multidb)：mapper.xml方式访问mysql，mybatis-plus-generator自动生成代码，dynamic-datasource-spring-boot-starter多库切换
- [sbmp-multidb-druid](https://github.com/MrCoderStack/SpringBootDemo/tree/master/sbmp-multidb-druid)：注解方式访问mysql，spring-boot-starter-aop，druid多库切换




## 如果觉得不错，请右上角 STAR 哦~



