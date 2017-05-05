package uk.doh.oht.database.model;

import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by peterwhitehead on 05/05/2017.
 */
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "citizen_address", schema = "public", catalog = "oht_database")
public class CitizenAddressEntity {
    private long citizenAddressId;
    private Long citizenId;
    private Long addressId;

    @Id
    @Column(name = "citizen_address_id", nullable = false)
    public long getCitizenAddressId() {
        return citizenAddressId;
    }

    @Basic
    @Column(name = "citizen_id", nullable = false)
    public Long getCitizenId() {
        return citizenId;
    }

    @Basic
    @Column(name = "address_id", nullable = false)
    public Long getAddressId() {
        return addressId;
    }
}
