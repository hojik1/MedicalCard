package ru.vsu.cs.service;

import org.junit.jupiter.api.Test;
import ru.vsu.cs.enums.Specialty;
import ru.vsu.cs.model.Doctor;
import ru.vsu.cs.model.Patient;
import ru.vsu.cs.model.Prescription;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PrescriptionServiceTest {

    private final PrescriptionService service = new PrescriptionService();

    private final Patient patient1 =
            new Patient("12345", "Иванов Иван");

    private final Patient patient2 =
            new Patient("67890", "Петров Пётр");

    private final Doctor doctor =
            new Doctor(1, "Сидоров Сергей", Specialty.THERAPIST);

    @Test
    void addsPrescription() {
        Prescription prescription = new Prescription(
                1, patient1, doctor, "Витамин D",
                LocalDate.of(2026, 11, 1),
                LocalDate.of(2026, 11, 14)
        );

        service.addPrescription(prescription);

        assertEquals(1, service.getAllPrescriptions().size());
    }

    @Test
    void findsActivePrescription() {
        Prescription prescription = new Prescription(
                1, patient1, doctor, "Витамин D",
                LocalDate.of(2026, 11, 1),
                LocalDate.of(2026, 11, 14)
        );

        service.addPrescription(prescription);

        assertEquals(
                1,
                service.getActivePrescriptions(
                        patient1, LocalDate.of(2026, 11, 7)
                ).size()
        );
    }

    @Test
    void doesNotFindPrescriptionBeforeStartDate() {
        Prescription prescription = new Prescription(
                1, patient1, doctor, "Витамин D",
                LocalDate.of(2026, 11, 1),
                LocalDate.of(2026, 11, 14)
        );

        service.addPrescription(prescription);

        assertTrue(
                service.getActivePrescriptions(
                        patient1, LocalDate.of(2026, 10, 31)
                ).isEmpty()
        );
    }

    @Test
    void doesNotFindPrescriptionAfterEndDate() {
        Prescription prescription = new Prescription(
                1, patient1, doctor, "Витамин D",
                LocalDate.of(2026, 11, 1),
                LocalDate.of(2026, 11, 14)
        );

        service.addPrescription(prescription);

        assertTrue(
                service.getActivePrescriptions(
                        patient1, LocalDate.of(2026, 11, 15)
                ).isEmpty()
        );
    }

    @Test
    void doesNotFindAnotherPatientsPrescription() {
        Prescription prescription = new Prescription(
                1, patient1, doctor, "Витамин D",
                LocalDate.of(2026, 11, 1),
                LocalDate.of(2026, 11, 14)
        );

        service.addPrescription(prescription);

        assertTrue(
                service.getActivePrescriptions(
                        patient2, LocalDate.of(2026, 11, 7)
                ).isEmpty()
        );
    }


    @Test
    void rejectsInvalidPrescriptionPeriod() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Prescription(
                        1, patient1, doctor, "Витамин D",
                        LocalDate.of(2026, 11, 14),
                        LocalDate.of(2026, 11, 1)
                )
        );
    }

    @Test
    void rejectsPrescriptionWithNullPatient() {
        assertThrows(
                NullPointerException.class,
                () -> new Prescription(
                        1, null, doctor, "Витамин D",
                        LocalDate.of(2026, 11, 1),
                        LocalDate.of(2026, 11, 14)
                )
        );
    }
}
