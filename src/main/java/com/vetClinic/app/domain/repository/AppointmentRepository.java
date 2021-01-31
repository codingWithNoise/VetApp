package com.vetClinic.app.domain.repository;
import com.vetClinic.app.domain.Appointment;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public class AppointmentRepository {

    @PersistenceContext
    private EntityManager em;

    public AppointmentRepository() {
    }

    @Transactional
    public Integer createAppointment(Appointment appointment) {
        em.persist(appointment);
        return appointment.getId();
    }

    @Transactional
    public void deleteAppointment(Integer clientId, Integer id) throws NoResultException {
        Appointment appointment = em.createQuery("from Appointment a where a.clientId=:clientId and a.id=:id",
                Appointment.class).setParameter("clientId", clientId).setParameter("id", id).getSingleResult();
        em.remove(appointment);
    }

    public Iterable<Appointment> getAllAppointments(String doctorId, Date date) {
        return em.createQuery("from Appointment a where a.doctorId=:doctorId and DATE(a.time)=:date", Appointment.class).
                setParameter("doctorId", doctorId).setParameter("date", date).getResultList();
    }

    public List<Appointment> getAppointmentsStartingBetween(String doctorId, Date startTime, Date endTime){
        return em.createQuery("from Appointment a where a.doctorId=:doctorId and a.time>:startTime and a.time<:endTime", Appointment.class)
                .setParameter("doctorId", doctorId).setParameter("startTime", startTime).setParameter("endTime", endTime).getResultList();
    }
}
