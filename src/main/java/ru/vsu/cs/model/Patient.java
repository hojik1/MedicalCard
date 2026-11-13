package ru.vsu.cs.model;

import java.util.Objects;

/**
 * Представляет пациента медицинского учреждения.
 * Уникальность пациента определяется номером страхового полиса.
 */
public class Patient {
    private final String policyNumber;
    private final String fullName;

    /**
     * Создаёт пациента с указанным номером полиса и ФИО.
     *
     * @param policyNumber номер страхового полиса
     * @param fullName полное имя пациента
     * @throws NullPointerException если номер полиса или ФИО равны null
     * @throws IllegalArgumentException если номер полиса или ФИО пусты
     */
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

    /**
     * Возвращает номер страхового полиса пациента.
     *
     * @return номер полиса
     */
    public String getPolicyNumber() {
        return policyNumber;
    }

    /**
     * Возвращает полное имя пациента.
     *
     * @return ФИО пациента
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Проверяет равенство пациентов по номеру страхового полиса.
     *
     * @param obj объект для сравнения
     * @return true, если объекты представляют одного пациента
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Patient patient)) return false;
        return policyNumber.equals(patient.policyNumber);
    }

    /**
     * Возвращает хеш-код пациента на основе номера полиса.
     *
     * @return хеш-код пациента
     */
    @Override
    public int hashCode() {
        return Objects.hash(policyNumber);
    }

    /**
     * Возвращает текстовое представление пациента.
     *
     * @return ФИО и номер страхового полиса
     */
    @Override
    public String toString() {
        return fullName + " (полис: " + policyNumber + ")";
    }
}