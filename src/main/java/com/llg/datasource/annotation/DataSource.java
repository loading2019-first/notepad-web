/**
 * Copyright (c) 2018  All rights reserved.
 *
 *
 *
 * 版权所有，侵权必究！
 */

package com.llg.datasource.annotation;

import java.lang.annotation.*;

/**
 * 多数据源注解
 *
 * @author llg
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSource {
    String value() default "";
}
