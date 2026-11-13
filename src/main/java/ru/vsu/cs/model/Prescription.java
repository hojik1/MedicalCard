
package ru.vsu.cs.model;

import java.time.LocalDate;
import java.util.Objects;

public class Prescription implements MedicalRecordEntry {

    private final int id;
    private final Patient patient;
    private final Doctor doctor;
    private final String description;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Prescription(int id, Patient patient, Doctor doctor,
                        String description, LocalDate startDate, LocalDate endDate) {

        if (id <= 0) {
            throw new IllegalArgumentException("ID назначения должен быть положительным");
        }

        this.patient = Objects.requireNonNull(patient, "Пациент не может быть null");
        this.doctor = Objects.requireNonNull(doctor, "Врач не может быть null");
        this.startDate = Objects.requireNonNull(startDate, "Дата начала не может быть null");
        this.endDate = Objects.requireNonNull(endDate, "Дата окончания не может быть null");
        Objects.requireNonNull(description, "Описание не может быть null");

        if (description.isBlank()) {
            throw new IllegalArgumentException("Описание назначения не может быть пустым");
        }

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(
                    "Дата начала не может быть позже даты окончания"
            );
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public String getSummary() {
        return "Назначение: " + description
                + ", период: с " + startDate + " по " + endDate;
    }
}
