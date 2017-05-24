package uk.doh.oht.database.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import uk.doh.oht.db.domain.*;
import uk.doh.oht.database.model.*;
import uk.doh.oht.database.repos.PendingRegistrationRepository;
import uk.doh.oht.database.repos.RegistrationRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.stream.Collectors.toList;


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
    private final EntityManager entityManager;

    @Inject
    public DatabaseSearchResultsService(final PendingRegistrationRepository pendingRegistrationRepository,
                                        final RegistrationRepository registrationRepository,
                                        final EntityResultConverter entityResultConverter,
                                        final EntityRepositoryHelper entityRepositoryHelper,
                                        final EntityManager entityManager) {
        this.pendingRegistrationRepository = pendingRegistrationRepository;
        this.registrationRepository = registrationRepository;
        this.entityResultConverter = entityResultConverter;
        this.entityRepositoryHelper = entityRepositoryHelper;
        this.entityManager = entityManager;
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
        Long registrationId = 0l;
        final RegistrationEntity registrationEntity = registrationRepository.findByCitizenEntityNinoIgnoreCaseAndRegistrationStatusEntityIn(searchData.getNino(), statuses);
        if (registrationEntity != null) {
            registrationEntity.setCaseId(searchData.getCaseId());
            registrationEntityList.add(registrationEntity);
            registrationId = registrationEntity.getRegistrationId();
        }
        getPartialMatches(searchData, registrationEntityList, statuses, registrationId);
    }

    private void getPartialMatches(final SearchData searchData,
                                   final List<RegistrationEntity> registrationEntityList,
                                   final List<RegistrationStatusEntity> statuses,
                                   final Long registrationId) {
        final List<RegistrationEntity> partialRegistrationEntityList = findSearchDataByOtherCriteria(searchData, statuses);
        registrationEntityList.addAll(
                partialRegistrationEntityList.stream()
                        .filter(registrationEntity -> { registrationEntity.setCaseId(searchData.getCaseId()); return registrationId != registrationEntity.getRegistrationId(); })
                        .collect(toList()));
    }

    private List<RegistrationEntity> findSearchDataByOtherCriteria(final SearchData searchData,
                                                                   final List<RegistrationStatusEntity> statuses) {
        final StringBuilder statement = new StringBuilder().append("SELECT * FROM registration r left outer join citizen c on r.citizen_id = c.citizen_id where ");

        final javax.persistence.Query query = setQueryParameters(entityManager.createNativeQuery(createQueryString(
                statement, searchData, statuses), RegistrationEntity.class), searchData, statuses);

        return query.getResultList();
    }

    public UserWorkDetails getUserUsage(final String userName) {
        final Long countDailyRegistrations =
                registrationRepository.findDailyCountOfCompletedByLastUpdateByAndLastUpdateDateAndRegistrationStatusId(
                        userName, entityRepositoryHelper.getCompletedStatus().getRegistrationStatusId());
        final Long countDailyRejections =
                registrationRepository.findDailyCountOfRejectedByLastUpdateByAndLastUpdateDateAndRegistrationStatusId(
                        userName, entityRepositoryHelper.getRejectedStatus().getRegistrationStatusId());
        final Long countDailyS1Requests =
                pendingRegistrationRepository.findDailyCountOfS1RequestsByLastUpdateByAndLastUpdateDateAndRegistrationStatusId(
                        userName, entityRepositoryHelper.getPendingStatus().getRegistrationStatusId());
        final Long countMonthlyRegistrations =
                registrationRepository.findMonthlyCountOfCompletedByLastUpdateByAndLastUpdateDateAndRegistrationStatusId(
                        userName, entityRepositoryHelper.getCompletedStatus().getRegistrationStatusId());
        final Long countMonthlyRejections =
                registrationRepository.findMonthlyCountOfRejectedByLastUpdateByAndLastUpdateDateAndRegistrationStatusId(
                        userName, entityRepositoryHelper.getRejectedStatus().getRegistrationStatusId());
        final Long countMonthlyS1Requests =
                pendingRegistrationRepository.findMonthlyCountOfS1RequestsByLastUpdateByAndLastUpdateDateAndRegistrationStatusId(
                        userName, entityRepositoryHelper.getPendingStatus().getRegistrationStatusId());
        return UserWorkDetails.builder()
                .numberDailyRegistrations(countDailyRegistrations)
                .numberDailyCancellations(countDailyRejections)
                .numberDailyRequests(countDailyS1Requests)
                .numberMonthlyRegistrations(countMonthlyRegistrations)
                .numberMonthlyCancellations(countMonthlyRejections)
                .numberMonthlyRequests(countMonthlyS1Requests)
                .build();
    }

    public String getCountryDescription(final String countryCode) {
        return entityRepositoryHelper.getCountryRepository().findByName(countryCode).getDescription();
    }

    private String createQueryString(final StringBuilder statement,
                                     final SearchData searchData,
                                     final List<RegistrationStatusEntity> statuses) {
        int count = 1;
        final String and = " and ";
        if (StringUtils.isNotEmpty(searchData.getFirstName())) {
            statement.append("c.first_name % ?").append(count).append(and);
            count++;
        }
        if (StringUtils.isNotEmpty(searchData.getLastName())) {
            statement.append("c.last_name % ?").append(count).append(and);
            count++;
        }
        if (searchData.getDateOfBirth() != null) {
            statement.append("to_char(c.date_of_birth, 'YYYY-MM-DD') % ?").append(count).append(and);
            count++;
        }
        if (!CollectionUtils.isEmpty(statuses)) {
            statement.append("r.registration_status_id in ?").append(count).append(and);
        }
        return StringUtils.removeEnd(statement.toString(), and);
    }

    private Query setQueryParameters(final Query query,
                                     final SearchData searchData,
                                     final List<RegistrationStatusEntity> statuses) {
        int count = 1;
        if (StringUtils.isNotEmpty(searchData.getFirstName())) {
            query.setParameter(count, searchData.getFirstName());
            count++;
        }
        if (StringUtils.isNotEmpty(searchData.getLastName())) {
            query.setParameter(count, searchData.getLastName());
            count++;
        }
        if (searchData.getDateOfBirth() != null) {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            query.setParameter(count, simpleDateFormat.format(searchData.getDateOfBirth()));
            count++;
        }
        if (!CollectionUtils.isEmpty(statuses)) {
            query.setParameter(count, statuses.stream().map(RegistrationStatusEntity::getRegistrationStatusId).collect(toList()));
        }
        return query;
    }
}
