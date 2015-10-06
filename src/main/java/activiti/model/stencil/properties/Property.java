package activiti.model.stencil.properties;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Created by alex on 30.09.2015.<br/>
 * Описание одного свойства компонента
 */
@Data
@Builder
public class Property {

    public final static String STRING = "string";
    public final static String BOOLEAN = "boolean";
    public final static String INTEGER = "integer";
    public final static String FLOAT = "float";
    public final static String COLOR = "color";
    public final static String DATE = "date";
    public final static String CHOICE = "choice";
    public final static String URL = "url";
    public final static String DIAGRAM_LINK = "diagramlink";
    public final static String COMPLEX = "complex";
    public final static String MULTIPLECOMPLEX = "multiplecomplex";
    public final static String TEXT = "text";
    public final static String KISBPM_MULTIINSTANCE = "kisbpm-multiinstance";
    public final static String MODEL_LINK = "modellink";
    public final static String FORM_FLOW_LINK = "formflowlink";
    public final static String FORM_LINK = "formlink";
    public final static String SUB_PROCESS_LINK = "subprocesslink";
    public final static String SERVICE_LINK = "servicelink";
    public final static String CONDITIONS = "conditions";
    public final static String VARIABLES = "variables";
    public final static String LISTENER = "listener";
    public final static String EPC_FREQ = "epcfrequency";
    public final static String GLOSSARY_LINK = "glossarylink";
    public final static String EXPRESSION = "expression";
    public final static String DATASOURCE = "datasource";
    public final static String DATASOURCE_MINIMAL = "datasource-minimal";
    public final static String VALIDATORS = "validators";

    /**
     * Идентификатор свойства, должен быть уникален в пределах компонента
     */
    private String id;

    /**
     * Тип свойства, доступные:
     * <code>
     * <ul>
     * 	<li>{@value #STRING}</li>
     * 	<li>{@value #BOOLEAN}</li>
     * 	<li>{@value #INTEGER}</li>
     * 	<li>{@value #FLOAT}</li>
     * 	<li>{@value #COLOR}</li>
     * 	<li>{@value #DATE}</li>
     * 	<li>{@value #CHOICE}</li>
     * 	<li>{@value #URL}</li>
     * 	<li>{@value #DIAGRAM_LINK}</li>
     * 	<li>{@value #COMPLEX}</li>
     * 	<li>{@value #MULTIPLECOMPLEX}</li>
     * 	<li>{@value #TEXT}</li>
     * 	<li>{@value #KISBPM_MULTIINSTANCE}</li>
     * 	<li>{@value #MODEL_LINK}</li>
     * 	<li>{@value #FORM_FLOW_LINK}</li>
     * 	<li>{@value #FORM_LINK}</li>
     * 	<li>{@value #SUB_PROCESS_LINK}</li>
     * 	<li>{@value #SERVICE_LINK}</li>
     * 	<li>{@value #CONDITIONS}</li>
     * 	<li>{@value #VARIABLES}</li>
     * 	<li>{@value #LISTENER}</li>
     * 	<li>{@value #EPC_FREQ}</li>
     * 	<li>{@value #GLOSSARY_LINK}</li>
     * 	<li>{@value #EXPRESSION}</li>
     * 	<li>{@value #DATASOURCE}</li>
     * 	<li>{@value #DATASOURCE_MINIMAL}</li>
     * 	<li>{@value #VALIDATORS}</li>
     * <ul>
     * </code>
     */
    private String type = STRING;

    /**
     * Название свойства
     */
    private String title;

    /**
     * Стартовое значение свойства
     */
    private Object value;

    /**
     * Короткое описание свойства
     */
    private String description;

    /**
     * Можно ли изменять значение свойства
     */
    private boolean readonly = false;

    /**
     * Формат даты  если {@link #type} равен <b>{@value #DATE}</b>
     */
    private String dateFormat;

    /**
     * Обязательное ли значение
     */
    private boolean optional = true;

    /**
     * Описывает список, того что можно выбрать, если {@link #type} равен <b>{@value #CHOICE}</b>
     */
    private List<Object> items;

    /**
     * Максимальная длина строки, если {@link #type} равен <b>{@value #STRING}</b>
     */
    private Integer length;

    /**
     * Максимальная длина строки, если {@link #type} равен <b>{@value #INTEGER}</b> или <b>{@value #FLOAT}</b>
     */
    private Integer min;

    /**
     * Максимальная длина строки, если {@link #type} равен <b>{@value #INTEGER}</b> или <b>{@value #FLOAT}</b>
     */
    private Integer max;

    /**
     * id элементов внутри SVG представления компонента
     */
    private List<String> refToView;

    /**
     * Нужно ли переносить строку, если {@link #type} равен <b>{@value #STRING}</b>
     */
    private Boolean wrapValue;

    private boolean popular;
}
