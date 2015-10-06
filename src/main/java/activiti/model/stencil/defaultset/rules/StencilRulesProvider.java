package activiti.model.stencil.defaultset.rules;

import activiti.model.stencil.rules.StencilRule;

import java.util.List;

/**
 * Created by alex on 01.10.2015.<br/>
 * Провайдер правил
 */
public interface StencilRulesProvider<T extends StencilRule> {

    List<T> getAll();
}
