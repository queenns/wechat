package org.queenns.tool.xml;

import org.queenns.tool.resource.Resource;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * Created by lxj on 18-3-8
 */
public class ResourceEntityResolver implements EntityResolver {

    private Resource resource;

    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {

        InputSource inputSource = new InputSource(resource.getInputStream());

        inputSource.setPublicId(publicId);

        inputSource.setSystemId(systemId);

        return inputSource;

    }

    public ResourceEntityResolver(Resource resource) {
        this.resource = resource;
    }

}
