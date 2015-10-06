package activiti.model.stencil.rules;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by alex on 30.09.2015.<br/>
 * Правило
 */
public interface StencilRule {

    /**
     * Группа правил, в которую оно попадет при трансформации в json
     */
    String group();
}
