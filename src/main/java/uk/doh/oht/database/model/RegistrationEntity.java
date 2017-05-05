package uk.doh.oht.database.model;

import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by peterwhitehead on 05/05/2017.
 */
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "registration", schema = "public", catalog = "oht_database")
public class RegistrationEntity {
    private long registrationId;
    private String requestedBy;
    private String issueType;
    private Date entitlementDate;
    private Date startDate;
    private Date endDate;
    private BenefitTypeEntity benefitTypeEntity;
    private CitizenStatusEntity citizenStatusEntity;
    private CitizenEntity citizenEntity;
    private RegistrationStatusEntity registrationStatusEntity;
    private CountryEntity countryEntity;

    @Id
    @Column(name = "registration_id", nullable = false)
    public long getRegistrationId() {
        return registrationId;
    }

    @Basic
    @Column(name = "requested_by", nullable = true, length = 255)
    public String getRequestedBy() {
        return requestedBy;
    }

    @Basic
    @Column(name = "issue_type", nullable = true, length = 255)
    public String getIssueType() {
        return issueType;
    }

    @Basic
    @Column(name = "entitlement_date", nullable = true)
    public Date getEntitlementDate() {
        return entitlementDate;
    }

    @Basic
    @Column(name = "start_date", nullable = true)
    public Date getStartDate() {
        return startDate;
    }

    @Basic
    @Column(name = "end_date", nullable = true)
    public Date getEndDate() {
        return endDate;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "citizen_status_id")
    public CitizenStatusEntity getCitizenStatusEntity() {
        return this.citizenStatusEntity;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "benefit_type_id")
    public BenefitTypeEntity getBenefitTypeEntity() {
        return this.benefitTypeEntity;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "citizen_id")
    public CitizenEntity getCitizenEntity() {
        return this.citizenEntity;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    public CountryEntity getCountryEntity() {
        return this.countryEntity;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "registration_status_id")
    public RegistrationStatusEntity getRegistrationStatusEntity() {
        return this.registrationStatusEntity;
    }
}
