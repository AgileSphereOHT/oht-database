package uk.doh.oht.database.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.doh.oht.db.domain.PendingRegistrationData;
import uk.doh.oht.database.model.RegistrationEntity;
import uk.doh.oht.database.repos.PendingRegistrationRepository;
import uk.doh.oht.database.repos.RegistrationRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

/**
 * Created by peterwhitehead on 15/05/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DatabaseCreateServiceTest {
    private DatabaseCreateService databaseCreateService;

    @Mock
    private RegistrationRepository registrationRepository;
    @Mock
    private PendingRegistrationRepository pendingRegistrationRepository;
    @Mock
    private RegistrationEntityCreator registrationEntityCreator;
    @Mock
    private EntityRepositoryHelper entityRepositoryHelper;

    private PendingRegistrationData pendingRegistrationData;

    @Before
    public void setUp() throws Exception {
        databaseCreateService = new DatabaseCreateService(
                registrationRepository,
                registrationEntityCreator,
                pendingRegistrationRepository,
                entityRepositoryHelper
        );
        pendingRegistrationData = new PendingRegistrationData();
    }

    @Test
    public void testCreateS1Request() throws Exception {
        final RegistrationEntity registrationEntity = new RegistrationEntity();
        given(registrationEntityCreator.createRegistrationEntity(pendingRegistrationData)).willReturn(registrationEntity);
        assertThat(Boolean.TRUE, is(databaseCreateService.createS1Request(pendingRegistrationData)));
    }
}