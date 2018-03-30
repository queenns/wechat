package org.queenns.tool.xml.extract;

import java.lang.annotation.*;

/**
 * Created by lxj on 18-3-30
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BaseExtractor {

    String element();

}
