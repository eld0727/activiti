package activiti.model.reflection;

import activiti.model.ModelBundle;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by alex on 03.10.2015.<br/>
 * TODO comment me
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
@Scope("prototype")
public @interface CustomTask {

    String type() default "";

    Class<? extends ModelBundle> bundle();
}
