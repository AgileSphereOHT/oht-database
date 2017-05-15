package uk.doh.oht.database.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.doh.oht.database.domain.*;
import uk.doh.oht.database.model.*;
import uk.doh.oht.database.repos.PendingRegistrationRepository;
import uk.doh.oht.database.repos.RegistrationRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by peterwhitehead on 04/05/2017.
 */
@Slf4j
@Service
public class DatabaseSearchResultsService {
    private final PendingRegistrationRepository pendingRegistrationRepository;
    private final RegistrationRepository registrationRepository;
    private final EntityResultConverter entityResultConverter;
    private final EntityRepositoryHelper entityRepositoryHelper;

    @Inject
    public DatabaseSearchResultsService(final PendingRegistrationRepository pendingRegistrationRepository,
                                        final RegistrationRepository registrationRepository,
                                        final EntityResultConverter entityResultConverter,
                                        final EntityRepositoryHelper entityRepositoryHelper) {
        this.pendingRegistrationRepository = pendingRegistrationRepository;
        this.registrationRepository = registrationRepository;
        this.entityResultConverter = entityResultConverter;
        this.entityRepositoryHelper = entityRepositoryHelper;
    }

    public List<RegistrationData> searchCases(final List<SearchData> searchDataList) {
        final List<RegistrationEntity> registrationEntityList = new ArrayList<>();
        for (final SearchData searchData : searchDataList) {
            createRegistrationEntity(searchData, registrationEntityList, entityRepositoryHelper.getPendingDelayedStatuses());
        }
        return entityResultConverter.convertRegistrationEntity(registrationEntityList);
    }

    public List<PendingRegistrationData> getPendingRegistrations() {
        return entityResultConverter.convertPendingRegistrationEntity(
                pendingRegistrationRepository.findByRegistrationStatusEntity(entityRepositoryHelper.getPendingStatus()));
    }

    private void createRegistrationEntity(final SearchData searchData,
                                          final List<RegistrationEntity> registrationEntityList,
                                          final List<RegistrationStatusEntity> statuses) {
        RegistrationEntity registrationEntity =
                registrationRepository.findByCitizenEntityNinoIgnoreCaseAndRegistrationStatusEntityIn(searchData.getNino(), statuses);
        if (registrationEntity == null) {
            registrationEntity = findSearchDataByOtherCriteria(searchData, statuses);
        }
        if (registrationEntity != null) {
            registrationEntity.setCaseId(searchData.getCaseId());
            registrationEntityList.add(registrationEntity);
        }
    }

    private  RegistrationEntity findSearchDataByOtherCriteria(final SearchData searchData,
                                                              final List<RegistrationStatusEntity> statuses) {
        return registrationRepository.findByCitizenEntityFirstNameLikeIgnoreCaseAndCitizenEntityLastNameLikeIgnoreCaseAndCitizenEntityDateOfBirthAndRegistrationStatusEntityIn(
                searchData.getFirstName(),
                searchData.getLastName(),
                searchData.getDateOfBirth(),
                statuses);
    }

    public UserWorkDetails getUserUsage(final String userName) {
        Long countRegistrations =
                registrationRepository.findCountOfCompletedByLastUpdateByAndLastUpdateDateAndRegistrationStatusId(
                        userName, entityRepositoryHelper.getCompletedStatus().getRegistrationStatusId());
        Long countRejections =
                registrationRepository.findCountOfRejectedByLastUpdateByAndLastUpdateDateAndRegistrationStatusId(
                        userName, entityRepositoryHelper.getRejectedStatus().getRegistrationStatusId());
        Long countS1Requests =
                pendingRegistrationRepository.findCountOfS1RequestsByLastUpdateByAndLastUpdateDateAndRegistrationStatusId(
                        userName, entityRepositoryHelper.getPendingStatus().getRegistrationStatusId());
        return UserWorkDetails.builder()
                .numberRegistrations(countRegistrations)
                .numberCancellations(countRejections)
                .numberRequests(countS1Requests)
                .build();
    }
}
