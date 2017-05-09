package uk.doh.oht.database.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import uk.doh.oht.database.domain.Address;
import uk.doh.oht.database.domain.ContactDetail;
import uk.doh.oht.database.domain.RegistrationData;
import uk.doh.oht.database.domain.UserDetails;
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
    private static final String NATIONAL = "National";
    private static final String FOREIGN = "Foreign";
    private static final String MOBILE = "Mobile";
    private static final String EMAIL = "Email";

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

    public List<RegistrationData> convertPendingRegistrationEntity(List<PendingRegistrationEntity> pendingRegistrations) {
        final List<RegistrationData> registrationDataList = new ArrayList<>();
        if (CollectionUtils.isEmpty(pendingRegistrations)) {
            return registrationDataList;
        }
        for (final PendingRegistrationEntity pendingRegistration : pendingRegistrations) {
            registrationDataList.add(new RegistrationData(
                    pendingRegistration.getPendingRegistrationId(),
                    createUserDetails(pendingRegistration),
                    createAddresses(pendingRegistration),
                    pendingRegistration.getBenefitTypeEntity().getName(),
                    pendingRegistration.getIssueType(),
                    pendingRegistration.getRegistrationStatusEntity().getName(),
                    pendingRegistration.getCountryEntity().getDescription(),
                    pendingRegistration.getEntitlementDate(),
                    null,
                    pendingRegistration.getHasForeignPension(),
                    pendingRegistration.getRequestedBy(),
                    null,
                    pendingRegistration.getCaseId())
            );
        }
        return registrationDataList;
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

    private UserDetails createUserDetails(final PendingRegistrationEntity pendingRegistration) {
        return new UserDetails(
                pendingRegistration.getNino(),
                pendingRegistration.getTitle(),
                pendingRegistration.getFirstName(),
                pendingRegistration.getOtherName(),
                pendingRegistration.getLastName(),
                pendingRegistration.getMaidenName(),
                pendingRegistration.getDateOfBirth(),
                pendingRegistration.getGenderEntity().getName(),
                pendingRegistration.getNationalityEntity().getName(),
                createContactDetailsList(pendingRegistration));
    }

    private List<ContactDetail> createContactDetailsList(PendingRegistrationEntity pendingRegistration) {
        final List<ContactDetail> contactDetails = new ArrayList<>();
        contactDetails.add(new ContactDetail(MOBILE, pendingRegistration.getTelephoneNumber()));
        contactDetails.add(new ContactDetail(EMAIL, pendingRegistration.getEmailAddress()));
        return contactDetails;
    }

    private List<Address> createAddresses(final PendingRegistrationEntity pendingRegistration) {
        final List<Address> addressList = new ArrayList<>();
        addressList.add(new Address(NATIONAL, pendingRegistration.getCurrentLineOne(), pendingRegistration.getCurrentLineTwo(),
                pendingRegistration.getCurrentLineThree(), pendingRegistration.getCurrentLineFour(),
                pendingRegistration.getCurrentLineFive(), pendingRegistration.getCurrentLineSix(),
                pendingRegistration.getCurrentCountryEntity().getDescription(), pendingRegistration.getCurrentPostcode()
        ));
        addressList.add(new Address(FOREIGN, pendingRegistration.getMovingLineOne(), pendingRegistration.getMovingLineTwo(),
                pendingRegistration.getMovingLineThree(), pendingRegistration.getMovingLineFour(),
                pendingRegistration.getMovingLineFive(), pendingRegistration.getMovingLineSix(),
                pendingRegistration.getMovingCountryEntity().getDescription(), pendingRegistration.getMovingPostcode()
        ));
        return addressList;
    }
}
