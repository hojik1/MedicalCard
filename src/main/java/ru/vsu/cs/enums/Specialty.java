package ru.vsu.cs.enums;

/**
 * Перечисление медицинских специальностей.
 * Для каждой специальности определены номер кабинета
 * и стандартная продолжительность приёма в минутах.
 */
public enum Specialty {
    THERAPIST(101, 30),
    SURGEON(205, 60),
    CARDIOLOGIST(302, 30),
    NEUROLOGIST(303, 30),
    PEDIATRICIAN(104, 30);

    private final int room;
    private final int appointmentDuration;

    /**
     * Создаёт специальность с указанным кабинетом и длительностью приёма.
     *
     * @param room номер кабинета
     * @param appointmentDuration продолжительность приёма в минутах
     */
    Specialty(int room, int appointmentDuration) {
        this.room = room;
        this.appointmentDuration = appointmentDuration;
    }

    /**
     * Возвращает номер кабинета для специальности.
     *
     * @return номер кабинета
     */
    public int getRoom() {
        return room;
    }

    /**
     * Возвращает стандартную продолжительность приёма.
     *
     * @return продолжительность приёма в минутах
     */
    public int getAppointmentDuration() {
        return appointmentDuration;
    }
}