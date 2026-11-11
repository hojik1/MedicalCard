package ru.vsu.cs.service;

import org.junit.jupiter.api.Test;
import ru.vsu.cs.enums.Specialty;
import ru.vsu.cs.exceptions.AppointmentConflictException;
import ru.vsu.cs.model.Appointment;
import ru.vsu.cs.model.Doctor;
import ru.vsu.cs.model.Patient;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentServiceTest {

    private final AppointmentService service = new AppointmentService();

    private final Patient patient1 =
            new Patient("12345", "Иванов Иван");

    private final Patient patient2 =
            new Patient("67890", "Петров Пётр");

    private final Doctor doctor1 =
            new Doctor(1, "Сидоров Сергей", Specialty.THERAPIST);

    private final Doctor doctor2 =
            new Doctor(2, "Козлова Анна", Specialty.THERAPIST);

    @Test
    void addsAppointmentAtFreeTime() {
        Appointment appointment = new Appointment(
                1, patient1, doctor1,
                LocalDateTime.of(2026, 11, 12, 10, 0)
        );

        assertDoesNotThrow(() -> service.addAppointment(appointment));
        assertEquals(1, service.getAppointments().size());
    }

    @Test
    void throwsExceptionWhenDoctorIsBusy() throws AppointmentConflictException {
        Appointment first = new Appointment(
                1, patient1, doctor1,
                LocalDateTime.of(2026, 11, 12, 10, 0)
        );

        Appointment second = new Appointment(
                2, patient2, doctor1,
                LocalDateTime.of(2026, 11, 12, 10, 15)
        );

        service.addAppointment(first);

        assertThrows(
                AppointmentConflictException.class,
                () -> service.addAppointment(second)
        );
    }

    @Test
    void throwsExceptionWhenRoomIsBusy() throws AppointmentConflictException {
        Appointment first = new Appointment(
                1, patient1, doctor1,
                LocalDateTime.of(2026, 11, 12, 10, 0)
        );

        Appointment second = new Appointment(
                2, patient2, doctor2,
                LocalDateTime.of(2026, 11, 12, 10, 15)
        );

        service.addAppointment(first);

        assertThrows(
                AppointmentConflictException.class,
                () -> service.addAppointment(second)
        );
    }

    @Test
    void allowsAppointmentImmediatelyAfterPreviousOne()
            throws AppointmentConflictException {

        Appointment first = new Appointment(
                1, patient1, doctor1,
                LocalDateTime.of(2026, 11, 12, 10, 0)
        );

        Appointment second = new Appointment(
                2, patient2, doctor1,
                LocalDateTime.of(2026, 11, 12, 10, 30)
        );

        service.addAppointment(first);
        service.addAppointment(second);

        assertEquals(2, service.getAppointments().size());
    }

    @Test
    void throwsExceptionOutsideWorkingHours() {
        Appointment appointment = new Appointment(
                1, patient1, doctor1,
                LocalDateTime.of(2026, 11, 12, 19, 45)
        );

        assertThrows(
                AppointmentConflictException.class,
                () -> service.addAppointment(appointment)
        );
    }
}
