package com.vetClinic.app.services;

import com.vetClinic.app.domain.Appointment;
import com.vetClinic.app.domain.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppointmentServiceTests {

    private AppointmentRepository repository;
    private AppointmentService service;
    private Appointment appointment1 = new Appointment();
    private Appointment appointment2 = new Appointment();
    private Date date;
    private String doctorId = "greym";
    private Calendar c;

    @BeforeEach
    void setUp() {
        repository = mock(AppointmentRepository.class);
        service = new AppointmentService(repository);
        appointment1.setDoctorId(doctorId);
        c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        c.add(Calendar.MINUTE, 5);
        date = c.getTime();
        appointment1.setDate(date);
        when(repository.isTimeAvail(doctorId, date)).thenReturn(true);
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

    @Test
    void isTimeCorrectTest() {
        assertTrue(service.isTimeCorrect(appointment1));
    }
}
