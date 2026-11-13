package ru.vsu.cs.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Представляет запись пациента на приём к врачу.
 * Содержит пациента, врача и дату со временем приёма.
 */
public class Appointment {
    private final int id;
    private final Patient patient;
    private final Doctor doctor;
    private final LocalDateTime dateTime;

    /**
     * Создаёт запись на приём.
     *
     * @param id идентификатор приёма
     * @param patient пациент
     * @param doctor врач
     * @param dateTime дата и время приёма
     * @throws IllegalArgumentException если ID неположительный
     * @throws NullPointerException если пациент, врач или дата равны null
     */
    public Appointment(int id, Patient patient, Doctor doctor, LocalDateTime dateTime) {
        if (id <= 0) throw new IllegalArgumentException("ID приёма должен быть положительным");
        this.patient = Objects.requireNonNull(patient, "Пациент не может быть null");
        this.doctor = Objects.requireNonNull(doctor, "Врач не может быть null");
        this.dateTime = Objects.requireNonNull(dateTime, "Дата приёма не может быть null");
        this.id = id;
    }

    /**
     * Возвращает идентификатор приёма.
     *
     * @return ID приёма
     */
    public int getId() {
        return id;
    }

    /**
     * Возвращает пациента, записанного на приём.
     *
     * @return пациент
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Возвращает врача, к которому записан пациент.
     *
     * @return врач
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Возвращает дату и время приёма.
     *
     * @return дата и время приёма
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }
}