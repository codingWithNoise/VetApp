package com.vetClinic.app.services;

import com.vetClinic.app.AppointmentServiceException;
import com.vetClinic.app.domain.Appointment;
import com.vetClinic.app.domain.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AppointmentServiceTests {

    private AppointmentRepository repository;
    private AppointmentService service;
    private Appointment appointment1 = new Appointment();
    private Appointment appointment2 = new Appointment();
    private Date date;
    private String doctorId = "greym";

    @BeforeEach
    void setUp() {
        repository = mock(AppointmentRepository.class);
        service = new AppointmentService(repository);
        appointment1.setDoctorId(doctorId);
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        c.add(Calendar.MINUTE, 5);
        date = c.getTime();
        c.add(Calendar.MINUTE, -30);
        Date startTime = c.getTime();
        c.add(Calendar.MINUTE, 30);
        Date endTime = c.getTime();
        appointment1.setTime(date);
        when(repository.getAppointmentsStartingBetween(doctorId, startTime, endTime)).thenReturn(Collections.emptyList());
    }

    @Test
    void getAllAppointmentsTest() {
        Iterable<Appointment> list = Arrays.asList(appointment1, appointment2);
        when(repository.getAllAppointments(doctorId, date)).thenReturn(list);
        assertEquals(service.getAllAppointments(doctorId, date), list);
    }

    @Test
    void createAppointmentTest() throws AppointmentServiceException {
        when(repository.createAppointment(appointment1)).thenReturn(1);
        assertEquals(1, service.createAppointment(appointment1));
    }
}
