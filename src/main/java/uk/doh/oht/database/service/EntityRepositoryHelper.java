package uk.doh.oht.database.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.doh.oht.database.model.*;
import uk.doh.oht.database.repos.*;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * Created by peterwhitehead on 12/05/2017.
 */
@Slf4j
@Component
public class EntityRepositoryHelper {
    private static final String PENDING = "Pending";
    private static final String DELAYED = "Delayed";
    private static final String COMPLETED = "Completed";
    private static final String REJECTED = "Rejected";

    private final GenderTypeRepository genderTypeRepository;
    private final NationalityRepository nationalityRepository;
    private final ContactDetailTypeRepository contactDetailTypeRepository;
    private final AddressTypeRepository addressTypeRepository;
    private final CountryRepository countryRepository;
    private final RegistrationStatusRepository registrationStatusRepository;
    private final BenefitTypeRepository benefitTypeRepository;
    private final CitizenStatusRepository citizenStatusRepository;

    private final List<RegistrationStatusEntity> pendingDelayedStatuses;
    private final RegistrationStatusEntity completedStatus;
    private final RegistrationStatusEntity rejectedStatus;

    @Inject
    public EntityRepositoryHelper(final GenderTypeRepository genderTypeRepository,
                                  final NationalityRepository nationalityRepository,
                                  final ContactDetailTypeRepository contactDetailTypeRepository,
                                  final AddressTypeRepository addressTypeRepository,
                                  final CountryRepository countryRepository,
                                  final RegistrationStatusRepository registrationStatusRepository,
                                  final BenefitTypeRepository benefitTypeRepository,
                                  final CitizenStatusRepository citizenStatusRepository) {
        this.genderTypeRepository = genderTypeRepository;
        this.nationalityRepository = nationalityRepository;
        this.contactDetailTypeRepository = contactDetailTypeRepository;
        this.addressTypeRepository = addressTypeRepository;
        this.countryRepository = countryRepository;
        this.registrationStatusRepository = registrationStatusRepository;
        this.benefitTypeRepository = benefitTypeRepository;
        this.citizenStatusRepository = citizenStatusRepository;
        this.pendingDelayedStatuses = registrationStatusRepository.findByNameIn(Arrays.asList(PENDING, DELAYED));
        this.completedStatus = registrationStatusRepository.findByName(COMPLETED);
        this.rejectedStatus = registrationStatusRepository.findByName(REJECTED);
    }

    public RegistrationStatusEntity getPendingStatus() {
        for (RegistrationStatusEntity registrationStatusEntity : pendingDelayedStatuses) {
            if (PENDING.equals(registrationStatusEntity.getName())) {
                return registrationStatusEntity;
            }
        }
        return null;
    }

    public RegistrationStatusEntity getCompletedStatus() {
        return completedStatus;
    }

    public RegistrationStatusEntity getRejectedStatus() {
        return rejectedStatus;
    }

    public List<RegistrationStatusEntity> getPendingDelayedStatuses() {
        return pendingDelayedStatuses;
    }

    public CountryEntity retrieveCountryEntity(final String name) {
        return countryRepository.findByName(name);
    }

    public BenefitTypeEntity retrieveBenefitTypeEntity(final String name) {
        return benefitTypeRepository.findByName(name);
    }

    public RegistrationStatusEntity retrieveRegistrationStatusEntity(final String name) {
        return registrationStatusRepository.findByName(name);
    }

    public CitizenStatusEntity retrieveCitizenStatusEntity(final String name) {
        return citizenStatusRepository.findByName(name);
    }

    public GenderTypeRepository getGenderTypeRepository() {
        return genderTypeRepository;
    }

    public NationalityRepository getNationalityRepository() {
        return nationalityRepository;
    }

    public ContactDetailTypeRepository getContactDetailTypeRepository() {
        return contactDetailTypeRepository;
    }

    public AddressTypeRepository getAddressTypeRepository() {
        return addressTypeRepository;
    }

    public CountryRepository getCountryRepository() {
        return countryRepository;
    }
}
