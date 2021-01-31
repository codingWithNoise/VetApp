package com.vetClinic.app.controllers;

import com.vetClinic.app.AppointmentServiceException;
import com.vetClinic.app.ClientServiceException;
import com.vetClinic.app.ErrorMessage;
import com.vetClinic.app.domain.Appointment;
import com.vetClinic.app.services.AppointmentService;
import com.vetClinic.app.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
public class AppointmentController {

    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final AppointmentService service;
    private final ClientService clientService;

    @Autowired
    public AppointmentController(AppointmentService service, ClientService clientService) {
        this.service = service;
        this.clientService = clientService;
    }

    @PostMapping(path="/appointments/new")
    public @ResponseBody Integer newAppointment(@RequestHeader Integer clientId, @RequestHeader String clientPIN, @RequestBody Appointment appointment) {
        appointment.setClientId(clientId);
        try {
            if(clientService.isPINCorrect(clientId, clientPIN)) {
                return service.createAppointment(appointment);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessage.AUTHORIZATION_ERROR.getMessage());
            }
        } catch (ClientServiceException ce) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ce.getMessage());
        } catch (AppointmentServiceException ae) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ae.getMessage());
        }
    }

    @GetMapping(path="/appointments/{doctorID}")
    public @ResponseBody Iterable<Appointment> getAllAppointments(@PathVariable String doctorID, @RequestParam String date) throws ParseException {
        return service.getAllAppointments(doctorID, DATE_FORMAT.parse(date));
    }

    @DeleteMapping(path="/appointments/{id}")
    public @ResponseBody void deleteAppointment(@RequestHeader Integer clientId, @RequestHeader String clientPIN, @PathVariable Integer id) {
        try {
            if(clientService.isPINCorrect(clientId, clientPIN)) {
                service.deleteAppointment(clientId, id);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorMessage.AUTHORIZATION_ERROR.getMessage());
            }
        } catch (ClientServiceException ce) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ce.getMessage());
        } catch (AppointmentServiceException ae) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ae.getMessage());
        }
    }
}
