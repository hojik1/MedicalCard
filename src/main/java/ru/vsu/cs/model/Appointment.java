package ru.vsu.cs.model;

import java.time.LocalDateTime;

public class Appointment {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime dateTime;

    public Appointment(int id, Patient patient, Doctor doctor, LocalDateTime dateTime) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}