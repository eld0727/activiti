package activiti.model.stencil.rules;

import lombok.Value;

import java.util.List;

/**
 * Created by alex on 01.10.2015.<br/>
 * Правила описывающие, что может находится внутри составных компонентов
 */
@Value
public class ContainmentRule implements StencilRule {

    /**
     * Id или роль компонента с типом {@link activiti.model.stencil.Stencil#NODE}
     */
    private String role;

    /**
     * Список ролей или компонентов который могут быть использованы внутри сосвтаного компанента
     */
    private List<String> contains;

    @Override
    public String group() {
        return "containmentRules";
    }
}
