package co.jtoehen.service;

import co.jtoehen.model.Atm;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    private RestTemplate template;

    private ObjectMapper mapper;

    public List<Atm> getAtm(String url) throws IOException, RestClientException
    {
        String where = template.getForObject(url, String.class);

        if(where == null)
        {
            throw new IOException("empty json returned from remote host.");
        }

        // json string clean-up before parsing
        if(where.indexOf(")]}',") != -1)
        {
            where = where.replace(")]}',","");
        }

        List<Atm> list = new ArrayList<>();
        mapper = new ObjectMapper();

        list = mapper.readValue(where, new TypeReference<List<Atm>>(){});

        return list;
    }
}
