package ru.vsu.cs.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Представляет диагноз, внесённый в медицинскую карту пациента.
 * Реализует интерфейс записи медицинской карты.
 */
public class Diagnosis implements MedicalRecordEntry {
    private final int id;
    private final Patient patient;
    private final Doctor doctor;
    private final String description;
    private final LocalDate date;

    /**
     * Создаёт диагноз с указанными данными.
     *
     * @param id идентификатор диагноза
     * @param patient пациент
     * @param doctor врач, поставивший диагноз
     * @param description описание диагноза
     * @param date дата постановки диагноза
     * @throws IllegalArgumentException если ID неположительный или описание пустое
     * @throws NullPointerException если обязательные данные равны null
     */
    public Diagnosis(int id, Patient patient, Doctor doctor,
                     String description, LocalDate date) {
        if (id <= 0) throw new IllegalArgumentException("ID диагноза должен быть положительным");
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

    /**
     * Возвращает идентификатор диагноза.
     *
     * @return ID диагноза
     */
    public int getId() {
        return id;
    }

    /**
     * Возвращает пациента, которому поставлен диагноз.
     *
     * @return пациент
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Возвращает врача, поставившего диагноз.
     *
     * @return врач
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Возвращает описание диагноза.
     *
     * @return описание диагноза
     */
    public String getDescription() {
        return description;
    }

    /**
     * Возвращает дату постановки диагноза.
     *
     * @return дата диагноза
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Формирует краткое текстовое описание диагноза.
     *
     * @return описание диагноза и дата его постановки
     */
    @Override
    public String getSummary() {
        return "Диагноз: " + description + ", дата: " + date;
    }
}