package uk.doh.oht.database.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by peterwhitehead on 05/05/2017.
 */
@Builder
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "citizen_address")
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
