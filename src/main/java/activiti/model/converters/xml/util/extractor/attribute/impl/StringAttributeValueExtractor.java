package activiti.model.converters.xml.util.extractor.attribute.impl;

import activiti.model.converters.xml.util.extractor.attribute.AttributeValueExtractor;

/**
 * Created by alex on 06.10.2015.<br/>
 * Преобразователь для строкового типа
 */
public class StringAttributeValueExtractor implements AttributeValueExtractor<String> {
    @Override
    public String extract(String attributeValue) {
        return attributeValue;
    }
}
