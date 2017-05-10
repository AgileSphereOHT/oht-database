package uk.doh.oht.database.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uk.doh.oht.database.domain.RegistrationData;
import uk.doh.oht.database.service.DatabaseUpdateService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by peterwhitehead on 10/05/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UpdateDatabaseControllerTest {
    private final static String REST_UPDATE_URI = "/oht-database/update-registration";

    private MockMvc mockMvc;
    private ObjectWriter objectWriter;

    @Mock
    private DatabaseUpdateService databaseUpdateService;

    private RegistrationData registrationData;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new UpdateDatabaseController(databaseUpdateService))
                .build();
        objectWriter = new ObjectMapper().writer();

        registrationData = new RegistrationData();
    }

    @Test
    public void testUpdateRegistration() throws Exception {
        final Boolean rtn = Boolean.TRUE;
        given(databaseUpdateService.updateRegistration(Mockito.<RegistrationData> any())).willReturn(rtn);

        mockMvc.perform(MockMvcRequestBuilders.post(REST_UPDATE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRegistrationDataAsJson()))
                .andExpect(handler().methodName("updateRegistration"))
                .andExpect(handler().handlerType(UpdateDatabaseController.class))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("true"));
    }

    private String validRegistrationDataAsJson() throws JsonProcessingException {
        return objectWriter.writeValueAsString(registrationData);
    }
}