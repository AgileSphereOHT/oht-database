package uk.doh.oht.database.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.doh.oht.database.domain.RegistrationData;
import uk.doh.oht.database.repos.RegistrationRepository;
import uk.doh.oht.database.validation.StartDateFormDate;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by peterwhitehead on 10/05/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DatabaseUpdateServiceTest {
    private DatabaseUpdateService databaseUpdateService;

    @Mock
    private RegistrationRepository registrationRepository;
    @Mock
    private EntityRepositoryHelper entityRepositoryHelper;

    private RegistrationData registrationData;

    @Before
    public void setUp() throws Exception {
        databaseUpdateService = new DatabaseUpdateService(registrationRepository, entityRepositoryHelper);
    }

    @Test
    public void testUpdateRegistration() throws Exception {
        registrationData = new RegistrationData();
        registrationData.setS073StartDate(new StartDateFormDate(new Date()));
        registrationData.setStartDate(new StartDateFormDate(new Date()));
        assertThat(Boolean.TRUE, is(databaseUpdateService.updateRegistration(registrationData)));
    }
}