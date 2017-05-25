package uk.doh.oht.database.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.doh.oht.db.domain.PendingRegistrationData;
import uk.doh.oht.db.domain.SearchData;
import uk.doh.oht.db.domain.RegistrationData;
import uk.doh.oht.db.domain.UserWorkDetails;
import uk.doh.oht.database.service.DatabaseSearchResultsService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by peterwhitehead on 04/05/2017.
 */
@Slf4j
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
        try {
            log.info("Enter retrieveOpenRegistrations");
            return ResponseEntity.ok().body(databaseSearchResultsService.searchCases(searchData));
        } finally {
            log.info("Exit retrieveOpenRegistrations");
        }
    }

    @PostMapping(value = "/oht-database/retrieve-pending-registrations", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(
            value = "Gets a list of all pending registration",
            notes = "Send a request to return all search pending registrations from database"
    )
    public ResponseEntity<List<PendingRegistrationData>> retrievePendingRegistrations() {
        try {
            log.info("Enter retrievePendingRegistrations");
            return ResponseEntity.ok().body(databaseSearchResultsService.getPendingRegistrations());
        } finally {
            log.info("Exit retrievePendingRegistrations");
        }
    }

    @PostMapping(value = "/oht-database/retrieve-user-work-details", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(
            value = "Gets count of users changes",
            notes = "Send a request to return a count of user changes from database"
    )
    public ResponseEntity<UserWorkDetails> retrieveUserUsage(@RequestBody final String userName) {
        try {
            log.info("Enter retrieveUserUsage");
            return ResponseEntity.ok().body(databaseSearchResultsService.getUserUsage(userName));
        } finally {
            log.info("Exit retrieveUserUsage");
        }
    }

    @GetMapping(value = "/oht-database/retrieve-country", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(
            value = "Gets description from country code",
            notes = "Send a description from country code from database"
    )
    public ResponseEntity<String> retrieveCountryDescription(@RequestParam final String countryCode) {
        try {
            log.info("Enter retrieveCountryDescription");
            return ResponseEntity.ok().body(databaseSearchResultsService.getCountryDescription(countryCode));
        } finally {
            log.info("Exit retrieveCountryDescription");
        }
    }
}
