package ru.vsu.cs.service;

import ru.vsu.cs.enums.Specialty;
import ru.vsu.cs.exceptions.AppointmentConflictException;
import ru.vsu.cs.model.Appointment;
import ru.vsu.cs.model.Patient;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Сервис для управления записями пациентов на приём.
 * Проверяет занятость врачей и кабинетов, а также рабочее время.
 */
public class AppointmentService {
    private final List<Appointment> appointments = new ArrayList<>();
    private static final LocalTime OPENING_TIME = LocalTime.of(8, 0);
    private static final LocalTime CLOSING_TIME = LocalTime.of(20, 0);

    /**
     * Добавляет запись на приём с предварительной проверкой
     * рабочего времени, занятости врача и кабинета.
     *
     * @param appointment запись на приём
     * @throws AppointmentConflictException если врач или кабинет заняты
     *         либо кабинет не работает в указанное время
     * @throws NullPointerException если запись равна null
     */
    public void addAppointment(Appointment appointment)
            throws AppointmentConflictException {
        Objects.requireNonNull(appointment);

        Specialty specialty = appointment.getDoctor().getSpecialty();
        LocalDateTime start = appointment.getDateTime();
        LocalDateTime end = start.plusMinutes(specialty.getAppointmentDuration());

        if (start.toLocalTime().isBefore(OPENING_TIME)
                || end.toLocalTime().isAfter(CLOSING_TIME)
                || !end.toLocalDate().equals(start.toLocalDate())) {
            throw new AppointmentConflictException("Кабинет не работает в это время");
        }

        for (Appointment existing : appointments) {
            LocalDateTime existingStart = existing.getDateTime();
            LocalDateTime existingEnd = existingStart.plusMinutes(
                    existing.getDoctor().getSpecialty().getAppointmentDuration());

            boolean overlaps = start.isBefore(existingEnd) && end.isAfter(existingStart);
            boolean sameDoctor = appointment.getDoctor().getId()
                    == existing.getDoctor().getId();
            boolean sameRoom = specialty.getRoom()
                    == existing.getDoctor().getSpecialty().getRoom();

            if (overlaps && (sameDoctor || sameRoom)) {
                throw new AppointmentConflictException(
                        "Врач или кабинет уже заняты в это время");
            }
        }

        appointments.add(appointment);
    }

    /**
     * Возвращает копию списка всех записей на приём.
     *
     * @return список записей
     */
    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }

    /**
     * Находит записи указанного пациента за заданный период.
     *
     * @param patient пациент
     * @param start начало периода
     * @param end конец периода
     * @return список записей пациента за указанный период
     */
    public List<Appointment> getPatientHistory(
            Patient patient, LocalDateTime start, LocalDateTime end) {
        List<Appointment> history = new ArrayList<>();

        for (Appointment appointment : appointments) {
            if (appointment.getPatient().equals(patient)
                    && !appointment.getDateTime().isBefore(start)
                    && !appointment.getDateTime().isAfter(end)) {
                history.add(appointment);
            }
        }

        return history;
    }
}