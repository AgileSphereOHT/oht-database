package uk.doh.oht.database.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import uk.doh.oht.database.domain.*;
import uk.doh.oht.database.model.*;
import uk.doh.oht.database.validation.StartDateFormDate;

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
    public List<RegistrationData> convertRegistrationEntity(Iterable<RegistrationEntity> registrationEntities) {
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
                registrationEntity.getCaseId())
            );
        }
        return registrationDataList;
    }

    public List<PendingRegistrationData> convertPendingRegistrationEntity(List<PendingRegistrationEntity> pendingRegistrations) {
        final List<PendingRegistrationData> pendingRegistrationList = new ArrayList<>();
        if (CollectionUtils.isEmpty(pendingRegistrations)) {
            return pendingRegistrationList;
        }
        for (final PendingRegistrationEntity pendingRegistration : pendingRegistrations) {
            pendingRegistrationList.add(createPendingRegistration(pendingRegistration));
        }
        return pendingRegistrationList;
    }

    private PendingRegistrationData createPendingRegistration(PendingRegistrationEntity pendingRegistration) {
        return new PendingRegistrationData(
                pendingRegistration.getPendingRegistrationId(),
                pendingRegistration.getTitle(),
                pendingRegistration.getFirstName(),
                pendingRegistration.getOtherName(),
                pendingRegistration.getLastName(),
                pendingRegistration.getMaidenName(),
                pendingRegistration.getDateOfBirth(),
                pendingRegistration.getGenderEntity().getDescription(),
                pendingRegistration.getNationalityEntity().getDescription(),
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
                pendingRegistration.getBenefitTypeEntity().getDescription(),
                pendingRegistration.getIssueType(),
                pendingRegistration.getRegistrationStatusEntity().getDescription(),
                pendingRegistration.getCountryEntity().getName(),
                pendingRegistration.getEntitlementDate(),
                pendingRegistration.getHasForeignPension(),
                pendingRegistration.getOccupationTypeEntity().getDescription(),
                pendingRegistration.getRequestedBy(),
                pendingRegistration.getCreationDate(),
                pendingRegistration.getLastUpdatedDate(),
                pendingRegistration.getCaseId(),
                null
        );
    }

    private UserDetails createUserDetails(final RegistrationEntity registrationEntity) {
        CitizenEntity citizenEntity = registrationEntity.getCitizenEntity();
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
    }

    private List<ContactDetail> createContactDetailsList(List<ContactDetailEntity> contactDetailEntityList) {
        return contactDetailEntityList.stream().map(contactDetailEntity ->
                new ContactDetail(
                    contactDetailEntity.getContactDetailTypeEntity().getName(),
                    contactDetailEntity.getContact()
                )
        ).collect(toList());
    }

    private List<Address> createAddresses(final RegistrationEntity registrationEntity) {
        return registrationEntity.getCitizenEntity().getAddressEntityList().stream().map(addressEntity ->
                new Address(
                    addressEntity.getAddressTypeEntity().getName(), addressEntity.getLineOne(), addressEntity.getLineTwo(),
                    addressEntity.getLineThree(), addressEntity.getLineFour(),
                    addressEntity.getLineFive(), addressEntity.getLineSix(),
                    addressEntity.getCountryEntity().getDescription(), addressEntity.getPostcode()
                )
        ).collect(toList());
    }
}
