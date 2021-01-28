package com.vetClinic.app.controllers;

import com.vetClinic.app.UserMessageKey;
import com.vetClinic.app.domain.Appointment;
import com.vetClinic.app.services.AppointmentService;
import com.vetClinic.app.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class AppointmentController {

    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private AppointmentService service;
    private ClientService clientService;

    @Autowired
    public AppointmentController(AppointmentService service, ClientService clientService) {
        this.service = service;
        this.clientService = clientService;
    }

    @PostMapping(path="/appointments/new")
    public @ResponseBody String newAppointment(@RequestHeader Integer clientId, @RequestHeader Integer clientPIN, @RequestBody Appointment appointment) {
        if(clientService.clientPINcheck(clientId, clientPIN)) {
            appointment.setClientId(clientId);
            return service.createAppointment(appointment);
        } else {
            System.out.println(UserMessageKey.AUTHORISATION_FAILED.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(path="/appointments/{doctorID}")
    public @ResponseBody Iterable<Appointment> getAllAppointments(@PathVariable String doctorID, @RequestParam String date) throws ParseException {
        return service.getAllAppointments(doctorID, DATE_FORMAT.parse(date));
    }

    @DeleteMapping(path="/appointments/{id}")
    public @ResponseBody String deleteAppointment(@RequestHeader Integer clientId, @RequestHeader Integer clientPIN, @PathVariable Integer id) {
        if(clientService.clientPINcheck(clientId, clientPIN)) {
            return service.deleteAppointment(clientId, id);
        } else {
            System.out.println(UserMessageKey.AUTHORISATION_FAILED.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
