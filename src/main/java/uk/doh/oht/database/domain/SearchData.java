package uk.doh.oht.database.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by peterwhitehead on 05/05/2017.
 */
@Data
@AllArgsConstructor
public class SearchData {
    private String nino;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String postcode;
}
