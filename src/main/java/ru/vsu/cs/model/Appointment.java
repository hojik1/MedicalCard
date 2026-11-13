package ru.vsu.cs.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Appointment {

    private final int id;
    private final Patient patient;
    private final Doctor doctor;
    private final LocalDateTime dateTime;

    public Appointment(int id, Patient patient, Doctor doctor,
                       LocalDateTime dateTime) {

        if (id <= 0) {
            throw new IllegalArgumentException("ID приёма должен быть положительным");
        }

        this.patient = Objects.requireNonNull(patient, "Пациент не может быть null");
        this.doctor = Objects.requireNonNull(doctor, "Врач не может быть null");
        this.dateTime = Objects.requireNonNull(dateTime, "Дата приёма не может быть null");

        this.id = id;
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
