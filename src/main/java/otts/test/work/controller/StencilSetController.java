package otts.test.work.controller;

import activiti.model.stencil.StencilSet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by alex on 01.10.2015.<br/>
 * TODO comment me
 */
@Controller
@RequestMapping("/api/stencil-set")
public class StencilSetController {

    @Autowired
    private ApplicationContext applicationContext;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, params = "id", produces = "text/plain; charset=utf-8")
    public String stencilSet(
            @RequestParam String id
    ) {
        StencilSet stencilSet = applicationContext.getBean(StencilSet.class, id);
        try {
            return new ObjectMapper().writeValueAsString(stencilSet);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
