package otts.test.work.delegates.hello.world;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * Created by alex on 14.09.2015.<br/>
 * Делегат который говорит "Hello World"
 */
public class HelloWorldDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println(execution.getVariable("var1"));
        System.out.println(execution.getVariable("var2"));
    }
}
