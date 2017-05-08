package uk.doh.oht.database.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * Created by peterwhitehead on 05/05/2017.
 */
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchData {
    private String nino;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String postcode;
}
