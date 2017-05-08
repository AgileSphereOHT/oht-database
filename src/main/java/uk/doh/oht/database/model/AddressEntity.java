package uk.doh.oht.database.model;

import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by peterwhitehead on 05/05/2017.
 */
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "address")
public class AddressEntity {
    private long addressId;
    private String lineOne;
    private String lineTwo;
    private String lineThree;
    private String lineFour;
    private String lineFive;
    private String lineSix;
    private String postcode;
    private String correspondenceAddress;
    private Timestamp startDate;
    private Timestamp endDate;
    private Timestamp creationDate;
    private Timestamp lastUpdatedDate;
    private AddressTypeEntity addressTypeEntity;
    private CountryEntity countryEntity;
    private CitizenEntity citizenEntity;

    @Id
    @Column(name = "address_id", nullable = false)
    public long getAddressId() {
        return addressId;
    }

    @Basic
    @Column(name = "line_one", nullable = true, length = 255)
    public String getLineOne() {
        return lineOne;
    }

    @Basic
    @Column(name = "line_two", nullable = true, length = 255)
    public String getLineTwo() {
        return lineTwo;
    }

    @Basic
    @Column(name = "line_three", nullable = true, length = 255)
    public String getLineThree() {
        return lineThree;
    }

    @Basic
    @Column(name = "line_four", nullable = true, length = 255)
    public String getLineFour() {
        return lineFour;
    }

    @Basic
    @Column(name = "line_five", nullable = true, length = 255)
    public String getLineFive() {
        return lineFive;
    }

    @Basic
    @Column(name = "line_six", nullable = true, length = 255)
    public String getLineSix() {
        return lineSix;
    }

    @Basic
    @Column(name = "postcode", nullable = true, length = 255)
    public String getPostcode() {
        return postcode;
    }

    @Basic
    @Column(name = "correspondence_address", nullable = true, length = 255)
    public String getCorrespondenceAddress() {
        return correspondenceAddress;
    }

    @Basic
    @Column(name = "start_date", nullable = true)
    public Timestamp getStartDate() {
        return startDate;
    }

    @Basic
    @Column(name = "end_date", nullable = true)
    public Timestamp getEndDate() {
        return endDate;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_type_id")
    public AddressTypeEntity getAddressTypeEntity() {
        return this.addressTypeEntity;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    public CountryEntity getCountryEntity() {
        return this.countryEntity;
    }

    @ManyToOne
    @JoinTable(name = "citizen_address", joinColumns = { @JoinColumn(name = "address_id") }, inverseJoinColumns = { @JoinColumn(name = "citizen_id") })
    public CitizenEntity getCitizenEntity() {
        return citizenEntity;
    }
}
