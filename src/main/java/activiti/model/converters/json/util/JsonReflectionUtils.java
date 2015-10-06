package activiti.model.converters.json.util;

import activiti.model.converters.json.util.extractor.ValueExtractor;
import activiti.model.converters.json.util.extractor.impl.DoubleExtractor;
import activiti.model.converters.json.util.extractor.impl.LongExtractor;
import activiti.model.converters.json.util.extractor.impl.StringExtractor;

import java.lang.reflect.Field;

/**
 * Created by alex on 05.10.2015.<br/>
 * TODO comment me
 */
public class JsonReflectionUtils {

    public static ValueExtractor getExtractorForField(Field field) {
        Class<?> type = field.getType();
        return getExtractorForType(type);
    }

    private static ValueExtractor getExtractorForType(Class<?> type) {
        if(type.equals(String.class)) {
            return new StringExtractor();
        } else if(isIntegral(type)) {
            return new LongExtractor();
        } else if(isReal(type)) {
            return new DoubleExtractor();
        }
        throw new IllegalArgumentException("Unsupported type: " + type);
    }

    private static boolean isReal(Class<?> type) {
        return type.equals(float.class) || type.equals(double.class) ||
                type.equals(Float.class) || type.equals(Double.class);
    }

    private static boolean isIntegral(Class<?> type) {
        return type.equals(int.class) || type.equals(long.class) || type.equals(Integer.class) || type.equals(Long.class);
    }
}
