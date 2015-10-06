package otts.test.work.configuration;

import activiti.model.ModelBundle;
import activiti.model.converters.BpmnConverter;
import activiti.model.stencil.StencilSet;
import activiti.model.stencil.StencilsRegistry;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.parse.BpmnParseHandler;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 16.09.2015.<br/>
 * Configuration of Activiti
 */
@Configuration
public class ActivitiConfiguration {

    private static Logger log = Logger.getLogger(ActivitiConfiguration.class);

    @Autowired(required = false)
    private List<ModelBundle> modelBundles;

    @Bean
    @DependsOn("bpmnConverter")
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(
         final PlatformTransactionManager transactionManager,
         final DataSource dataSource
    ) throws IOException {
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setTransactionManager(transactionManager);
        configuration.setDatabaseSchemaUpdate(SpringProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        configuration.setJobExecutorActivate(true);
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver=new PathMatchingResourcePatternResolver();
        Resource[] resources = pathMatchingResourcePatternResolver.getResources("classpath*:/otts/test/work/activiti/*.bpmn*");
        configuration.setDeploymentResources(resources);
        if(modelBundles != null) {
            List<BpmnParseHandler> parseHandlers = new ArrayList<>();
            for (ModelBundle modelBundle : modelBundles) {
                BpmnParseHandler parseHandler = modelBundle.getBpmnParseHandler();
                if(parseHandler != null) {
                    parseHandlers.add(parseHandler);
                }
            }
            configuration.setPreBpmnParseHandlers(parseHandlers);
        }
        return configuration;
    }

    @Bean
    public StencilSet defaultStencilSet(final StencilsRegistry stencilsRegistry) {
        return stencilsRegistry.createSet()
                .title("Process editor")
                .namespace("http://b3mn.org/stencilset/bpmn2.0#")
                .description("BPMN process editor")
                .build();
    }

    @Bean
    InitializingBean repositoryInfo(final RepositoryService repositoryService) {

        return new InitializingBean() {
            public void afterPropertiesSet() throws Exception {
                log.info("Current processes");
                for (ProcessDefinition processDefinition : repositoryService.createProcessDefinitionQuery().list()) {
                    log.info("\t" + processDefinition.getId() + " : " + processDefinition.getName());
                }
            }
        };
    }

    @Bean
    InitializingBean usersAndGroupsInitializer(final IdentityService identityService) {

        return new InitializingBean() {
            public void afterPropertiesSet() throws Exception {

                List<User> list = identityService.createUserQuery().list();
                if(list.isEmpty()) {

                    User admin = identityService.newUser("admin");
                    admin.setPassword("admin");
                    admin.setFirstName("admin");
                    identityService.saveUser(admin);

                    User user1 = identityService.newUser("user1");
                    user1.setPassword("user1");
                    user1.setFirstName("user1");
                    identityService.saveUser(user1);

                    User user2 = identityService.newUser("user2");
                    user2.setPassword("user2");
                    user2.setFirstName("user2");
                    identityService.saveUser(user2);

                    User user3 = identityService.newUser("user3");
                    user3.setPassword("user3");
                    user3.setFirstName("user3");
                    identityService.saveUser(user3);
                }

            }
        };
    }

    @Bean
    InitializingBean modelInitializer(
            final RepositoryService repositoryService,
            final BpmnConverter bpmnConverter
    ) {

        return new InitializingBean() {
            public void afterPropertiesSet() throws Exception {
                ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                        .processDefinitionKey("voteProcess")
                        .singleResult();
                BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition.getId());
                ObjectNode node = bpmnConverter.convertToJson(bpmnModel);
                Model model = repositoryService.newModel();
                model.setName("Vote process");
                model.setKey(processDefinition.getKey());
                model.setMetaInfo("{\"name\": \"Vote process\", \"description\":\"\"}");
                repositoryService.saveModel(model);
                repositoryService.addModelEditorSource(model.getId(), node.toString().getBytes("UTF-8"));
            }
        };
    }


}
