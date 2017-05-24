package uk.doh.oht.database.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import uk.doh.oht.db.domain.PendingRegistrationData;
import uk.doh.oht.db.domain.RegistrationData;
import uk.doh.oht.db.domain.SearchData;
import uk.doh.oht.database.model.PendingRegistrationEntity;
import uk.doh.oht.database.model.RegistrationEntity;
import uk.doh.oht.database.model.RegistrationStatusEntity;
import uk.doh.oht.database.repos.PendingRegistrationRepository;
import uk.doh.oht.database.repos.RegistrationRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

/**
 * Created by peterwhitehead on 08/05/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DatabaseSearchResultsServiceTest {
    private DatabaseSearchResultsService databaseSearchResultsService;

    @Mock
    private PendingRegistrationRepository pendingRegistrationRepository;
    @Mock
    private RegistrationRepository registrationRepository;
    @Mock
    private EntityResultConverter entityResultConverter;
    @Mock
    private EntityRepositoryHelper entityRepositoryHelper;
    @Mock
    private EntityManager entityManager;
    @Mock
    private Query query;

    private List<SearchData> searchDataList;
    private List<PendingRegistrationData> pendingRegistrationDataList;
    private List<RegistrationData> registrationDataList;
    private RegistrationData registrationData;

    @Before
    public void setUp() throws Exception {
        databaseSearchResultsService = new DatabaseSearchResultsService(
                pendingRegistrationRepository,
                registrationRepository,
                entityResultConverter,
                entityRepositoryHelper,
                entityManager
        );

        searchDataList = new ArrayList<>();
        final SearchData searchData = new SearchData();
        searchDataList.add(searchData);

        registrationDataList = new ArrayList<>();
        registrationData = new RegistrationData();
        registrationData.setRegistrationId(1l);
        registrationDataList.add(registrationData);

        pendingRegistrationDataList = new ArrayList<>();
        final PendingRegistrationData pendingRegistrationData = new PendingRegistrationData();
        pendingRegistrationData.setPendingRegistrationId(1l);
        pendingRegistrationDataList.add(pendingRegistrationData);
    }

    @Test
    public void testSearchCases() throws Exception {
        final RegistrationEntity registrationEntity = new RegistrationEntity();
        registrationEntity.setRegistrationId(1L);

        given(registrationRepository.findByCitizenEntityNinoIgnoreCaseAndRegistrationStatusEntityIn(
                anyString(), Mockito.<List<RegistrationStatusEntity>> any())).willReturn(registrationEntity);
        given(registrationRepository.findByCitizenEntityNinoIgnoreCaseAndRegistrationStatusEntityIn(
                anyString(), Mockito.<List<RegistrationStatusEntity>> any())).willReturn(null);
        given(entityManager.createNativeQuery(anyString(), Mockito.<Class<RegistrationEntity>> any())).willReturn(query);

        given(entityResultConverter.convertRegistrationEntity( Mockito.<List<RegistrationEntity>> any())).willReturn(registrationDataList);
        final List<RegistrationData> registrationDataList1 = databaseSearchResultsService.searchCases(searchDataList);
        assertThat(registrationDataList, is(registrationDataList1));
    }

    @Test
    public void testSearchCases2() throws Exception {
        final RegistrationEntity registrationEntity = new RegistrationEntity();
        registrationEntity.setRegistrationId(1L);
        final List<RegistrationEntity> registrationEntityList = new ArrayList<>();
        registrationEntityList.add(registrationEntity);

        given(registrationRepository.findByCitizenEntityNinoIgnoreCaseAndRegistrationStatusEntityIn(
                anyString(), Mockito.<List<RegistrationStatusEntity>> any())).willReturn(null);
        given(entityManager.createNativeQuery(anyString(), Mockito.<Class<RegistrationEntity>> any())).willReturn(query);
        given(query.getResultList()).willReturn(registrationEntityList);
        given(entityResultConverter.convertRegistrationEntity(registrationEntityList)).willReturn(registrationDataList);

        final List<RegistrationData> registrationDataList1 = databaseSearchResultsService.searchCases(searchDataList);
        assertThat(registrationDataList, is(registrationDataList1));
    }

    @Test
    public void testGetPendingRegistrations() throws Exception {
        final PendingRegistrationEntity pendingRegistrationEntity = new PendingRegistrationEntity();
        final List<PendingRegistrationEntity> pendingRegistrationEntityList = new ArrayList<>();
        pendingRegistrationEntityList.add(pendingRegistrationEntity);

        given(pendingRegistrationRepository.findByRegistrationStatusEntity(Mockito.<RegistrationStatusEntity> any())).willReturn(pendingRegistrationEntityList);
        given(entityResultConverter.convertPendingRegistrationEntity(pendingRegistrationEntityList)).willReturn(pendingRegistrationDataList);
        final List<PendingRegistrationData> pendingRegistrationDataList1 = databaseSearchResultsService.getPendingRegistrations();
        assertThat(pendingRegistrationDataList, is(pendingRegistrationDataList1));
    }
}