package uk.doh.oht.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by peterwhitehead on 05/05/2017.
 */
@Data
@AllArgsConstructor
public class UserDetails {
    private String nino;
    private String title;
    private String firstName;
    private String otherName;
    private String lastName;
    private String maidenName;
    private String dateOfBirth;
    private String gender;
    private String nationality;
    private List<ContactDetails> contactDetailsList;
}
