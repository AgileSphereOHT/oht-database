package uk.doh.oht.database.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.doh.oht.database.domain.RegistrationData;
import uk.doh.oht.database.service.DatabaseUpdateService;

import javax.inject.Inject;

/**
 * Created by peterwhitehead on 09/05/2017.
 */
@RestController
public class UpdateDatabaseController {
    private final DatabaseUpdateService databaseUpdateService;

    @Inject
    public UpdateDatabaseController(final DatabaseUpdateService databaseUpdateService) {
        this.databaseUpdateService = databaseUpdateService;
    }

    @PostMapping(value = "/oht-database/update-registration", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(
            value = "Gets a list of all registrations matching search data criteria",
            notes = "Send a request to return registrations matching all search data criteria in database"
    )
    public ResponseEntity<Boolean> updateRegistration(@RequestBody final RegistrationData registrationData) {
        return ResponseEntity.ok().body(databaseUpdateService.updateRegistration(registrationData));
    }

}
