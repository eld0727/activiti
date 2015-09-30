package otts.test.work.controller;

import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import otts.test.work.service.DeploymentService;

/**
 * Created by alex on 29.09.2015.<br/>
 * TODO comment me
 */
@Controller
@RequestMapping("/api/deployment")
public class DeploymentController {

    @Autowired
    private DeploymentService deploymentService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, params = "model")
    public Deployment deployModel(
        @RequestParam("model") String modelId
    ) {
        return deploymentService.deployModel(modelId);
    }
}
