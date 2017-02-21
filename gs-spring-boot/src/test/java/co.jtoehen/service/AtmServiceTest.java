package co.jtoehen.service;

import co.jtoehen.TestConfiguration;
import co.jtoehen.model.Atm;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by jtoehen on 21/2/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class AtmServiceTest
{
    @Mock
    private RestTemplate template;

    @InjectMocks
    private AtmService service;

    @Before
    public void beforeTest()
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void afterTest()
    {
        service = null;
        template = null;
    }

    /**
     * Positive test with a well-structured json returned.
     * @throws IOException
     */
    @Test
    public void testGetAtm() throws IOException
    {
        when(template.getForObject("dummyURL", String.class)).thenReturn(")]}',\n" +
                "[{\"address\":{\"street\":\"Ameidestraat\",\"housenumber\":\"14\",\"postalcode\":\"5701 NP\"," +
                "\"city\":\"HELMOND\",\"geoLocation\":{\"lat\":\"51.479018\",\"lng\":\"5.656697\"}},\"distance\":0," +
                "\"type\":\"ING\"}]");

        List<Atm> list = service.getAtm("dummyURL");

        assert(list != null);
        assert(list.size() == 1);
    }

    /**
     * Negative test with malformed json returned.
     * @throws IOException
     */
    @Test(expected = IOException.class)
    public void testGetAtmWithException() throws IOException
    {
        when(template.getForObject("dummyURL", String.class)).thenReturn(")]}',\n" +
                "[{\"street\":\"Ameidestraat\",\"housenumber\":\"14\",\"postalcode\":\"5701 NP\"," +
                "\"city\":\"HELMOND\",\"geoLocation\":{\"lat\":\"51.479018\",\"lng\":\"5.656697\"}},\"distance\":0}]");

        service.getAtm("dummyURL");
    }
}
