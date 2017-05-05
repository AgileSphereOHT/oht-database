package uk.doh.oht.database.model;

import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by peterwhitehead on 05/05/2017.
 */
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "citizen", schema = "public", catalog = "oht_database")
public class CitizenEntity {
    private long citizenId;
    private String title;
    private String firstName;
    private String otherName;
    private String lastName;
    private String maidenName;
    private Date dateOfBirth;
    private String nino;
    private String telephoneNumber;
    private String emailAddress;
    private Timestamp creationDate;
    private Timestamp lastUpdatedDate;
    private List<AddressEntity> addressEntityList;
    private GenderEntity genderEntity;
    private NationalityEntity nationalityEntity;

    @Id
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

    @Basic
    @Column(name = "telephone_number", nullable = true, length = 255)
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    @Basic
    @Column(name = "email_address", nullable = true, length = 255)
    public String getEmailAddress() {
        return emailAddress;
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "citizen_address", joinColumns = { @JoinColumn(name = "citizen_id") }, inverseJoinColumns = { @JoinColumn(name = "address_id") })
    public List<AddressEntity> getAddressEntityList() {
        return this.addressEntityList;
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
}
