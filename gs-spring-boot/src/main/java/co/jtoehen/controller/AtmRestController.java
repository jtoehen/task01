package co.jtoehen.controller;

import co.jtoehen.model.Atm;
import co.jtoehen.service.AtmService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.BeanInject;
import org.apache.camel.EndpointInject;
import org.apache.camel.FluentProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Spring REST endpoint
 */
@RestController
@RequestMapping("/spring")
public class AtmRestController
{

    private static Logger log = LoggerFactory.getLogger(AtmRestController.class);

    @BeanInject
    private AtmService service;

    /**
     * HTTP GET method for atm based on location.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/atm/{location}",
                    produces = "application/json")
    public String getAtm(@PathVariable("location") String location)
    {
        log.info("get the atms.");

        ObjectMapper mapper = new ObjectMapper();
        String out = "";
        List<Atm> output = new ArrayList<>();

        try
        {
            List<Atm> list = service.getAtm();

            for(Atm atm : list)
            {
                String city = atm.getAddress().getCity();
                if (city.equalsIgnoreCase(location))
                {
                    output.add(atm);
                }
            }
            out = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(output);
        }
        catch(IOException io)
        {
            log.error(io.getMessage());
        }

        return out;
    }

    /**
     * HTTP GET method for all atms
     */
    @RequestMapping(method = RequestMethod.GET, value = "/atm/",
            produces = "application/json")
    public String getAllAtm()
    {
        log.info("get all the atms.");

        ObjectMapper mapper = new ObjectMapper();
        String out = "";

        try
        {
            List<Atm> list = service.getAtm();

            out = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        }
        catch(IOException io)
        {
            log.error(io.getMessage());
        }

        return out;
    }
}

