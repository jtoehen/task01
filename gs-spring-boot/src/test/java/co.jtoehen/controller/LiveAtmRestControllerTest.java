package co.jtoehen.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:application.xml")
public class LiveAtmRestControllerTest
{
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception
    {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    /**
     * Live test with remote site to returned filtered atms based on location.
     * @throws Exception
     */
    @Test
    public void testGetAtmBasedOnLocationLive() throws Exception
    {
        mockMvc.perform(get("/spring/atm/OSS")
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].address.city", is("OSS")));
    }

    /**
     * Live test with remote site to returned all atms.
     * @throws Exception
     */
    @Test
    public void testGetAllAtmLive() throws Exception
    {
        mockMvc.perform(get("/spring/atm/")
                .contentType(contentType))
                .andExpect(status().isOk());
    }
}