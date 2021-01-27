package com.vetClinic.app.controllers;

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
    private ClientService clientService;
    private AppointmentController controller;
    private Integer clientId = 1111;
    private Integer clientPIN = 2222;
    private Appointment appointment1 = new Appointment();
    private Appointment appointment2 = new Appointment();
    private String doctorId = "yangc";

    @BeforeEach
    void setUp() {
        appointmentService = mock(AppointmentService.class);
        clientService = mock(ClientService.class);
        controller = new AppointmentController(appointmentService, clientService);
        when(clientService.clientPINcheck(clientId, clientPIN)).thenReturn(true);
    }

    @Test
    void newAppointmentTest() {
        String expected = "The appointment is scheduled. ID of the appointment: 1";
        when(appointmentService.createAppointment(appointment1)).thenReturn(expected);
        String actual = controller.newAppointment(clientId, clientPIN, appointment1);
        assertEquals(actual, expected);
        assertEquals(clientId, appointment1.getClientId());
    }

    @Test
    void getAllAppointmentsTest() throws ParseException {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        Iterable<Appointment> list = Arrays.asList(appointment1, appointment2);
        when(appointmentService.getAllAppointments(doctorId, DATE_FORMAT.parse("2019-09-13"))).thenReturn(list);
        try {
            assertEquals(controller.getAllAppointments(doctorId, "2019-09-13"), list);
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    void deleteAppointmentTest() {
        String expected = "Deleted";
        when(appointmentService.deleteAppointment(clientId, 1)).thenReturn(expected);
        String actual = controller.deleteAppointment(clientId, clientPIN, 1);
        assertEquals(actual, expected);
    }
}
