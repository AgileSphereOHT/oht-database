package uk.doh.oht.database.model;

import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by peterwhitehead on 06/05/2017.
 */
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "contact_detail")
public class ContactDetailEntity {
    private long contactDetailId;
    private String contact;
    private ContactDetailTypeEntity contactDetailTypeEntity;
    private CitizenEntity citizenEntity;

    @Id
    @Column(name = "contact_detail_id", nullable = false)
    public long getContactDetailId() {
        return contactDetailId;
    }

    @Basic
    @Column(name = "contact", nullable = true, length = 255)
    public String getContact() {
        return contact;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_detail_type_id")
    public ContactDetailTypeEntity getContactDetailTypeEntity() {
        return this.contactDetailTypeEntity;
    }

    @ManyToOne
    @JoinTable(name = "citizen_contact_detail", joinColumns = { @JoinColumn(name = "contact_detail_id") }, inverseJoinColumns = { @JoinColumn(name = "citizen_id") })
    public CitizenEntity getCitizenEntity() {
        return citizenEntity;
    }
}
