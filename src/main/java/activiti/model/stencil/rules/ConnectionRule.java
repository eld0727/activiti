package activiti.model.stencil.rules;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;

/**
 * Created by alex on 30.09.2015.<br/>
 * Правило описывающее возможные связи между компонентами
 */
@Value
@Builder
public class ConnectionRule implements StencilRule {

    /**
     * Id компонента у которого тип {@link activiti.model.stencil.Stencil#EDGE}
     */
    private String role;

    /**
     * Описание связей
     */
    @Singular("connect") private List<Connect> connects;

    @Override
    public String group() {
        return "connectionRules";
    }


}
