package co.jtoehen;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import co.jtoehen.configuration.SecurityConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
@WebAppConfiguration
public class ApplicationSecurityTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    /**
     * Test authorized access
     * @throws Exception
     */
    @Test
    public void testProtectedRest() throws Exception {
        mvc.perform(get("/spring/atm/")
                .with(httpBasic("user","password")))
                .andExpect(status().isOk());
    }

    /**
     * Test unauthorized access
     * @throws Exception
     */
    @Test
    public void testProtectedRestUnauthorized() throws Exception {
        mvc.perform(get("/spring/atm/")
                .with(httpBasic("user","otherword")))
                .andExpect(status().isUnauthorized());
    }
}
