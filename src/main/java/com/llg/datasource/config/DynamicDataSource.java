/**
 * Copyright (c) 2018  All rights reserved.
 *
 *
 *
 * 版权所有，侵权必究！
 */

package com.llg.datasource.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 多数据源
 *
 * @author llg
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicContextHolder.peek();
    }

}
