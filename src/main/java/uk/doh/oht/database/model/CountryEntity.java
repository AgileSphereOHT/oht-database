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
@Table(name = "country", schema = "public", catalog = "oht_database")
public class CountryEntity {
    private long countryId;
    private String name;
    private String description;

    @Id
    @Column(name = "country_id", nullable = false)
    public long getCountryId() {
        return countryId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }
}
