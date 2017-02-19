package co.jtoehen.service;

import co.jtoehen.controller.AtmRestController;
import co.jtoehen.model.Atm;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.EndpointInject;
import org.apache.camel.FluentProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jtoehen on 20/2/2017.
 */
@Service
public class AtmService
{
    private static Logger log = LoggerFactory.getLogger(AtmService.class);

    /**
     * Inject Camel producer to use camel-http to request for the atm list.
     */
    @EndpointInject(uri = "https://www.ing.nl/api/locator/atms/")
    private FluentProducerTemplate producer;

    public List<Atm> getAtm() throws IOException
    {
        // call Camel to get the list, the returned string is in JSon format
        String where = producer.request(String.class);

        // json string clean-up before parsing
        where = where.replace(")]}',","");

        List<Atm> list = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        list = mapper.readValue(where, new TypeReference<List<Atm>>(){});

        return list;
    }
}
