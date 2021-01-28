package com.vetClinic.app.services;

import com.vetClinic.app.UserMessageKey;
import com.vetClinic.app.domain.Appointment;
import com.vetClinic.app.domain.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.NoResultException;
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
        if(isTimeCorrect(appointment) && repository.isTimeAvail(appointment.getDoctorId(), appointment.getDate())){
            Integer id = repository.createAppointment(appointment);
            return UserMessageKey.APPOINTMENT_NEW_DONE.getMessage() + id;
        } else {
            return UserMessageKey.APPOITNMENT_FAILED.getMessage();
        }
    }

    public String deleteAppointment(Integer clientId, Integer id) {
        try {
            repository.deleteAppointment(clientId, id);
        } catch (NoResultException e) {
            return UserMessageKey.CANCELLATION_FAILED.getMessage();
        }
        return UserMessageKey.CANCELLATION_DONE.getMessage();
    }

    protected boolean isTimeCorrect(Appointment appointment) {
        return appointment.getDate().after(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime());
    }
}
