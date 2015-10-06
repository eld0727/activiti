package activiti.model.stencil.defaultset.stencils;

import activiti.model.stencil.Stencil;

import java.util.List;

/**
 * Created by alex on 01.10.2015.<br/>
 * Провайдер компонентов
 */
public interface StencilProvider {

    List<Stencil> getAll();
}
