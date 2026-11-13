package ru.vsu.cs.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Представляет назначение врача пациенту на определённый период.
 * Реализует интерфейс записи медицинской карты.
 */
public class Prescription implements MedicalRecordEntry {
    private final int id;
    private final Patient patient;
    private final Doctor doctor;
    private final String description;
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Создаёт назначение с указанными данными.
     *
     * @param id идентификатор назначения
     * @param patient пациент
     * @param doctor врач, сделавший назначение
     * @param description описание назначения
     * @param startDate дата начала назначения
     * @param endDate дата окончания назначения
     * @throws IllegalArgumentException если ID неположительный,
     *         описание пустое или дата начала позже даты окончания
     * @throws NullPointerException если обязательные данные равны null
     */
    public Prescription(int id, Patient patient, Doctor doctor,
                        String description, LocalDate startDate, LocalDate endDate) {
        if (id <= 0) throw new IllegalArgumentException("ID назначения должен быть положительным");
        this.patient = Objects.requireNonNull(patient, "Пациент не может быть null");
        this.doctor = Objects.requireNonNull(doctor, "Врач не может быть null");
        this.startDate = Objects.requireNonNull(startDate, "Дата начала не может быть null");
        this.endDate = Objects.requireNonNull(endDate, "Дата окончания не может быть null");
        Objects.requireNonNull(description, "Описание не может быть null");

        if (description.isBlank()) {
            throw new IllegalArgumentException("Описание назначения не может быть пустым");
        }

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Дата начала не может быть позже даты окончания");
        }

        this.id = id;
        this.description = description;
    }

    /**
     * Возвращает идентификатор назначения.
     *
     * @return ID назначения
     */
    public int getId() {
        return id;
    }

    /**
     * Возвращает пациента, которому сделано назначение.
     *
     * @return пациент
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Возвращает врача, сделавшего назначение.
     *
     * @return врач
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Возвращает описание назначения.
     *
     * @return описание назначения
     */
    public String getDescription() {
        return description;
    }

    /**
     * Возвращает дату начала назначения.
     *
     * @return дата начала
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Возвращает дату окончания назначения.
     *
     * @return дата окончания
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Формирует краткое текстовое описание назначения.
     *
     * @return описание назначения и период его действия
     */
    @Override
    public String getSummary() {
        return "Назначение: " + description + ", период: с " + startDate + " по " + endDate;
    }
}