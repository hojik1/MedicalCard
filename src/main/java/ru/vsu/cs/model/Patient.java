
package ru.vsu.cs.model;

import java.util.Objects;

public class Patient {
    private final String policyNumber;
    private final String fullName;

    public Patient(String policyNumber, String fullName) {
        this.policyNumber = Objects.requireNonNull(policyNumber);
        this.fullName = Objects.requireNonNull(fullName);
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
