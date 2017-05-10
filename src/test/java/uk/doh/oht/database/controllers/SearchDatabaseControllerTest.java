package uk.doh.oht.database.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import uk.doh.oht.database.domain.RegistrationData;
import uk.doh.oht.database.domain.SearchData;
import uk.doh.oht.database.service.DatabaseSearchResultsService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by peterwhitehead on 08/05/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SearchDatabaseControllerTest {
    private final static String REST_SEARCH_OPEN_URI = "/oht-database/retrieve-registrations";
    private final static String REST_SEARCH_PENDING_URI = "/oht-database/retrieve-pending-registrations";

    private MockMvc mockMvc;

    @Mock
    private DatabaseSearchResultsService databaseSearchResultsService;

    private List<RegistrationData> registrationDataList;
    private List<SearchData> searchDataList;

    private ObjectWriter objectWriter;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new SearchDatabaseController(databaseSearchResultsService))
                .build();
        objectWriter = new ObjectMapper().writer();

        searchDataList = new ArrayList<>();
        final SearchData searchData = new SearchData();
        searchDataList.add(searchData);

        registrationDataList = new ArrayList<>();
        final RegistrationData registrationData = new RegistrationData();
        registrationData.setRegistrationId(1l);
        registrationDataList.add(registrationData);
    }

    @Test
    public void testRetrieveOpenRegistrations() throws Exception {
        given(databaseSearchResultsService.searchCases(searchDataList)).willReturn(registrationDataList);

        mockMvc.perform(MockMvcRequestBuilders.post(REST_SEARCH_OPEN_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(validSearchDataAsJson()))
                .andExpect(handler().methodName("retrieveOpenRegistrations"))
                .andExpect(handler().handlerType(SearchDatabaseController.class))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("[{\"registrationId\":1,\"userDetails\":null,\"addresses\":null,\"benefitType\":null,\"issueType\":null,\"registrationStatus\":null,\"country\":null,\"entitlementDate\":null,\"startDate\":null,\"hasForeignPension\":null,\"requestedBy\":null,\"s073StartDate\":null,\"caseId\":null}]"));
    }

    @Test
    public void testRetrievePendingRegistrations() throws Exception {
        given(databaseSearchResultsService.getPendingRegistrations()).willReturn(registrationDataList);

        mockMvc.perform(MockMvcRequestBuilders.post(REST_SEARCH_PENDING_URI))
                .andExpect(handler().methodName("retrievePendingRegistrations"))
                .andExpect(handler().handlerType(SearchDatabaseController.class))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("[{\"registrationId\":1,\"userDetails\":null,\"addresses\":null,\"benefitType\":null,\"issueType\":null,\"registrationStatus\":null,\"country\":null,\"entitlementDate\":null,\"startDate\":null,\"hasForeignPension\":null,\"requestedBy\":null,\"s073StartDate\":null,\"caseId\":null}]"));

    }

    private String validSearchDataAsJson() throws JsonProcessingException {
        return objectWriter.writeValueAsString(searchDataList);
    }
}