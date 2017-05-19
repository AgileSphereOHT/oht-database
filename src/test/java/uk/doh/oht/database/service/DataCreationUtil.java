package uk.doh.oht.database.service;

import uk.doh.oht.db.domain.*;
import uk.doh.oht.database.model.*;
import uk.doh.oht.validation.StartDateFormDate;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by peterwhitehead on 16/05/2017.
 */
public class DataCreationUtil {
    public RegistrationEntity createRegistrationEntity(final Date today) {
        return RegistrationEntity.builder()
            .benefitTypeEntity(
                    BenefitTypeEntity.builder().benefitTypeId(1).description("desc").name("name").build()
            )
            .caseId("1")
            .citizenEntity(createCitizenEntity(today))
            .citizenStatusEntity(
                    CitizenStatusEntity.builder().citizenStatusId(1).description("desc").name("name").build()
            )
            .countryEntity(
                    CountryEntity.builder().countryId(1).description("desc").name("name").build()
            )
            .createdBy("test")
            .creationDate(new Timestamp(today.getTime()))
            .endDate(today)
            .entitlementDate(today)
            .issueType("test")
            .lastUpdatedBy("test")
            .lastUpdatedDate(new Timestamp(today.getTime()))
            .registrationId(1l)
            .registrationStatusEntity(
                    RegistrationStatusEntity.builder().registrationStatusId(1).description("desc").name("name").build()
            )
            .requestedBy("test")
            .s073StartDate(today)
            .startDate(today)
            .build();
    }

    public PendingRegistrationEntity createPendingRegistrationEntity(final Date today) {
        return PendingRegistrationEntity.builder()
                .benefitTypeEntity(
                        BenefitTypeEntity.builder().benefitTypeId(1).description("desc").name("name").build()
                )
                .caseId("1")
                .countryEntity(
                        CountryEntity.builder().countryId(1).description("desc").name("name").build()
                )
                .createdBy("test")
                .creationDate(new Timestamp(today.getTime()))
                .currentCountryEntity(
                        CountryEntity.builder().countryId(1).description("desc").name("name").build()
                )
                .currentLineFive("5")
                .currentLineFour("4")
                .currentLineOne("1")
                .currentLineSix("6")
                .currentLineThree("3")
                .currentLineTwo("2")
                .currentPostcode("PR1 1HB")
                .dateOfBirth(today)
                .emailAddress("be@bt.com")
                .entitlementDate(today)
                .firstName("test")
                .genderEntity(
                        GenderEntity.builder().genderId(1).description("desc").name("name").build()
                )
                .hasForeignPension("no")
                .issueType("test")
                .lastName("test")
                .lastUpdatedBy("test")
                .lastUpdatedDate(new Timestamp(today.getTime()))
                .maidenName("test")
                .movingCountryEntity(
                        CountryEntity.builder().countryId(2).description("desc").name("name").build()
                )
                .movingLineFive("5")
                .movingLineFour("4")
                .movingLineOne("1")
                .movingLineSix("6")
                .movingLineThree("3")
                .movingLineTwo("2")
                .movingPostcode("213421")
                .movingDate(today)
                .nationalityEntity(
                        NationalityEntity.builder().nationalityId(1).description("desc").name("name").build()
                )
                .nino("AA123456A")
                .occupationTypeEntity(
                        OccupationTypeEntity.builder().occupationTypeId(1).description("desc").name("name").build()
                )
                .otherName("test")
                .pendingRegistrationId(1)
                .registrationStatusEntity(
                        RegistrationStatusEntity.builder().registrationStatusId(1).description("desc").name("name").build()
                )
                .requestedBy("test")
                .telephoneNumber("077000000")
                .title("Mr")
                .build();
    }

    public RegistrationData createRegistrationData(final Date today) {
        return RegistrationData.builder()
                .addresses(createAddressList(today))
                .benefitType("name")
                .caseId("1")
                .country("desc")
                .entitlementDate(today)
                .issueType("test")
                .registrationId(1)
                .registrationStatus("name")
                .requestedBy("test")
                .startDate(new StartDateFormDate(today))
                .userDetails(createUserDetails(today))
                .build();
    }

