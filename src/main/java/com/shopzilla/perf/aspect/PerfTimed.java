package com.shopzilla.perf.aspect;

/**
 * Created by IntelliJ IDEA.
 * User: spradhan
 * Date: 5/24/11
 * Time: 7:29 PM
 * To change this template use File | Settings | File Templates.
 */

import java.lang.annotation.*;

@Inherited
@Documented
@Target( { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PerfTimed {
}