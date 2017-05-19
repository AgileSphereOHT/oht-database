package uk.doh.oht.database.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.doh.oht.db.domain.PendingRegistrationData;
import uk.doh.oht.database.model.*;
import uk.doh.oht.database.repos.*;
import uk.doh.oht.validation.StartDateFormDate;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

/**
 * Created by peterwhitehead on 15/05/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class RegistrationEntityCreatorTest {
    private RegistrationEntityCreator registrationEntityCreator;
    private DataCreationUtil dataCreationUtil;

    @Mock
    private EntityRepositoryHelper entityRepositoryHelper;
    @Mock
    private GenderTypeRepository genderTypeRepository;
    @Mock
    private NationalityRepository nationalityRepository;
    @Mock
    private ContactDetailTypeRepository contactDetailTypeRepository;
    @Mock
    private AddressTypeRepository addressTypeRepository;
    @Mock
    private CountryRepository countryRepository;
    @Mock
    private RegistrationStatusRepository registrationStatusRepository;
    @Mock
    private BenefitTypeRepository benefitTypeRepository;
    @Mock
    private CitizenStatusRepository citizenStatusRepository;

    @Before
    public void setUp() throws Exception {
        registrationEntityCreator = new RegistrationEntityCreator(entityRepositoryHelper);
        dataCreationUtil = new DataCreationUtil();
        given(entityRepositoryHelper.getAddressTypeRepository()).willReturn(addressTypeRepository);
        given(entityRepositoryHelper.getCompletedStatus()).willReturn(
                RegistrationStatusEntity.builder().registrationStatusId(2).description("desc").name("name").build()
        );
        given(entityRepositoryHelper.getContactDetailTypeRepository()).willReturn(contactDetailTypeRepository);
        given(entityRepositoryHelper.getCountryRepository()).willReturn(countryRepository);
        given(entityRepositoryHelper.getGenderTypeRepository()).willReturn(genderTypeRepository);
        given(entityRepositoryHelper.getNationalityRepository()).willReturn(nationalityRepository);
        given(entityRepositoryHelper.retrieveBenefitTypeEntity(anyString())).willReturn(
                BenefitTypeEntity.builder().benefitTypeId(1).description("desc").name("name").build()
        );
        given(entityRepositoryHelper.retrieveCitizenStatusEntity(anyString())).willReturn(
                CitizenStatusEntity.builder().citizenStatusId(1).description("desc").name("name").build()
        );
        given(entityRepositoryHelper.retrieveCountryEntity(anyString())).willReturn(
                CountryEntity.builder().countryId(1).description("desc").name("name").build()
        );
        given(entityRepositoryHelper.getPendingDelayedStatuses()).willReturn(
                Arrays.asList(
                        RegistrationStatusEntity.builder().registrationStatusId(1).description("desc").name("name").build(),
                        RegistrationStatusEntity.builder().registrationStatusId(3).description("desc").name("name").build()
                )
        );
        given(entityRepositoryHelper.getPendingStatus()).willReturn(
                RegistrationStatusEntity.builder().registrationStatusId(1).description("desc").name("name").build()
        );
        given(entityRepositoryHelper.getRejectedStatus()).willReturn(
                RegistrationStatusEntity.builder().registrationStatusId(4).description("desc").name("name").build()
        );
        given(addressTypeRepository.findByName(anyString())).willReturn(
                AddressTypeEntity.builder().addressTypeId(1).description("desc").name("name").build()
        );
        given(contactDetailTypeRepository.findByName(anyString())).willReturn(
                ContactDetailTypeEntity.builder().contactDetailTypeId(1).description("desc").name("name").build()
        );
        given(countryRepository.findByName(anyString())).willReturn(
                CountryEntity.builder().countryId(1).description("desc").name("name").build()
        );
        given(genderTypeRepository.findByName(anyString())).willReturn(
                GenderEntity.builder().genderId(1).description("desc").name("name").build()
        );
        given(nationalityRepository.findByName(anyString())).willReturn(
                NationalityEntity.builder().nationalityId(1).description("desc").name("name").build()
        );
    }

    @Test
    public void testCreateRegistrationEntity() throws Exception {
        final Date today = new Date();
        final RegistrationEntity registrationEntity1 = dataCreationUtil.createRegistrationEntity(today);
        final PendingRegistrationData pendingRegistrationData =dataCreationUtil.createPendingRegistrationData(today);
        pendingRegistrationData.setStartDate(new StartDateFormDate(today));
        final RegistrationEntity registrationEntity = registrationEntityCreator.createRegistrationEntity(pendingRegistrationData);
        assertThat(registrationEntity.getCreatedBy(), is(registrationEntity1.getCreatedBy()));
    }

    @Test
    public void testCreateCitizenEntity() throws Exception {
        final Date today = new Date();
        final Timestamp ts = new Timestamp(today.getTime());
        final CitizenEntity citizenEntity1 = dataCreationUtil.createCitizenEntity(today);
        final CitizenEntity citizenEntity = registrationEntityCreator.createCitizenEntity(
                dataCreationUtil.createPendingRegistrationData(today), ts);
        assertThat(citizenEntity.getCreatedBy(), is(citizenEntity1.getCreatedBy()));
    }
}