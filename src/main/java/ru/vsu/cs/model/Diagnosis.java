package ru.vsu.cs.model;

import java.time.LocalDate;

public class Diagnosis implements MedicalRecordEntry {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private String description;
    private LocalDate date;

    public Diagnosis(int id, Patient patient, Doctor doctor,
                     String description, LocalDate date) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.description = description;
        this.date = date;
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

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String getSummary() {
        return "Диагноз: " + description + ", дата: " + date;
    }
}