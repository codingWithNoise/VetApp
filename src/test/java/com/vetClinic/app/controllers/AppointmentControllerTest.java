package com.vetClinic.app.controllers;

import com.vetClinic.app.AppointmentServiceException;
import com.vetClinic.app.ClientServiceException;
import com.vetClinic.app.ErrorMessage;
import com.vetClinic.app.domain.Appointment;
import com.vetClinic.app.services.AppointmentService;
import com.vetClinic.app.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentControllerTest {

    private AppointmentService appointmentService;
    private AppointmentController controller;
    private Integer clientId = 1111;
    private String clientPIN = "2222";
    private Appointment appointment1 = new Appointment();
    private Appointment appointment2 = new Appointment();

    @BeforeEach
    void setUp() throws ClientServiceException {
        appointmentService = mock(AppointmentService.class);
        ClientService clientService = mock(ClientService.class);
        controller = new AppointmentController(appointmentService, clientService);
        when(clientService.isPINCorrect(clientId, clientPIN)).thenReturn(true);
    }

    @Test
    void newAppointmentTest() throws AppointmentServiceException {
        when(appointmentService.createAppointment(appointment1)).thenReturn(1);
        assertEquals(controller.newAppointment(clientId, clientPIN, appointment1), 1);
        assertEquals(clientId, appointment1.getClientId());
    }

    @Test
    void getAllAppointmentsTest() throws ParseException {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        Iterable<Appointment> list = Arrays.asList(appointment1, appointment2);
        String doctorId = "yangc";
        when(appointmentService.getAllAppointments(doctorId, DATE_FORMAT.parse("2019-09-13"))).thenReturn(list);
        assertEquals(controller.getAllAppointments(doctorId, "2019-09-13"), list);
    }
}
