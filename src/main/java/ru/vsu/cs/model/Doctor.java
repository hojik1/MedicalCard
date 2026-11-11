package ru.vsu.cs.model;

import ru.vsu.cs.enums.Specialty;

public class Doctor {
    private int id;
    private String fullName;
    private Specialty specialty;

    public Doctor(int id, String fullName, Specialty specialty) {
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