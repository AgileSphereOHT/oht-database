package uk.doh.oht.database.model;

import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by peterwhitehead on 05/05/2017.
 */
@Setter
@EqualsAndHashCode
@Entity
@javax.persistence.Table(name = "pending_registration")
public class PendingRegistrationEntity {
    private long pendingRegistrationId;
    private String title;
    private String firstName;
    private String otherName;
    private String lastName;
    private String maidenName;
    private Date dateOfBirth;
    private GenderEntity genderEntity;
    private NationalityEntity nationalityEntity;
    private String nino;
    private String telephoneNumber;
    private String emailAddress;
    private String currentLineOne;
    private String currentLineTwo;
    private String currentLineThree;
    private String currentLineFour;
    private String currentLineFive;
    private String currentLineSix;
    private CountryEntity currentCountryEntity;
    private String currentPostcode;
    private String movingLineOne;
    private String movingLineTwo;
    private String movingLineThree;
    private String movingLineFour;
    private String movingLineFive;
    private String movingLineSix;
    private CountryEntity movingCountryEntity;
    private String movingPostcode;
    private BenefitTypeEntity benefitTypeEntity;
    private String issueType;
    private RegistrationStatusEntity registrationStatusEntity;
    private CountryEntity countryEntity;
    private Date entitlementDate;
    private String hasForeignPension;
    private String requestedBy;
    private Timestamp creationDate;
    private Timestamp lastUpdatedDate;

    @Id
    @javax.persistence.Column(name = "pending_registration_id", nullable = false)
    public long getPendingRegistrationId() {
        return pendingRegistrationId;
    }

    @Basic
    @javax.persistence.Column(name = "title", nullable = true, length = 255)
    public String getTitle() {
        return title;
    }

    @Basic
    @javax.persistence.Column(name = "first_name", nullable = true, length = 255)
    public String getFirstName() {
        return firstName;
    }

    @Basic
    @javax.persistence.Column(name = "other_name", nullable = true, length = 255)
    public String getOtherName() {
        return otherName;
    }

    @Basic
    @javax.persistence.Column(name = "last_name", nullable = true, length = 255)
    public String getLastName() {
        return lastName;
    }

    @Basic
    @javax.persistence.Column(name = "maiden_name", nullable = true, length = 255)
    public String getMaidenName() {
        return maidenName;
    }

    @Basic
    @javax.persistence.Column(name = "date_of_birth", nullable = true)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gender_id")
    public GenderEntity getGenderEntity() {
        return this.genderEntity;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nationality_id")
    public NationalityEntity getNationalityEntity() {
        return this.nationalityEntity;
    }

    @Basic
    @javax.persistence.Column(name = "nino", nullable = true, length = 255)
    public String getNino() {
        return nino;
    }

    @Basic
    @javax.persistence.Column(name = "telephone_number", nullable = true, length = 255)
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    @Basic
    @javax.persistence.Column(name = "email_address", nullable = true, length = 255)
    public String getEmailAddress() {
        return emailAddress;
    }

    @Basic
    @javax.persistence.Column(name = "current_line_one", nullable = true, length = 255)
    public String getCurrentLineOne() {
        return currentLineOne;
    }

    @Basic
    @javax.persistence.Column(name = "current_line_two", nullable = true, length = 255)
    public String getCurrentLineTwo() {
        return currentLineTwo;
    }

    @Basic
    @javax.persistence.Column(name = "current_line_three", nullable = true, length = 255)
    public String getCurrentLineThree() {
        return currentLineThree;
    }

    @Basic
    @javax.persistence.Column(name = "current_line_four", nullable = true, length = 255)
    public String getCurrentLineFour() {
        return currentLineFour;
    }

    @Basic
    @javax.persistence.Column(name = "current_line_five", nullable = true, length = 255)
    public String getCurrentLineFive() {
        return currentLineFive;
    }

    @Basic
    @javax.persistence.Column(name = "current_line_six", nullable = true, length = 255)
    public String getCurrentLineSix() {
        return currentLineSix;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "country_id", name = "current_country_id")
    public CountryEntity getCurrentCountryEntity() {
        return this.currentCountryEntity;
    }

    @Basic
    @javax.persistence.Column(name = "current_postcode", nullable = true, length = 255)
    public String getCurrentPostcode() {
        return currentPostcode;
    }

    @Basic
    @javax.persistence.Column(name = "moving_line_one", nullable = true, length = 255)
    public String getMovingLineOne() {
        return movingLineOne;
    }

    @Basic
    @javax.persistence.Column(name = "moving_line_two", nullable = true, length = 255)
    public String getMovingLineTwo() {
        return movingLineTwo;
    }

    @Basic
    @javax.persistence.Column(name = "moving_line_three", nullable = true, length = 255)
    public String getMovingLineThree() {
        return movingLineThree;
    }

    @Basic
    @javax.persistence.Column(name = "moving_line_four", nullable = true, length = 255)
    public String getMovingLineFour() {
        return movingLineFour;
    }

    @Basic
    @javax.persistence.Column(name = "moving_line_five", nullable = true, length = 255)
    public String getMovingLineFive() {
        return movingLineFive;
    }

    @Basic
    @javax.persistence.Column(name = "moving_line_six", nullable = true, length = 255)
    public String getMovingLineSix() {
        return movingLineSix;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "country_id", name = "moving_country_id")
    public CountryEntity getMovingCountryEntity() {
        return this.movingCountryEntity;
    }

    @Basic
    @javax.persistence.Column(name = "moving_postcode", nullable = true, length = 255)
    public String getMovingPostcode() {
        return movingPostcode;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "benefit_type_id")
    public BenefitTypeEntity getBenefitTypeEntity() {
        return this.benefitTypeEntity;
    }

    @Basic
    @javax.persistence.Column(name = "issue_type", nullable = true, length = 255)
    public String getIssueType() {
        return issueType;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registration_status_id")
    public RegistrationStatusEntity getRegistrationStatusEntity() {
        return this.registrationStatusEntity;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    public CountryEntity getCountryEntity() {
        return this.countryEntity;
    }

    @Basic
    @javax.persistence.Column(name = "entitlement_date", nullable = true)
    public Date getEntitlementDate() {
        return entitlementDate;
    }

    @Basic
    @javax.persistence.Column(name = "has_foreign_pension", nullable = true, length = 255)
    public String getHasForeignPension() {
        return hasForeignPension;
    }

    @Basic
    @javax.persistence.Column(name = "requested_by", nullable = true, length = 255)
    public String getRequestedBy() {
        return requestedBy;
    }

    @Basic
    @javax.persistence.Column(name = "creation_date", nullable = true)
    public Timestamp getCreationDate() {
        return creationDate;
    }

    @Basic
    @javax.persistence.Column(name = "last_updated_date", nullable = true)
    public Timestamp getLastUpdatedDate() {
        return lastUpdatedDate;
    }
}
