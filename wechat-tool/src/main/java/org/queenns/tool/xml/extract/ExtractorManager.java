package org.queenns.tool.xml.extract;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lxj on 18-3-30
 */
public class ExtractorManager {

    private Class clazz = Extract.class;

    private Map<String, Class<? extends Extractor>> extractors = new HashMap<String, Class<? extends Extractor>>();

    private ExtractorManager() {

        Package packages = clazz.getPackage();

        System.out.println(packages);

    }

    public static void main(String[] args) {
        new ExtractorManager();
    }

}
