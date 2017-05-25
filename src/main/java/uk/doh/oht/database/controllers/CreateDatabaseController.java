package uk.doh.oht.database.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.doh.oht.db.domain.PendingRegistrationData;
import uk.doh.oht.database.service.DatabaseCreateService;

import javax.inject.Inject;

/**
 * Created by peterwhitehead on 12/05/2017.
 */
@Slf4j
@RestController
public class CreateDatabaseController {
    private final DatabaseCreateService databaseCreateService;

    @Inject
    public CreateDatabaseController(final DatabaseCreateService databaseCreateService) {
        this.databaseCreateService = databaseCreateService;
    }

    @PostMapping(value = "/oht-database/create-s1-request", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(
            value = "Create a registration record/citizen/addresses etc for pending registration",
            notes = "Creates registration data from pending request"
    )
    public ResponseEntity<Boolean> createS1Request(@RequestBody final PendingRegistrationData pendingRegistrationData) {
        try {
            log.info("Enter createS1Request");
            return ResponseEntity.ok().body(databaseCreateService.createS1Request(pendingRegistrationData));
        } finally {
            log.info("Exit createS1Request");
        }
    }

}
