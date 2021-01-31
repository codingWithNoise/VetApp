package com.vetClinic.app.services;

import com.vetClinic.app.AppointmentServiceException;
import com.vetClinic.app.ErrorMessage;
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

    private final AppointmentRepository repository;

    @Autowired
    AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public Iterable<Appointment> getAllAppointments(String doctorId, Date date) {
        return repository.getAllAppointments(doctorId, date);
    }

    public Integer createAppointment(Appointment appointment) throws AppointmentServiceException {
        if (isTimeInFuture(appointment) && isTimeAvail(appointment.getDoctorId(), appointment.getTime())) {
            return repository.createAppointment(appointment);
        } else {
            throw new AppointmentServiceException(ErrorMessage.APPOINTMENT_ERROR);
        }
    }

    public void deleteAppointment(Integer clientId, Integer id) throws AppointmentServiceException {
        try {
            repository.deleteAppointment(clientId, id);
        } catch (NoResultException e) {
            throw new AppointmentServiceException(ErrorMessage.CANCELLATION_ERROR);
        }
    }

    private boolean isTimeInFuture(Appointment appointment) {
        return appointment.getTime().after(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime());
    }

    private boolean isTimeAvail(String doctorId, Date time) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);
        c.add(Calendar.MINUTE, -30);
        Date startTime = c.getTime();
        c.add(Calendar.MINUTE, 60);
        Date endTime = c.getTime();

        return repository.getAppointmentsStartingBetween(doctorId, startTime, endTime).isEmpty();
    }
}
