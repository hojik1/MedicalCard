package ru.vsu.cs.model;

import java.util.Objects;

public class Patient {

    private final String policyNumber;
    private final String fullName;

    public Patient(String policyNumber, String fullName) {
        Objects.requireNonNull(policyNumber, "Номер полиса не может быть null");
        Objects.requireNonNull(fullName, "ФИО не может быть null");

        if (policyNumber.isBlank()) {
            throw new IllegalArgumentException("Номер полиса не может быть пустым");
        }

        if (fullName.isBlank()) {
            throw new IllegalArgumentException("ФИО не может быть пустым");
        }

        this.policyNumber = policyNumber;
        this.fullName = fullName;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Patient patient)) return false;
        return policyNumber.equals(patient.policyNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyNumber);
    }

    @Override
    public String toString() {
        return fullName + " (полис: " + policyNumber + ")";
    }
}
