package com.cloud.commons.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Hayson
 * @description
 */
@Configuration
@MapperScan(basePackages = "com.cloud.commons.dao")
public class MybatisConfig {

    @Value("${spring.datasource.db1.driver-class-name}")
    private String oneDriverClassName;
    @Value("${spring.datasource.db1.initial-size}")
    private String oneInitialSize;
    @Value("${spring.datasource.db1.max-active}")
    private String oneMaxActive;
    @Value("${spring.datasource.db1.min-idle}")
    private String oneMinIdle;
    @Value("${spring.datasource.db1.max-wait}")
    private String oneMaxWait;
    @Value("${spring.datasource.db1.pool-prepared-statements}")
    private Boolean onePoolpreparedstatements;
    @Value("${spring.datasource.db1.max-pool-prepared-statement-per-connection-size}")
    private String oneMaxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.datasource.db1.time-between-eviction-runs-millis}")
    private String oneTimeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.db1.min-evictable-idle-time-millis}")
    private String oneMinEvictableIdleTimeMillis;
    @Value("${spring.datasource.db1.validation-query}")
    private String oneValidationQuery;
    @Value("${spring.datasource.db1.validation-query-timeout}")
    private String oneValidationQueryTimeout;
    @Value("${spring.datasource.db1.test-while-idle}")
    private Boolean oneTestWhileIdle;
    @Value("${spring.datasource.db1.test-on-borrow}")
    private Boolean oneTestOnBorrow;
    @Value("${spring.datasource.db1.test-on-return}")
    private Boolean oneTestOnReturn;
    @Value("${spring.datasource.db1.use-unfair-lock}")
    private Boolean oneUseUnfairLock;
    @Value("${spring.datasource.db1.remove-abandoned}")
    private Boolean oneRemoveAbandoned;
    @Value("${spring.datasource.db1.remove-abandoned-timeout}")
    private String oneRemoveAbandonedTimeout;
    @Value("${spring.datasource.db1.jdbc-url}")
    private String oneUrl;
    @Value("${spring.datasource.db1.username}")
    private String oneUsername;
    @Value("${spring.datasource.db1.password}")
    private String onePassword;

    @Value("${spring.datasource.db2.driver-class-name}")
    private String twoDriverClassName;
    @Value("${spring.datasource.db2.initial-size}")
    private String twoInitialSize;
    @Value("${spring.datasource.db2.max-active}")
    private String twoMaxActive;
    @Value("${spring.datasource.db2.min-idle}")
    private String twoMinIdle;
    @Value("${spring.datasource.db2.max-wait}")
    private String twoMaxWait;
    @Value("${spring.datasource.db2.pool-prepared-statements}")
    private Boolean twoPoolpreparedstatements;
    @Value("${spring.datasource.db2.max-pool-prepared-statement-per-connection-size}")
    private String twoMaxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.datasource.db2.time-between-eviction-runs-millis}")
    private String twoTimeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.db2.min-evictable-idle-time-millis}")
    private String twoMinEvictableIdleTimeMillis;
    @Value("${spring.datasource.db2.validation-query}")
    private String twoValidationQuery;
    @Value("${spring.datasource.db2.validation-query-timeout}")
    private String twoValidationQueryTimeout;
    @Value("${spring.datasource.db2.test-while-idle}")
    private Boolean twoTestWhileIdle;
    @Value("${spring.datasource.db2.test-on-borrow}")
    private Boolean twoTestOnBorrow;
    @Value("${spring.datasource.db2.test-on-return}")
    private Boolean twoTestOnReturn;
    @Value("${spring.datasource.db2.use-unfair-lock}")
    private Boolean twoUseUnfairLock;
    @Value("${spring.datasource.db2.remove-abandoned}")
    private Boolean twoRemoveAbandoned;
    @Value("${spring.datasource.db2.remove-abandoned-timeout}")
    private String twoRemoveAbandonedTimeout;
    @Value("${spring.datasource.db2.jdbc-url}")
    private String twoUrl;
    @Value("${spring.datasource.db2.username}")
    private String twoUsername;
    @Value("${spring.datasource.db2.password}")
    private String twoPassword;

    @Value("${spring.datasource.filters}")
    private String filters;


    @Bean("db1DataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource db1DataSource() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(oneUrl);
        datasource.setUsername(oneUsername);
        datasource.setPassword(onePassword);
        datasource.setDriverClassName(oneDriverClassName);
        //configuration
        if (oneInitialSize != null && !"".equals(oneInitialSize)) {
            datasource.setInitialSize(Integer.parseInt(oneInitialSize));
        }
        if (oneMaxActive != null && !"".equals(oneMaxActive)) {
            datasource.setMaxActive(Integer.parseInt(oneMaxActive));
        }
        if (oneMinIdle != null && !"".equals(oneMinIdle)) {
            datasource.setMinIdle(Integer.parseInt(oneMinIdle));
        }
        if (oneMaxWait != null && !"".equals(oneMaxWait)) {
            datasource.setMaxWait(Integer.parseInt(oneMaxWait));
        }
        if (oneTimeBetweenEvictionRunsMillis != null && !"".equals(oneTimeBetweenEvictionRunsMillis)) {
            datasource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(oneTimeBetweenEvictionRunsMillis));
        }
        if (oneMinEvictableIdleTimeMillis != null && !"".equals(oneMinEvictableIdleTimeMillis)) {
            datasource.setMinEvictableIdleTimeMillis(Integer.parseInt(oneMinEvictableIdleTimeMillis));
        }
        if (onePoolpreparedstatements != null) {
            datasource.setPoolPreparedStatements(onePoolpreparedstatements);
        }
        if (oneMaxPoolPreparedStatementPerConnectionSize != null && !"".equals(oneMaxPoolPreparedStatementPerConnectionSize)) {
            datasource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(oneMaxPoolPreparedStatementPerConnectionSize));
        }
        if (oneValidationQuery != null && !"".equals(oneValidationQuery)) {
            datasource.setValidationQuery(oneValidationQuery);
        }
        if (oneValidationQueryTimeout != null && !"".equals(oneValidationQueryTimeout)) {
            datasource.setValidationQueryTimeout(Integer.parseInt(oneValidationQueryTimeout));
        }
        if (oneTestWhileIdle != null) {
            datasource.setTestWhileIdle(oneTestWhileIdle);
        }
        if (oneTestOnBorrow != null) {
            datasource.setTestOnBorrow(oneTestOnBorrow);
        }
        if (oneTestOnReturn != null) {
            datasource.setTestOnReturn(oneTestOnReturn);
        }
        if (oneUseUnfairLock != null) {
            datasource.setUseUnfairLock(oneUseUnfairLock);
        }
        if (oneRemoveAbandoned != null) {
            datasource.setRemoveAbandoned(oneRemoveAbandoned);
        }
        if (oneRemoveAbandonedTimeout != null && !"".equals(oneRemoveAbandonedTimeout)) {
            datasource.setRemoveAbandonedTimeout(Integer.parseInt(oneRemoveAbandonedTimeout));
        }

        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }

    @Bean("db2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource db2DataSource() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(twoUrl);
        datasource.setUsername(twoUsername);
        datasource.setPassword(twoPassword);
        datasource.setDriverClassName(twoDriverClassName);
        //configuration
        if (twoInitialSize != null && !"".equals(twoInitialSize)) {
            datasource.setInitialSize(Integer.parseInt(twoInitialSize));
        }
        if (twoMaxActive != null && !"".equals(twoMaxActive)) {
            datasource.setMaxActive(Integer.parseInt(twoMaxActive));
        }
        if (twoMinIdle != null && !"".equals(twoMinIdle)) {
            datasource.setMinIdle(Integer.parseInt(twoMinIdle));
        }
        if (twoMaxWait != null && !"".equals(twoMaxWait)) {
            datasource.setMaxWait(Integer.parseInt(twoMaxWait));
        }
        if (twoTimeBetweenEvictionRunsMillis != null && !"".equals(twoTimeBetweenEvictionRunsMillis)) {
            datasource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(twoTimeBetweenEvictionRunsMillis));
        }
        if (twoMinEvictableIdleTimeMillis != null && !"".equals(twoMinEvictableIdleTimeMillis)) {
            datasource.setMinEvictableIdleTimeMillis(Integer.parseInt(twoMinEvictableIdleTimeMillis));
        }
        if (twoPoolpreparedstatements != null) {
            datasource.setPoolPreparedStatements(twoPoolpreparedstatements);
        }
        if (twoMaxPoolPreparedStatementPerConnectionSize != null && !"".equals(twoMaxPoolPreparedStatementPerConnectionSize)) {
            datasource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(twoMaxPoolPreparedStatementPerConnectionSize));
        }
        if (twoValidationQuery != null && !"".equals(twoValidationQuery)) {
            datasource.setValidationQuery(twoValidationQuery);
        }
        if (twoValidationQueryTimeout != null && !"".equals(twoValidationQueryTimeout)) {
            datasource.setValidationQueryTimeout(Integer.parseInt(twoValidationQueryTimeout));
        }
        if (twoTestWhileIdle != null) {
            datasource.setTestWhileIdle(twoTestWhileIdle);
        }
        if (twoTestOnBorrow != null) {
            datasource.setTestOnBorrow(twoTestOnBorrow);
        }
        if (twoTestOnReturn != null) {
            datasource.setTestOnReturn(twoTestOnReturn);
        }
        if (twoUseUnfairLock != null) {
            datasource.setUseUnfairLock(twoUseUnfairLock);
        }
        if (twoRemoveAbandoned != null) {
            datasource.setRemoveAbandoned(twoRemoveAbandoned);
        }
        if (twoRemoveAbandonedTimeout != null && !"".equals(twoRemoveAbandonedTimeout)) {
            datasource.setRemoveAbandonedTimeout(Integer.parseInt(twoRemoveAbandonedTimeout));
        }

        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }

    @Bean
    public DynamicDataSource dataSource(@Qualifier("db1DataSource") DataSource db1DataSource,
                                        @Qualifier("db2DataSource") DataSource db2DataSource) {
        Map<Object, Object> map = new HashMap<>();
        // 添加的key为String类型
        map.put(DataSourceType.db1.getDb(), db1DataSource);
        map.put(DataSourceType.db2.getDb(), db2DataSource);
        // 通过单例获取对象
        DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();
        dynamicDataSource.setTargetDataSources(map);
        dynamicDataSource.setDefaultTargetDataSource(db1DataSource);

        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dynamicDataSource);
//        factoryBean.setTypeAliasesPackage();
        // 设置mapper.xml的位置路径
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis-mappers/**/*.xml");
        factoryBean.setMapperLocations(resources);
        return factoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(DynamicDataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }

}