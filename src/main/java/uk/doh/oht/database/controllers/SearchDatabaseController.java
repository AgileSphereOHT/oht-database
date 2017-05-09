package uk.doh.oht.database.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.doh.oht.database.domain.SearchData;
import uk.doh.oht.database.domain.RegistrationData;
import uk.doh.oht.database.service.DatabaseSearchResultsService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by peterwhitehead on 04/05/2017.
 */
@RestController
public class SearchDatabaseController {
    private final DatabaseSearchResultsService databaseSearchResultsService;

    @Inject
    public SearchDatabaseController(final DatabaseSearchResultsService databaseSearchResultsService) {
        this.databaseSearchResultsService = databaseSearchResultsService;
    }

    @PostMapping(value = "/oht-database/retrieve-registrations", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(
            value = "Gets a list of all registrations matching search data criteria",
            notes = "Send a request to return registrations matching all search data criteria in database"
    )
    public ResponseEntity<List<RegistrationData>> retrieveOpenRegistrations(@RequestBody final List<SearchData> searchData) {
        return ResponseEntity.ok().body(databaseSearchResultsService.searchCases(searchData));
    }

    @PostMapping(value = "/oht-database/retrieve-pending-registrations", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(
            value = "Gets a list of all pending registration",
            notes = "Send a request to return all search pending registrations from database"
    )
    public ResponseEntity<List<RegistrationData>> retrievePendingRegistrations() {
        return ResponseEntity.ok().body(databaseSearchResultsService.getPendingRegistrations());
    }
}
