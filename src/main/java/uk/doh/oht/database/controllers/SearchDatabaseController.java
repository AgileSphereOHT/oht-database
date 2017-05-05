package uk.doh.oht.database.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.doh.oht.database.model.SearchData;
import uk.doh.oht.database.model.SearchResults;
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

    @GetMapping(value = "/oht-database/retrieve-open-registrations", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(
            value = "Gets a list of all cases matching search data criteria",
            notes = "Send a request to return cases matching all search data criteria in RINA sub system"
    )
    public ResponseEntity<List<SearchResults>> retrieveOpenRegistrations(@RequestParam final List<SearchData> searchData) {
        return ResponseEntity.ok().body(databaseSearchResultsService.searchCases(searchData));
    }
}
