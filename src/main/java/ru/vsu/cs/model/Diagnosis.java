package ru.vsu.cs.model;

import java.time.LocalDate;
import java.util.Objects;

public class Diagnosis implements MedicalRecordEntry {

    private final int id;
    private final Patient patient;
    private final Doctor doctor;
    private final String description;
    private final LocalDate date;

    public Diagnosis(int id, Patient patient, Doctor doctor,
                     String description, LocalDate date) {

        if (id <= 0) {
            throw new IllegalArgumentException("ID диагноза должен быть положительным");
        }

        this.patient = Objects.requireNonNull(patient, "Пациент не может быть null");
        this.doctor = Objects.requireNonNull(doctor, "Врач не может быть null");
        this.date = Objects.requireNonNull(date, "Дата диагноза не может быть null");
        Objects.requireNonNull(description, "Описание не может быть null");

        if (description.isBlank()) {
            throw new IllegalArgumentException("Описание диагноза не может быть пустым");
        }

        this.id = id;
        this.description = description;
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
