package uk.doh.oht.database.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import uk.doh.oht.database.validation.StartDateFormDate;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by peterwhitehead on 10/05/2017.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PendingRegistrationData implements Serializable {
    private long pendingRegistrationId;
    private String title;
    private String firstName;
    private String otherName;
    private String lastName;
    private String maidenName;
    private Date dateOfBirth;
    private String gender;
    private String nationality;
    private String nino;
    private String telephoneNumber;
    private String emailAddress;
    private String currentLineOne;
    private String currentLineTwo;
    private String currentLineThree;
    private String currentLineFour;
    private String currentLineFive;
    private String currentLineSix;
    private String currentCountry;
    private String currentPostcode;
    private String movingLineOne;
    private String movingLineTwo;
    private String movingLineThree;
    private String movingLineFour;
    private String movingLineFive;
    private String movingLineSix;
    private String movingCountry;
    private String movingPostcode;
    private Date movingDate;
    private String benefitType;
    private String issueType;
    private String registrationStatus;
    private String country;
    private Date entitlementDate;
    private String hasForeignPension;
    private String occupationType;
    private String requestedBy;
    private Date creationDate;
    private Date lastUpdatedDate;
    private String caseId;
    private StartDateFormDate startDate;
    private String modifiedByUserId;
}
