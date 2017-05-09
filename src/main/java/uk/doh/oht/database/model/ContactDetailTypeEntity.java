package uk.doh.oht.database.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by peterwhitehead on 06/05/2017.
 */
@Builder
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contact_detail_type")
public class ContactDetailTypeEntity {
    private long contactDetailTypeId;
    private String name;
    private String description;

    @Id
    @Column(name = "contact_detail_type_id", nullable = false)
    public long getContactDetailTypeId() {
        return contactDetailTypeId;
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
