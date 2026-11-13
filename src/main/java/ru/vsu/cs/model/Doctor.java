package ru.vsu.cs.model;

import ru.vsu.cs.enums.Specialty;
import java.util.Objects;

/**
 * Представляет врача медицинского учреждения.
 * Содержит идентификатор, ФИО и медицинскую специальность.
 */
public class Doctor {
    private final int id;
    private final String fullName;
    private final Specialty specialty;

    /**
     * Создаёт врача с указанными данными.
     *
     * @param id уникальный идентификатор врача
     * @param fullName полное имя врача
     * @param specialty медицинская специальность
     * @throws IllegalArgumentException если ID неположительный или ФИО пустое
     * @throws NullPointerException если ФИО или специальность равны null
     */
    public Doctor(int id, String fullName, Specialty specialty) {
        if (id <= 0) throw new IllegalArgumentException("ID врача должен быть положительным");
        Objects.requireNonNull(fullName, "ФИО не может быть null");
        Objects.requireNonNull(specialty, "Специальность не может быть null");
        if (fullName.isBlank()) throw new IllegalArgumentException("ФИО не может быть пустым");

        this.id = id;
        this.fullName = fullName;
        this.specialty = specialty;
    }

    /**
     * Возвращает идентификатор врача.
     *
     * @return ID врача
     */
    public int getId() {
        return id;
    }

    /**
     * Возвращает полное имя врача.
     *
     * @return ФИО врача
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Возвращает специальность врача.
     *
     * @return специальность врача
     */
    public Specialty getSpecialty() {
        return specialty;
    }
}