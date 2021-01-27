package com.vetClinic.app.services;

import com.vetClinic.app.domain.Appointment;
import com.vetClinic.app.domain.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.NoResultException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Service
public class AppointmentService {

    private AppointmentRepository repository;

    @Autowired
    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public Iterable<Appointment> getAllAppointments(String doctorId, Date date) {
        return repository.getAllAppointments(doctorId, date);
    }

    public String createAppointment(Appointment appointment) {
        if(isTimeCorrect(appointment) && isTimeAvail(appointment)){
            Integer id = repository.createAppointment(appointment);
            return "The appointment is scheduled. ID of the appointment: " + id;
        } else {
            return "Chosen time is not available.";
        }
    }

    public String deleteAppointment(Integer clientId, Integer id) {
        try {
            repository.deleteAppointment(clientId, id);
        } catch (NoResultException e) {
            return "No appointment to delete.";
        }
        return "Deleted";
    }

    protected boolean isTimeCorrect(Appointment appointment) {
        return appointment.getDate().after(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime());
    }

    protected boolean isTimeAvail(Appointment appointment) {
        return repository.isTimeAvail(appointment.getDoctorId(), appointment.getDate());
    }
}
