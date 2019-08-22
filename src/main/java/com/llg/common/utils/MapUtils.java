/**
 * Copyright (c) 2016-2019  All rights reserved.
 *
 *
 *
 * 版权所有，侵权必究！
 */

package com.llg.common.utils;

import java.util.HashMap;


/**
 * Map工具类
 *
 * @author llg
 */
public class MapUtils extends HashMap<String, Object> {

    @Override
    public MapUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
