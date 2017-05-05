package uk.doh.oht.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by peterwhitehead on 04/05/2017.
 */
@Data
@AllArgsConstructor
public class SearchResults {
    private UserDetails userDetails;
    private List<Address> addresses;
    private String benefitType;
    private String issueType;
    private String registrationStatus;
    private String country;
    private String entitlementSate;
    private String hasForeignPension;
}
