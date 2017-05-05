package uk.doh.oht.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by peterwhitehead on 05/05/2017.
 */
@Data
@AllArgsConstructor
public class ContactDetails {
    private String type;
    private String details;
}
