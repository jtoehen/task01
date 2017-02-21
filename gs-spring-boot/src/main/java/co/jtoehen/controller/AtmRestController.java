package co.jtoehen.controller;

import co.jtoehen.exception.AtmException;
import co.jtoehen.exception.ErrorResponse;
import co.jtoehen.model.Atm;
import co.jtoehen.service.AtmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    @Autowired
    private AtmService service;

    /**
     * HTTP GET method for atm based on location.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/atm/{location}",
                    produces = "application/json")
    public ResponseEntity<List<Atm>> getAtm(@PathVariable("location") String location) throws AtmException
    {
        List<Atm> list = new ArrayList<>();

        List<Atm> output = new ArrayList<>();

        try
        {
            list = service.getAtm("https://www.ing.nl/api/locator/atms/");

            for(Atm atm : list)
            {
                String city = atm.getAddress().getCity();
                if (city.equalsIgnoreCase(location))
                {
                    output.add(atm);
                }
            }
        }
        catch(Exception e)
        {
            throw new AtmException(e.getMessage());
        }

        return new ResponseEntity<List<Atm>>(output, HttpStatus.OK);
    }

    /**
     * HTTP GET method for all atms
     */
    @RequestMapping(method = RequestMethod.GET, value = "/atm/",
            produces = "application/json")
    public ResponseEntity<List<Atm>> getAllAtm() throws AtmException
    {
        List<Atm> list = new ArrayList<>();

        try
        {
            list = service.getAtm("https://www.ing.nl/api/locator/atms/");
        }
        catch(Exception e)
        {
            throw new AtmException(e.getMessage());
        }

        return new ResponseEntity<List<Atm>>(list, HttpStatus.OK);
    }

    @ExceptionHandler(AtmException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex)
    {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
    }
}

