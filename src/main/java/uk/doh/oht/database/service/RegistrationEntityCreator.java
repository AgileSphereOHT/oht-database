package uk.doh.oht.database.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.doh.oht.db.domain.PendingRegistrationData;
import uk.doh.oht.database.model.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by peterwhitehead on 14/05/2017.
 */
@Slf4j
@Component
public class RegistrationEntityCreator {
    private final EntityRepositoryHelper entityRepositoryHelper;

    public RegistrationEntityCreator(final EntityRepositoryHelper entityRepositoryHelper) {
        this.entityRepositoryHelper = entityRepositoryHelper;
    }

    public RegistrationEntity createRegistrationEntity(final PendingRegistrationData pendingRegistrationData) {
        try {
            log.debug("Enter createRegistrationEntity");
            final Timestamp today = new Timestamp(new Date().getTime());
            return RegistrationEntity.builder()
                    .registrationStatusEntity(entityRepositoryHelper.getPendingStatus())
                    .citizenEntity(createCitizenEntity(pendingRegistrationData, today))
                    .countryEntity(entityRepositoryHelper.retrieveCountryEntity(pendingRegistrationData.getCountry()))
                    .benefitTypeEntity(entityRepositoryHelper.retrieveBenefitTypeEntity(pendingRegistrationData.getBenefitType()))
                    .citizenStatusEntity(entityRepositoryHelper.retrieveCitizenStatusEntity(pendingRegistrationData.getOccupationType()))
                    .entitlementDate(pendingRegistrationData.getEntitlementDate())
                    .issueType(pendingRegistrationData.getIssueType())
                    .lastUpdatedBy(pendingRegistrationData.getModifiedByUserId())
                    .lastUpdatedDate(today)
                    .createdBy(pendingRegistrationData.getModifiedByUserId())
                    .creationDate(today)
                    .requestedBy(pendingRegistrationData.getRequestedBy())
                    .startDate(pendingRegistrationData.getStartDate().getDate())
                    .build();
        } finally {
            log.debug("Exit createRegistrationEntity");
        }
    }

    public CitizenEntity createCitizenEntity(final PendingRegistrationData pendingRegistrationData, final Timestamp today) {
        try {
            log.debug("Enter createCitizenEntity");
            final CitizenEntity citizenEntity = CitizenEntity.builder()
                    .creationDate(today)
                    .createdBy(pendingRegistrationData.getModifiedByUserId())
                    .dateOfBirth(pendingRegistrationData.getDateOfBirth())
                    .firstName(pendingRegistrationData.getFirstName())
                    .genderEntity(entityRepositoryHelper.getGenderTypeRepository().findByName(pendingRegistrationData.getGender()))
                    .lastName(pendingRegistrationData.getLastName())
                    .lastUpdatedDate(today)
                    .lastUpdatedBy(pendingRegistrationData.getModifiedByUserId())
                    .maidenName(pendingRegistrationData.getMaidenName())
                    .nationalityEntity(entityRepositoryHelper.getNationalityRepository().findByName(pendingRegistrationData.getNationality()))
                    .nino(pendingRegistrationData.getNino())
                    .otherName(pendingRegistrationData.getOtherName())
                    .title(pendingRegistrationData.getTitle())
                    .build();
            citizenEntity.setContactDetailEntityList(createContactDetails(pendingRegistrationData, citizenEntity));
            citizenEntity.setAddressEntityList(createAddressList(pendingRegistrationData, citizenEntity, today));
            return citizenEntity;
        } finally {
            log.debug("Exit createCitizenEntity");
        }
    }

    private List<ContactDetailEntity> createContactDetails(final PendingRegistrationData pendingRegistrationData,
                                                           final CitizenEntity citizenEntity) {
        try {
            log.debug("Enter createContactDetails");
            final List<ContactDetailEntity> contactDetails = new ArrayList<>();
            contactDetails.add(ContactDetailEntity.builder()
                    .contact(pendingRegistrationData.getTelephoneNumber())
                    .contactDetailTypeEntity(entityRepositoryHelper.getContactDetailTypeRepository().findByName("Mobile"))
                    .citizenEntity(citizenEntity)
                    .build()
            );
            contactDetails.add(ContactDetailEntity.builder()
                    .contact(pendingRegistrationData.getEmailAddress())
                    .contactDetailTypeEntity(entityRepositoryHelper.getContactDetailTypeRepository().findByName("Email"))
                    .citizenEntity(citizenEntity)
                    .build()
            );
            return contactDetails;
        } finally {
            log.debug("Exit createContactDetails");
        }
    }

    private List<AddressEntity> createAddressList(final PendingRegistrationData pendingRegistrationData,
                                                  final CitizenEntity citizenEntity, final Timestamp today) {
        try {
            log.debug("Enter createAddressList");
            final List<AddressEntity> addressDetails = new ArrayList<>();
            addressDetails.add(AddressEntity.builder()
                    .addressTypeEntity(entityRepositoryHelper.getAddressTypeRepository().findByName("Foreign"))
                    .citizenEntity(citizenEntity)
                    .countryEntity(entityRepositoryHelper.getCountryRepository().findByDescription(pendingRegistrationData.getMovingCountry()))
                    .createdBy(pendingRegistrationData.getModifiedByUserId())
                    .creationDate(today)
                    .lastUpdatedBy(pendingRegistrationData.getModifiedByUserId())
                    .lastUpdatedDate(today)
                    .lineFive(pendingRegistrationData.getMovingLineFive())
                    .lineFour(pendingRegistrationData.getMovingLineFour())
                    .lineOne(pendingRegistrationData.getMovingLineOne())
                    .lineSix(pendingRegistrationData.getMovingLineSix())
                    .lineThree(pendingRegistrationData.getMovingLineThree())
                    .lineTwo(pendingRegistrationData.getMovingLineTwo())
                    .postcode(pendingRegistrationData.getMovingPostcode())
                    .startDate(today)
                    .build()
            );
            addressDetails.add(AddressEntity.builder()
                    .addressTypeEntity(entityRepositoryHelper.getAddressTypeRepository().findByName("National"))
                    .citizenEntity(citizenEntity)
                    .countryEntity(entityRepositoryHelper.getCountryRepository().findByDescription(pendingRegistrationData.getCurrentCountry()))
                    .createdBy(pendingRegistrationData.getModifiedByUserId())
                    .creationDate(today)
                    .lastUpdatedBy(pendingRegistrationData.getModifiedByUserId())
                    .lastUpdatedDate(today)
                    .lineFive(pendingRegistrationData.getCurrentLineFive())
                    .lineFour(pendingRegistrationData.getCurrentLineFour())
                    .lineOne(pendingRegistrationData.getCurrentLineOne())
                    .lineSix(pendingRegistrationData.getCurrentLineSix())
                    .lineThree(pendingRegistrationData.getCurrentLineThree())
                    .lineTwo(pendingRegistrationData.getCurrentLineTwo())
                    .postcode(pendingRegistrationData.getCurrentPostcode())
                    .startDate(today)
                    .build()
            );
            return addressDetails;
        } finally {
            log.debug("Exit createAddressList");
        }
    }
}
