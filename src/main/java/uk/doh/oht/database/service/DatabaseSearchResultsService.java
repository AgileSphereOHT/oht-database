package uk.doh.oht.database.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.doh.oht.database.domain.*;
import uk.doh.oht.database.model.*;
import uk.doh.oht.database.repos.PendingRegistrationRepository;
import uk.doh.oht.database.repos.RegistrationRepository;
import uk.doh.oht.database.repos.RegistrationStatusRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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

    public List<RegistrationData> searchCases(final List<SearchData> searchDataList) {
        List<RegistrationEntity> registrationEntityList = new ArrayList<>();
        for (final SearchData searchData : searchDataList) {
            createRegistrationEntity(searchData, registrationEntityList);
        }
        return entityResultConverter.convertRegistrationEntity(registrationEntityList);
    }

    public List<PendingRegistrationData> getPendingRegistrations() {
        final RegistrationStatusEntity registrationStatusEntity = registrationStatusRepository.findByName(PENDING);
        return entityResultConverter.convertPendingRegistrationEntity(pendingRegistrationRepository.findByRegistrationStatusEntity(registrationStatusEntity));
    }

    private void createRegistrationEntity(final SearchData searchData,
                                          final List<RegistrationEntity> registrationEntityList) {
        RegistrationEntity registrationEntity = registrationRepository.findByCitizenEntityNinoInIgnoreCase(searchData.getNino());
        if (registrationEntity == null) {
            registrationEntity = findSearchDataByOtherCriteria(searchData);
        }
        if (registrationEntity != null) {
            registrationEntity.setCaseId(searchData.getCaseId());
            registrationEntityList.add(registrationEntity);
        }
    }

    private  RegistrationEntity findSearchDataByOtherCriteria(final SearchData searchData) {
        return registrationRepository.findByCitizenEntityFirstNameLikeIgnoreCaseAndCitizenEntityLastNameLikeIgnoreCaseAndCitizenEntityDateOfBirth(
                    searchData.getFirstName(),
                    searchData.getLastName(),
                    searchData.getDateOfBirth());
    }
}
