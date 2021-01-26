package com.vetClinic.app.services;

import com.vetClinic.app.domain.Appointment;
import com.vetClinic.app.domain.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Date;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppointmentServiceTests {

    private AppointmentRepository repository;
    private AppointmentService service;
    private Appointment appointment1 = new Appointment();
    private Appointment appointment2 = new Appointment();
    private Date date = new Date();
    private String doctorId = "greym";

    @BeforeEach
    void setUp() {
        repository = mock(AppointmentRepository.class);
        service = new AppointmentService(repository);
        when(repository.isTimeAvail(doctorId, date)).thenReturn(true);
        appointment1.setDoctorId(doctorId);
        appointment1.setDate(date);
    }

    @Test
    void getAllAppointmentsTest() {
        Iterable<Appointment> list = Arrays.asList(appointment1, appointment2);
        when(repository.getAllAppointments(doctorId, date)).thenReturn(list);
        assertEquals(service.getAllAppointments(doctorId, date), list);
    }

    @Test
    void createAppointmentTest() {
        when(repository.createAppointment(appointment1)).thenReturn(1);
        String expected = "The appointment is scheduled. ID of the appointment: 1";
        String actual = service.createAppointment(appointment1);
        assertEquals(expected, actual);
    }

    @Test
    void deleteAppointmentTest() {
        String expected = "Deleted";
        String actual = service.deleteAppointment(1111, 1);
        assertEquals(expected, actual);
    }

    @Test
    void isTimeAvailTest() {
        assertTrue(service.isTimeAvail(appointment1));
    }
}
