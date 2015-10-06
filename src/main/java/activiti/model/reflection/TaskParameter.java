package activiti.model.reflection;

import java.lang.annotation.*;

/**
 * Created by alex on 03.10.2015.<br/>
 * TODO comment me
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TaskParameter {

    String stencilParameter() default "";

    String xmlParameter() default "";
}
