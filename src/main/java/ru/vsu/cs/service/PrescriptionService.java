package ru.vsu.cs.service;

import ru.vsu.cs.model.Patient;
import ru.vsu.cs.model.Prescription;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Сервис для управления назначениями врачей.
 * Позволяет добавлять назначения и находить действующие назначения пациентов.
 */
public class PrescriptionService {
    private final List<Prescription> prescriptions = new ArrayList<>();

    /**
     * Добавляет назначение в список.
     *
     * @param prescription назначение врача
     * @throws NullPointerException если назначение равно null
     */
    public void addPrescription(Prescription prescription) {
        prescriptions.add(Objects.requireNonNull(prescription));
    }

    /**
     * Находит назначения пациента, действующие на указанную дату.
     *
     * @param patient пациент
     * @param date дата проверки
     * @return список действующих назначений
     * @throws NullPointerException если пациент или дата равны null
     */
    public List<Prescription> getActivePrescriptions(Patient patient, LocalDate date) {
        Objects.requireNonNull(patient);
        Objects.requireNonNull(date);

        List<Prescription> activePrescriptions = new ArrayList<>();

        for (Prescription prescription : prescriptions) {
            boolean samePatient = prescription.getPatient().equals(patient);
            boolean active = !date.isBefore(prescription.getStartDate())
                    && !date.isAfter(prescription.getEndDate());

            if (samePatient && active) {
                activePrescriptions.add(prescription);
            }
        }

        return activePrescriptions;
    }

    /**
     * Возвращает копию списка всех назначений.
     *
     * @return список назначений
     */
    public List<Prescription> getAllPrescriptions() {
        return new ArrayList<>(prescriptions);
    }
}
