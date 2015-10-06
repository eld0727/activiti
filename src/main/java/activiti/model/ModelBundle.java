package activiti.model;

import activiti.model.converters.json.ExtendedBaseBpmnJsonConverter;
import activiti.model.stencil.Stencil;
import activiti.model.stencil.rules.StencilRule;
import org.activiti.bpmn.converter.BaseBpmnXMLConverter;
import org.activiti.engine.parse.BpmnParseHandler;

import java.util.List;

/**
 * Created by alex on 02.10.2015.<br/>
 * Сборник необходимых компонентов
 */
public interface ModelBundle {

    String getStencilId();

    /**
     * Компонент для отображения в редакторе
     */
    Stencil getStencil();

    /**
     * JSON конвертер компонента
     */
    ExtendedBaseBpmnJsonConverter getJsonConverter();

    /**
     * XML конвертер компонента
     */
    BaseBpmnXMLConverter getXMLConverter();

    /**
     * Парсер компонента
     */
    BpmnParseHandler getBpmnParseHandler();

    /**
     * Необходимые для модели правила
     */
    List<StencilRule> getRequiredRules();
}
