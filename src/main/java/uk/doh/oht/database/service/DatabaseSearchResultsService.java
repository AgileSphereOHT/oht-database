package uk.doh.oht.database.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import uk.doh.oht.database.domain.*;
import uk.doh.oht.database.model.*;
import uk.doh.oht.database.repos.PendingRegistrationRepository;
import uk.doh.oht.database.repos.RegistrationRepository;
import uk.doh.oht.database.repos.RegistrationStatusRepository;

import javax.inject.Inject;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by peterwhitehead on 04/05/2017.
 */
@Slf4j
@Service
public class DatabaseSearchResultsService {
    private static final String PENDING = "Pending";

    private final PendingRegistrationRepository pendingRegistrationRepository;
    private final RegistrationRepository registrationRepository;
    private final RegistrationStatusRepository registrationStatusRepository;
    private final EntityResultConverter entityResultConverter;

    @Inject
    public DatabaseSearchResultsService(final PendingRegistrationRepository pendingRegistrationRepository,
                                        final RegistrationStatusRepository registrationStatusRepository,
                                        final RegistrationRepository registrationRepository,
                                        final EntityResultConverter entityResultConverter) {
        this.pendingRegistrationRepository = pendingRegistrationRepository;
        this.registrationStatusRepository = registrationStatusRepository;
        this.registrationRepository = registrationRepository;
        this.entityResultConverter = entityResultConverter;
    }

    public List<SearchResults> searchCases(final List<SearchData> searchData) {
        final  List<String> ninos = searchData.stream().map(SearchData::getNino).collect(toList());
        final List<RegistrationEntity> foundRecords = registrationRepository.findByCitizenEntityNinoInIgnoreCase(ninos);
        final List<SearchData> changedSearchData = removeFoundNinosFromSearchList(searchData, foundRecords);
        return entityResultConverter.convertRegistrationEntity(findRemainingSearchDataByOtherCriteria(changedSearchData, foundRecords));
    }

    public List<SearchResults> getPendingRegistrations() {
        final RegistrationStatusEntity registrationStatusEntity = registrationStatusRepository.findByName(PENDING);
        return entityResultConverter.convertPendingRegistrationEntity(pendingRegistrationRepository.findByRegistrationStatusEntity(registrationStatusEntity));
    }

    private List<SearchData> removeFoundNinosFromSearchList(final List<SearchData> searchDataList, List<RegistrationEntity> foundNinos) {
        return searchDataList.stream()
                .filter(p -> foundNinos.stream().anyMatch(re -> !p.getNino().equalsIgnoreCase(re.getCitizenEntity().getNino())))
                .collect(toList());
    }

    private  List<RegistrationEntity> findRemainingSearchDataByOtherCriteria(final List<SearchData> searchDataList, List<RegistrationEntity> foundData) {
        for (final SearchData searchData : searchDataList) {
            final List<RegistrationEntity> foundRecords = registrationRepository.findByCitizenEntityFirstNameLikeIgnoreCaseAndCitizenEntityLastNameLikeIgnoreCaseAndCitizenEntityDateOfBirth(
                    searchData.getFirstName(),
                    searchData.getLastName(),
                    searchData.getDateOfBirth()
            );
            if (!CollectionUtils.isEmpty(foundRecords)) {
                foundData.addAll(foundRecords);
            }
        }
        return foundData;
    }
}
