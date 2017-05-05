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
@Table(name = "citizen_status", schema = "public", catalog = "oht_database")
public class CitizenStatusEntity {
    private long citizenStatusId;
    private String name;
    private String description;
    private CitizenEntity citizenEntity;

    @Id
    @Column(name = "citizen_status_id", nullable = false)
    public long getCitizenStatusId() {
        return citizenStatusId;
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

    @OneToOne(mappedBy = "citizenEntity")
    public CitizenEntity getCitizenEntity() {
        return this.citizenEntity;
    }
}
