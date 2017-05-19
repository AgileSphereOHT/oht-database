package uk.doh.oht.database.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import uk.doh.oht.db.domain.PendingRegistrationData;
import uk.doh.oht.db.domain.RegistrationData;
import uk.doh.oht.database.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by peterwhitehead on 15/05/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class EntityResultConverterTest {
    private EntityResultConverter entityResultConverter;
    private DataCreationUtil dataCreationUtil;

    @Before
    public void setUp() throws Exception {
        entityResultConverter = new EntityResultConverter();
        dataCreationUtil = new DataCreationUtil();
    }

    @Test
    public void testConvertRegistrationEntity() throws Exception {
        final Date today = new Date();
        final List<RegistrationData> registrationDataList1 = new ArrayList<>();
        registrationDataList1.add(dataCreationUtil.createRegistrationData(today));

        final List<RegistrationEntity> registrationEntityList = new ArrayList<>();
        registrationEntityList.add(dataCreationUtil.createRegistrationEntity(today));

        final List<RegistrationData> registrationDataList = entityResultConverter.convertRegistrationEntity(registrationEntityList);
        assertThat(registrationDataList1).hasSameElementsAs(registrationDataList);
    }

    @Test
    public void testConvertPendingRegistrationEntity() throws Exception {
        final Date today = new Date();
        final List<PendingRegistrationData> pendingRegistrationDataList1 = new ArrayList<>();
        pendingRegistrationDataList1.add(dataCreationUtil.createPendingRegistrationData(today));

        final List<PendingRegistrationEntity> pendingRegistrationEntityList = new ArrayList<>();
        pendingRegistrationEntityList.add(dataCreationUtil.createPendingRegistrationEntity(today));

        final List<PendingRegistrationData> pendingRegistrationDataList = entityResultConverter.convertPendingRegistrationEntity(pendingRegistrationEntityList);
        assertThat(pendingRegistrationDataList1).hasSameElementsAs(pendingRegistrationDataList);
    }
}