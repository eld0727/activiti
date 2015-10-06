package otts.test.work.service.impl;

import activiti.model.converters.BpmnConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.log4j.Log4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import otts.test.work.service.DeploymentService;

/**
 * Created by alex on 29.09.2015.<br/>
 * TODO comment me
 */
@Log4j
@Service
public class DeploymentServiceImpl implements DeploymentService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private BpmnConverter bpmnConverter;

    @Override
    public Deployment deployModel(String modelId) {
        try {
            byte[] source = repositoryService.getModelEditorSource(modelId);
            final ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(new String(source, "UTF-8"));
            BpmnModel bpmnModel = bpmnConverter.convertToBpmnModel(modelNode);
            byte[] bpmnBytes = bpmnConverter.convertToXML(bpmnModel);
            Model model = repositoryService.getModel(modelId);
            String processName = model.getKey() + ".bpmn20.xml";
            return repositoryService.createDeployment()
                    .name(model.getName() + " deployment")
                    .addString(processName, new String(bpmnBytes, "UTF-8"))
                    .deploy();
        } catch (Exception e) {
            log.error("Something wrong: " + e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }
}
