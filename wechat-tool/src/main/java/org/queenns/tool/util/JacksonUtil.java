package org.queenns.tool.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.io.IOException;
import java.util.Map;

/**
 * Created by lxj on 17-12-27
 */
public class JacksonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String parseObjToJson(Object obj) throws JsonProcessingException {

        SimpleFilterProvider filterProvider = new SimpleFilterProvider().setFailOnUnknownId(false);

        return mapper.writer(filterProvider).writeValueAsString(obj);

    }

    public static String parseObjToJson(Object obj, Map<String, String[]> filters) throws JsonProcessingException {

        SimpleFilterProvider filterProvider = new SimpleFilterProvider().setFailOnUnknownId(false);

        if (!CollectionUtil.isEmpty(filters)) {

            for (String filterId : filters.keySet()) {

                String[] filterFields = filters.get(filterId);

                filterProvider.addFilter(filterId, SimpleBeanPropertyFilter.serializeAllExcept(filterFields));

            }

        }

        return mapper.writer(filterProvider).writeValueAsString(obj);

    }

    public static <T> T parseJsonToObj(String json, Class<?> clazz) throws IOException {

            if (StringUtil.isEmpty(json)) return null;

            return (T) mapper.readValue(json, clazz);

    }

}
