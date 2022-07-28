package com.ziola.recruitmenttask.reservationcontrollertest;

import com.ziola.recruitmenttask.landlords.Landlord;
import com.ziola.recruitmenttask.objectstorent.ObjectToRent;
import com.ziola.recruitmenttask.reservations.Reservation;
import com.ziola.recruitmenttask.reservations.ReservationDAO;
import com.ziola.recruitmenttask.tenants.Tenant;
import com.ziola.recruitmenttask.tenants.TenantDAO;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    ReservationDAO reservationDAO;
    @MockBean
    TenantDAO tenantDAO;

    Tenant johnTenant = new Tenant();


    @Test
    public void should_Return_Reservations_By_ObjectId() throws Exception {

        Mockito.when(reservationDAO.findAllReservationsByObjectId(1L)).thenReturn(createReservationList());

        mockMvc.perform(MockMvcRequestBuilders.get("/objectsReservations/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void should_Return_Reservations_By_TenantName() throws Exception {

        Mockito.when(reservationDAO.findAllReservationsByTenantId(1L)).thenReturn(createReservationList());
        Mockito.when(tenantDAO.findTenantByName("John")).thenReturn(johnTenant);

        johnTenant.setName("John");
        johnTenant.setId(1L);

        mockMvc.perform(MockMvcRequestBuilders.get("/tenantsReservations/John"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    private List<Reservation> createReservationList() {
        List<Reservation> reservationList = new ArrayList<>();



        Reservation reservation1 = Reservation.builder()
                .toDateRent(LocalDate.of(2022, 8, 6))
                .fromDateRent(LocalDate.of(2022, 8, 12))
                .rentCost(BigDecimal.valueOf(100))
                .tenant(johnTenant)
                .landlord(new Landlord())
                .objectToRent(new ObjectToRent())
                .build();

        Reservation reservation2 = Reservation.builder()
                .toDateRent(LocalDate.of(2022, 8, 1))
                .fromDateRent(LocalDate.of(2022, 8, 5))
                .rentCost(BigDecimal.valueOf(100))
                .tenant(johnTenant)
                .landlord(new Landlord())
                .objectToRent(new ObjectToRent())
                .build();

        reservationList.add(reservation1);
        reservationList.add(reservation2);

        return reservationList;
    }
}
