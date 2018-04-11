package org.queenns.tool.xml.extract;

import org.queenns.tool.resource.ClassPathResource;
import org.queenns.tool.resource.Resource;
import org.queenns.tool.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lxj on 18-3-30
 */
public class ExtractorManager {

    private Class clazz = Extract.class;

    private Map<String, Class<? extends Extractor>> extractors = new ConcurrentHashMap<>();

    private ExtractorManager() throws IOException {

        Package packages = clazz.getPackage();

        String packageDirectory = StringUtil.replace(packages.getName(), ".", "/");

        Resource resource = new ClassPathResource(packageDirectory);

        File file = resource.getFile();

        System.out.println(file);

        System.out.println(resource.exists());

    }

    public static void main(String[] args) throws IOException {

        new ExtractorManager();

    }

}
