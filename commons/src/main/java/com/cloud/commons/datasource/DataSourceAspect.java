package com.cloud.commons.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Slf4j
@Aspect
@Component
public class DataSourceAspect {
    @Before("@annotation(ds)")
    public void beforeDataSource(DataSource ds) {
        // 修改为String
        String value = ds.value().getDb();
        DataSourceContextHolder.setDataSource(value);
        log.info("当前使用的数据源为：{}", value);
    }
    @After("@annotation(ds)")
    public void afterDataSource(DataSource ds){
        DataSourceContextHolder.clearDataSource();
    }
}