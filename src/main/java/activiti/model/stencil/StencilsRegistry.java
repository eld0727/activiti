package activiti.model.stencil;

import activiti.model.ModelBundle;
import activiti.model.stencil.defaultset.rules.StencilRulesProvider;
import activiti.model.stencil.defaultset.stencils.StencilProvider;
import activiti.model.stencil.rules.StencilRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by alex on 01.10.2015.<br/>
 * Реест всех графических компонентов для редактора
 */
@Component
public class StencilsRegistry {

    @Autowired
    private List<StencilProvider> stencilProviders;

    @Autowired
    private List<StencilRulesProvider> stencilRulesProviders;

    @Autowired(required = false)
    private List<ModelBundle> modelBundles;

    @PostConstruct
    private void postConstruct() {
        if(modelBundles == null) {
            modelBundles = new ArrayList<>();
        }
    }

    public StencilSet.StencilSetBuilder createSet() {
        return StencilSet.builder()
                .stencils(getAllStencils())
                .stencilRules(getAllStencilRules());
    }

    private Collection<StencilRule> getAllStencilRules() {
        Set<StencilRule> rules = new LinkedHashSet<>();
        for (StencilRulesProvider stencilRulesProvider : stencilRulesProviders) {
            rules.addAll(stencilRulesProvider.getAll());
        }
        for (ModelBundle modelBundle : modelBundles) {
            if(modelBundle.getRequiredRules() != null) {
                rules.addAll(modelBundle.getRequiredRules());
            }
        }
        return rules;
    }

    private Collection<Stencil> getAllStencils() {
        Set<Stencil> stencils = new LinkedHashSet<>();
        for (StencilProvider stencilProvider : stencilProviders) {
            stencils.addAll(stencilProvider.getAll());
        }
        for (ModelBundle modelBundle : modelBundles) {
            stencils.add(modelBundle.getStencil());
        }
        return stencils;
    }
}
