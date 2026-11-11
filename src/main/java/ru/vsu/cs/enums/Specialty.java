
package ru.vsu.cs.enums;

public enum Specialty {
    THERAPIST(101, 30),
    SURGEON(205, 60),
    CARDIOLOGIST(302, 30),
    NEUROLOGIST(303, 30),
    PEDIATRICIAN(104, 30);

    private final int room;
    private final int appointmentDuration;

    Specialty(int room, int appointmentDuration) {
        this.room = room;
        this.appointmentDuration = appointmentDuration;
    }

    public int getRoom() {
        return room;
    }

    public int getAppointmentDuration() {
        return appointmentDuration;
    }
}
