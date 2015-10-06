package activiti.model.converters.json.util.extractor.impl;

import activiti.model.converters.json.util.extractor.ValueExtractor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;

/**
 * Created by alex on 05.10.2015.<br/>
 * Абстрактный экстрактор значений для значений, которые могут принимать null
 */
public abstract class NullableExtractor<T> implements ValueExtractor<T> {

    @Override
    public T extractValue(JsonNode node) {
        if(node.getNodeType().equals(JsonNodeType.NULL)) {
            return defaultValue();
        }
        return extractNonNull(node);
    }

    /**
     * Распоковать не пустое значение
     */
    protected abstract T extractNonNull(JsonNode node);

    /**
     * Значение, которое должно быть возвращено, если значение - null
     */
    private T defaultValue() {
        return null;
    }
}
