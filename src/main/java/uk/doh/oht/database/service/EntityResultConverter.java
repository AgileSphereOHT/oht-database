package uk.doh.oht.database.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import uk.doh.oht.db.domain.*;
import uk.doh.oht.database.model.*;
import uk.doh.oht.validation.StartDateFormDate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by peterwhitehead on 06/05/2017.
 */
@Slf4j
@Component
public class EntityResultConverter {
    public List<RegistrationData> convertRegistrationEntity(final Iterable<RegistrationEntity> registrationEntities) {
        try {
            log.debug("Enter convertRegistrationEntity");
            final List<RegistrationData> registrationDataList = new ArrayList<>();
            if (registrationEntities == null) {
                return registrationDataList;
            }
            final Iterator<RegistrationEntity> results = registrationEntities.iterator();
            while (results.hasNext()) {
                final RegistrationEntity registrationEntity = results.next();
                registrationDataList.add(new RegistrationData(
                        registrationEntity.getRegistrationId(),
                        createUserDetails(registrationEntity),
                        createAddresses(registrationEntity),
                        registrationEntity.getBenefitTypeEntity().getName(),
                        registrationEntity.getIssueType(),
                        registrationEntity.getRegistrationStatusEntity().getName(),
                        registrationEntity.getCountryEntity().getDescription(),
                        registrationEntity.getEntitlementDate(),
                        new StartDateFormDate(registrationEntity.getStartDate()),
                        null,
                        registrationEntity.getRequestedBy(),
                        null,
                        null,
                        registrationEntity.getCaseId(),
                        null)
                );
            }
            return registrationDataList;
        } finally {
            log.debug("Exit convertRegistrationEntity");
        }
    }

    public List<PendingRegistrationData> convertPendingRegistrationEntity(final List<PendingRegistrationEntity> pendingRegistrations) {
        try {
            log.debug("Enter convertPendingRegistrationEntity");
            final List<PendingRegistrationData> pendingRegistrationList = new ArrayList<>();
            if (CollectionUtils.isEmpty(pendingRegistrations)) {
                return pendingRegistrationList;
            }
            for (final PendingRegistrationEntity pendingRegistration : pendingRegistrations) {
                pendingRegistrationList.add(createPendingRegistration(pendingRegistration));
            }
            return pendingRegistrationList;
        } finally {
            log.debug("Exit convertPendingRegistrationEntity");
        }
    }

    private PendingRegistrationData createPendingRegistration(final PendingRegistrationEntity pendingRegistration) {
        try {
            log.debug("Enter createPendingRegistration");
            return new PendingRegistrationData(
                    pendingRegistration.getPendingRegistrationId(),
                    pendingRegistration.getTitle(),
                    pendingRegistration.getFirstName(),
                    pendingRegistration.getOtherName(),
                    pendingRegistration.getLastName(),
                    pendingRegistration.getMaidenName(),
                    pendingRegistration.getDateOfBirth(),
                    pendingRegistration.getGenderEntity().getName(),
                    pendingRegistration.getNationalityEntity().getName(),
                    pendingRegistration.getNino(),
                    pendingRegistration.getTelephoneNumber(),
                    pendingRegistration.getEmailAddress(),
                    pendingRegistration.getCurrentLineOne(),
                    pendingRegistration.getCurrentLineTwo(),
                    pendingRegistration.getCurrentLineThree(),
                    pendingRegistration.getCurrentLineFour(),
                    pendingRegistration.getCurrentLineFive(),
                    pendingRegistration.getCurrentLineSix(),
                    pendingRegistration.getCurrentCountryEntity().getDescription(),
                    pendingRegistration.getCurrentPostcode(),
                    pendingRegistration.getMovingLineOne(),
                    pendingRegistration.getMovingLineTwo(),
                    pendingRegistration.getMovingLineThree(),
                    pendingRegistration.getMovingLineFour(),
                    pendingRegistration.getMovingLineFive(),
                    pendingRegistration.getMovingLineSix(),
                    pendingRegistration.getMovingCountryEntity().getDescription(),
                    pendingRegistration.getMovingPostcode(),
                    pendingRegistration.getMovingDate(),
                    pendingRegistration.getBenefitTypeEntity().getName(),
                    pendingRegistration.getIssueType(),
                    pendingRegistration.getRegistrationStatusEntity().getName(),
                    pendingRegistration.getCountryEntity().getDescription(),
                    pendingRegistration.getEntitlementDate(),
                    pendingRegistration.getHasForeignPension(),
                    pendingRegistration.getOccupationTypeEntity().getName(),
                    pendingRegistration.getRequestedBy(),
                    pendingRegistration.getCaseId(),
                    null,
                    pendingRegistration.getLastUpdatedBy()
            );
        } finally {
            log.debug("Exit createPendingRegistration");
        }
    }

    private UserDetails createUserDetails(final RegistrationEntity registrationEntity) {
        try {
            log.debug("Enter createUserDetails");
            final CitizenEntity citizenEntity = registrationEntity.getCitizenEntity();
            return new UserDetails(
                    citizenEntity.getNino(),
                    citizenEntity.getTitle(),
                    citizenEntity.getFirstName(),
                    citizenEntity.getOtherName(),
                    citizenEntity.getLastName(),
                    citizenEntity.getMaidenName(),
                    citizenEntity.getDateOfBirth(),
                    citizenEntity.getGenderEntity().getName(),
                    citizenEntity.getNationalityEntity().getName(),
                    createContactDetailsList(citizenEntity.getContactDetailEntityList()));
        } finally {
            log.debug("Exit createUserDetails");
        }
    }

    private List<ContactDetail> createContactDetailsList(final List<ContactDetailEntity> contactDetailEntityList) {
        try {
            log.debug("Enter createContactDetailsList");
            return contactDetailEntityList.stream().map(contactDetailEntity ->
                    new ContactDetail(
                            contactDetailEntity.getContactDetailTypeEntity().getName(),
                            contactDetailEntity.getContact()
                    )
            ).collect(toList());
        } finally {
            log.debug("Exit createContactDetailsList");
        }
    }

    private List<Address> createAddresses(final RegistrationEntity registrationEntity) {
        try {
            log.debug("Enter createAddresses");
            return registrationEntity.getCitizenEntity().getAddressEntityList().stream().map(addressEntity ->
                    new Address(
                            addressEntity.getAddressTypeEntity().getName(), addressEntity.getLineOne(), addressEntity.getLineTwo(),
                            addressEntity.getLineThree(), addressEntity.getLineFour(),
                            addressEntity.getLineFive(), addressEntity.getLineSix(),
                            addressEntity.getCountryEntity().getDescription(), addressEntity.getPostcode()
                    )
            ).collect(toList());
        } finally {
            log.debug("Exit createAddresses");
        }
    }
}
