package ru.vsu.cs.model;

import ru.vsu.cs.enums.Specialty;

import java.util.Objects;

public class Doctor {

    private final int id;
    private final String fullName;
    private final Specialty specialty;

    public Doctor(int id, String fullName, Specialty specialty) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID врача должен быть положительным");
        }

        Objects.requireNonNull(fullName, "ФИО не может быть null");
        Objects.requireNonNull(specialty, "Специальность не может быть null");

        if (fullName.isBlank()) {
            throw new IllegalArgumentException("ФИО не может быть пустым");
        }

        this.id = id;
        this.fullName = fullName;
        this.specialty = specialty;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Specialty getSpecialty() {
        return specialty;
    }
}
