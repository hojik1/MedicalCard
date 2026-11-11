package ru.vsu.cs.model;

import java.time.LocalDate;

public class Prescription implements MedicalRecordEntry {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;

    public Prescription(int id, Patient patient, Doctor doctor,
                        String description, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public String getSummary() {
        return "Назначение: " + description +
                ", период: с " + startDate + " по " + endDate;
    }
}