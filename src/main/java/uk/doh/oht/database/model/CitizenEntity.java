package uk.doh.oht.database.model;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by peterwhitehead on 05/05/2017.
 */
@Builder
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "citizen")
public class CitizenEntity {
    private long citizenId;
    private String title;
    private String firstName;
    private String otherName;
    private String lastName;
    private String maidenName;
    private Date dateOfBirth;
    private String nino;
    private List<ContactDetailEntity> contactDetailEntityList;
    private List<AddressEntity> addressEntityList;
    private GenderEntity genderEntity;
    private NationalityEntity nationalityEntity;
    private List<RegistrationEntity> registrationEntity;
    private String lastUpdatedBy;
    private Timestamp lastUpdatedDate;
    private String createdBy;
    private Timestamp creationDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "citizen_id", nullable = false)
    public long getCitizenId() {
        return citizenId;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 255)
    public String getTitle() {
        return title;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 255)
    public String getFirstName() {
        return firstName;
    }

    @Basic
    @Column(name = "other_name", nullable = true, length = 255)
    public String getOtherName() {
        return otherName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 255)
    public String getLastName() {
        return lastName;
    }

    @Basic
    @Column(name = "maiden_name", nullable = true, length = 255)
    public String getMaidenName() {
        return maidenName;
    }

    @Basic
    @Column(name = "date_of_birth", nullable = true)
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    @Basic
    @Column(name = "nino", nullable = true, length = 255)
    public String getNino() {
        return nino;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "citizen_contact_detail", joinColumns = { @JoinColumn(name = "citizen_id") }, inverseJoinColumns = { @JoinColumn(name = "contact_detail_id") })
    public List<ContactDetailEntity> getContactDetailEntityList() {
        return this.contactDetailEntityList;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "citizen_address", joinColumns = { @JoinColumn(name = "citizen_id") }, inverseJoinColumns = { @JoinColumn(name = "address_id") })
    public List<AddressEntity> getAddressEntityList() {
        return this.addressEntityList;
    }

    @OneToOne
    @JoinColumn(name = "gender_id")
    public GenderEntity getGenderEntity() {
        return this.genderEntity;
    }

    @OneToOne
    @JoinColumn(name = "nationality_id")
    public NationalityEntity getNationalityEntity() {
        return this.nationalityEntity;
    }

    @OneToMany(mappedBy = "citizenEntity")
    @Where(clause = "registration_status_id in (1, 3)")
    public List<RegistrationEntity> getRegistrationEntity() {
        return registrationEntity;
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
    @Column(name = "last_updated_date", nullable = true)
    public Timestamp getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    @Basic
    @Column(name = "last_updated_by", nullable = true, length = 255)
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
}
