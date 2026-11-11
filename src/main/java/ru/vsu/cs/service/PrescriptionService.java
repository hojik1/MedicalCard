
package ru.vsu.cs.service;

import ru.vsu.cs.model.Patient;
import ru.vsu.cs.model.Prescription;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PrescriptionService {

    private final List<Prescription> prescriptions = new ArrayList<>();

    public void addPrescription(Prescription prescription) {
        Objects.requireNonNull(prescription);

        if (prescription.getStartDate().isAfter(prescription.getEndDate())) {
            throw new IllegalArgumentException(
                    "Дата начала назначения не может быть позже даты окончания"
            );
        }

        prescriptions.add(prescription);
    }

    public List<Prescription> getActivePrescriptions(
            Patient patient,
            LocalDate date) {

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

    public List<Prescription> getAllPrescriptions() {
        return new ArrayList<>(prescriptions);
    }
}
