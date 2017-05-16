package uk.doh.oht.database.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uk.doh.oht.database.domain.PendingRegistrationData;
import uk.doh.oht.database.service.DatabaseCreateService;
import uk.doh.oht.database.service.EntityRepositoryHelper;

import javax.sql.DataSource;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by peterwhitehead on 15/05/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@MockBean(EntityRepositoryHelper.class)
@MockBean(DataSource.class)
public class CreateDatabaseControllerTest {
    private final static String REST_CREATE_URI = "/oht-database/create-s1-request";

    @Mock
    private DatabaseCreateService databaseCreateService;

    private MockMvc mockMvc;

    private ObjectWriter objectWriter;
    private PendingRegistrationData pendingRegistrationData;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new CreateDatabaseController(databaseCreateService))
                .build();
        objectWriter = new ObjectMapper().writer();
        pendingRegistrationData = new PendingRegistrationData();
    }

    @Test
    public void testCreateS1Request() throws Exception {
        given(databaseCreateService.createS1Request(pendingRegistrationData)).willReturn(Boolean.TRUE);

        mockMvc.perform(MockMvcRequestBuilders.post(REST_CREATE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(validPendingRegistrationDataAsJson()))
                .andExpect(handler().methodName("createS1Request"))
                .andExpect(handler().handlerType(CreateDatabaseController.class))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("true"));

    }

    private String validPendingRegistrationDataAsJson() throws JsonProcessingException {
        return objectWriter.writeValueAsString(pendingRegistrationData);
    }
}