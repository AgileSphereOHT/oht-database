package uk.doh.oht.database.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

/**
 * Created by peterwhitehead on 05/05/2017.
 */
@Builder
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registration")
public class RegistrationEntity {
    private long registrationId;
    private String requestedBy;
    private String issueType;
    private Date entitlementDate;
    private Date startDate;
    private Date endDate;
    private Date s073StartDate;
    private BenefitTypeEntity benefitTypeEntity;
    private CitizenStatusEntity citizenStatusEntity;
    private CitizenEntity citizenEntity;
    private RegistrationStatusEntity registrationStatusEntity;
    private CountryEntity countryEntity;
    private String caseId;
    private String lastUpdatedBy;
    private Timestamp lastUpdatedDate;
    private String createdBy;
    private Timestamp creationDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Basic
    @Column(name = "s073_start_date", nullable = true)
    public Date getS073StartDate() {
        return s073StartDate;
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

    @ManyToOne(cascade = CascadeType.ALL)
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

    @Basic
    @Column(name = "created_by", nullable = true, length = 255)
    public String getCreatedBy() {
        return createdBy;
    }

    @Basic
    @Column(name = "creation_date", nullable = true)
    public Timestamp getCreationDate() {
        return creationDate;
    }

    @Basic
    @Column(name = "last_updated_by", nullable = true, length = 255)
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    @Basic
    @Column(name = "last_updated_date", nullable = true)
    public Timestamp getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    @Transient
    public String getCaseId() {
        return caseId;
    }
}
