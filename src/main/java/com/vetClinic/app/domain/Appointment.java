package com.vetClinic.app.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer clientId;
    private String doctorId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public Appointment() {
    }

    public Integer getClientId() {
        return clientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public Date getTime() {
        return time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