    public PendingRegistrationData createPendingRegistrationData(final Date today) {
        return PendingRegistrationData.builder()
                .benefitType("name")
                .caseId("1")
                .country("desc")
                .currentCountry("desc")
                .currentLineFive("5")
                .currentLineFour("4")
                .currentLineOne("1")
                .currentLineSix("6")
                .currentLineThree("3")
                .currentLineTwo("2")
                .currentPostcode("PR1 1HB")
                .dateOfBirth(today)
                .emailAddress("be@bt.com")
                .entitlementDate(today)
                .firstName("test")
                .gender("name")
                .hasForeignPension("no")
                .issueType("test")
                .lastName("test")
                .modifiedByUserId("test")
                .maidenName("test")
                .movingCountry("desc")
                .movingLineFive("5")
                .movingLineFour("4")
                .movingLineOne("1")
                .movingLineSix("6")
                .movingLineThree("3")
                .movingLineTwo("2")
                .movingPostcode("213421")
                .movingDate(today)
                .nationality("name")
                .nino("AA123456A")
                .occupationType("name")
                .otherName("test")
                .pendingRegistrationId(1)
                .registrationStatus("name")
                .requestedBy("test")
                .telephoneNumber("077000000")
                .title("Mr")
                .build();

    }

    public CitizenEntity createCitizenEntity(final Date today) {
        return CitizenEntity.builder()
                .addressEntityList(createAddressEntityList(today))
                .citizenId(1l)
                .contactDetailEntityList(createContactDetailEntityList())
                .createdBy("test")
                .creationDate(new Timestamp(today.getTime()))
                .dateOfBirth(today)
                .firstName("test")
                .genderEntity(
                        GenderEntity.builder().genderId(1).description("desc").name("name").build()
                )
                .lastName("test")
                .lastUpdatedBy("test")
                .lastUpdatedDate(new Timestamp(today.getTime()))
                .maidenName("test")
                .nationalityEntity(
                        NationalityEntity.builder().nationalityId(1).description("desc").name("name").build()
                )
                .nino("AB123456A")
                .otherName("test")
                .registrationEntity(null)
                .title("Mr")
                .build();
    }

    private List<ContactDetailEntity> createContactDetailEntityList() {
        return Arrays.asList(
                ContactDetailEntity.builder().contact("test").contactDetailId(1).contactDetailTypeEntity(
                        ContactDetailTypeEntity.builder()
                                .contactDetailTypeId(1)
                                .description("desc")
                                .name("name")
                                .build()
                ).build()
        );
    }

    private List<AddressEntity> createAddressEntityList(final Date today) {
        return Arrays.asList(
                AddressEntity.builder().addressId(1)
                        .addressTypeEntity(
                                AddressTypeEntity.builder().addressTypeId(1).description("desc").name("name").build()
                        )
                        .citizenEntity(null)
                        .correspondenceAddress("no")
                        .countryEntity(
                                CountryEntity.builder().countryId(1).description("desc").name("name").build()
                        )
                        .createdBy("test")
                        .creationDate(new Timestamp(today.getTime()))
                        .endDate(new Timestamp(today.getTime()))
                        .lastUpdatedBy("test")
                        .lastUpdatedDate(new Timestamp(today.getTime()))
                        .lineFive("5").lineFour("4").lineThree("3").lineOne("1").lineSix("6").lineTwo("2").postcode("PR1 1HB")
                        .startDate(new Timestamp(today.getTime()))
                        .build()
        );
    }

    private UserDetails createUserDetails(final Date today) {
        return UserDetails.builder()
                .contactDetailList(createContactDetailList(today))
                .dateOfBirth(today)
                .firstName("test")
                .gender("name")
                .lastName("test")
                .maidenName("test")
                .nationality("name")
                .nino("AB123456A")
                .otherName("test")
                .title("Mr")
                .build();
    }

    private List<ContactDetail> createContactDetailList(final Date today) {
        return Arrays.asList(ContactDetail.builder()
                .details("test").type("name").build()
        );
    }

    private List<Address> createAddressList(final Date today) {
        return Arrays.asList(Address.builder()
                .country("desc")
                .lineFive("5").lineFour("4").lineThree("3").lineOne("1").lineSix("6").lineTwo("2").postcode("PR1 1HB")
                .type("name")
                .build()
        );
    }
}
