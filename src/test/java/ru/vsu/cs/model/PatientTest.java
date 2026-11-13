package ru.vsu.cs.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    @Test
    void patientsWithSamePolicyNumberAreEqual() {
        Patient first = new Patient("12345", "Иванов Иван");
        Patient second = new Patient("12345", "Петров Пётр");

        assertEquals(first, second);
    }

    @Test
    void patientsWithDifferentPolicyNumbersAreNotEqual() {
        Patient first = new Patient("12345", "Иванов Иван");
        Patient second = new Patient("67890", "Иванов Иван");

        assertNotEquals(first, second);
    }

    @Test
    void equalPatientsHaveSameHashCode() {
        Patient first = new Patient("12345", "Иванов Иван");
        Patient second = new Patient("12345", "Петров Пётр");

        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void patientIsEqualToItself() {
        Patient patient = new Patient("12345", "Иванов Иван");

        assertEquals(patient, patient);
    }

    @Test
    void rejectsBlankPolicyNumber() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Patient("   ", "Иванов Иван")
        );
    }

    @Test
    void rejectsBlankFullName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Patient("12345", "")
        );
    }
}
