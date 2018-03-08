package org.queenns.tool.xml;

import org.queenns.tool.resource.ClassPathResource;
import org.queenns.tool.resource.Resource;
import org.queenns.tool.util.ObjectUtil;
import org.queenns.tool.util.PropertiesUtil;
import org.queenns.tool.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by lxj on 18-3-7
 */
public class DefaultEntityResolver implements EntityResolver {

    private static final Logger logger = LoggerFactory.getLogger(DefaultEntityResolver.class);

    /**
     * Suffix for DTD files
     */
    public static final String DTD_SUFFIX = ".dtd";

    /**
     * Suffix for XSD files
     */
    public static final String XSD_SUFFIX = ".xsd";

    private Properties properties;

    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {

        logger.debug("EntityResolver resolveEntity publicId[{}] systemId[{}]", publicId, systemId);

        if (StringUtil.isEmpty(systemId)) return null;

        loadMapping();

        if (systemId.endsWith(XSD_SUFFIX)) {

            Resource resource = new ClassPathResource(properties.getProperty(systemId));

            return new ResourceEntityResolver(resource).resolveEntity(publicId, systemId);

        } else if (systemId.endsWith(DTD_SUFFIX)) {

            return null;

        } else {

            return null;

        }

    }

    private synchronized void loadMapping() throws IOException {

        if (ObjectUtil.isEmpty(this.properties))

            this.properties = PropertiesUtil.loadProperties("mapping/xsd-mapping.propreties");

    }

}
