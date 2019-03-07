package com.mrcoder.sbmpmultidbdruid.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return  DbContextHolder.getDbType();
    }
}