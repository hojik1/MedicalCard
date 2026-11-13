package ru.vsu.cs.model;

import org.junit.jupiter.api.Test;
import ru.vsu.cs.enums.Specialty;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {

    @Test
    void rejectsInvalidId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Doctor(0, "Иванов Иван", Specialty.THERAPIST)
        );
    }

    @Test
    void rejectsBlankName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Doctor(1, "   ", Specialty.THERAPIST)
        );
    }

    @Test
    void createsDoctorWithValidData() {
        Doctor doctor = new Doctor(
                1, "Иванов Иван", Specialty.THERAPIST
        );

        assertEquals("Иванов Иван", doctor.getFullName());
    }
}
