package activiti.model.converters.xml.util.extractor.attribute.impl;

import activiti.model.converters.xml.util.extractor.attribute.AttributeValueExtractor;

/**
 * Created by alex on 06.10.2015.<br/>
 * Преобразователь для чисел с плавующей точкой
 */
public class DoubleAttributeValueExtractor implements AttributeValueExtractor<Double> {

    @Override
    public Double extract(String attributeValue) {
        return Double.parseDouble(attributeValue);
    }
}
