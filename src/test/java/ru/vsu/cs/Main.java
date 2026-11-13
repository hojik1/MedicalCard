package ru.vsu.cs;

import ru.vsu.cs.enums.Specialty;
import ru.vsu.cs.exceptions.AppointmentConflictException;
import ru.vsu.cs.model.*;
import ru.vsu.cs.service.AppointmentService;
import ru.vsu.cs.service.PrescriptionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        AppointmentService appointmentService = new AppointmentService();
        PrescriptionService prescriptionService = new PrescriptionService();

        System.out.println("=== МЕДИЦИНСКАЯ КАРТА ПАЦИЕНТА ===");

        // Создание пациентов
        List<Patient> patients = List.of(
                new Patient("10001", "Иванов Иван"),
                new Patient("10002", "Петров Пётр"),
                new Patient("10003", "Сидорова Анна"),
                new Patient("10004", "Козлов Максим"),
                new Patient("10005", "Смирнова Мария"),
                new Patient("10006", "Васильев Олег"),
                new Patient("10007", "Попова Елена"),
                new Patient("10008", "Морозов Артём"),
                new Patient("10009", "Фёдорова Дарья"),
                new Patient("10010", "Новиков Михаил")
        );

        // Создание врачей
        List<Doctor> doctors = List.of(
                new Doctor(1, "Сергей Сидоров", Specialty.THERAPIST),
                new Doctor(2, "Анна Козлова", Specialty.SURGEON),
                new Doctor(3, "Игорь Павлов", Specialty.CARDIOLOGIST),
                new Doctor(4, "Мария Волкова", Specialty.NEUROLOGIST),
                new Doctor(5, "Олег Смирнов", Specialty.PEDIATRICIAN)
        );

        System.out.println("\nПациентов создано: " + patients.size());
        System.out.println("Врачей создано: " + doctors.size());

        // Запись пациентов на приём
        LocalDateTime date = LocalDateTime.of(2026, 11, 12, 9, 0);

        int appointmentId = 1;

        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = doctors.get(i);

            try {
                Appointment first = new Appointment(
                        appointmentId++,
                        patients.get(i),
                        doctor,
                        date
                );

                Appointment second = new Appointment(
                        appointmentId++,
                        patients.get(i + 5),
                        doctor,
                        date.plusHours(1)
                );

                appointmentService.addAppointment(first);
                appointmentService.addAppointment(second);

            } catch (AppointmentConflictException e) {
                System.out.println("Ошибка записи: " + e.getMessage());
            }
        }

        System.out.println("\n=== ЗАПИСИ НА ПРИЁМ ===");

        for (Appointment appointment : appointmentService.getAppointments()) {
            System.out.println(
                    appointment.getPatient().getFullName()
                            + " | врач: "
                            + appointment.getDoctor().getFullName()
                            + " | дата: "
                            + appointment.getDateTime()
            );
        }

        // Проверка попытки записи на занятое время
        System.out.println("\n=== ПРОВЕРКА ЗАНЯТОГО ВРЕМЕНИ ===");

        try {
            Appointment conflictingAppointment = new Appointment(
                    100,
                    patients.get(9),
                    doctors.get(0),
                    date.plusMinutes(15)
            );

            appointmentService.addAppointment(conflictingAppointment);

        } catch (AppointmentConflictException e) {
            System.out.println("Запись отклонена: " + e.getMessage());
        }

        // История приёмов пациента за период
        System.out.println("\n=== ИСТОРИЯ ПРИЁМОВ ===");

        List<Appointment> history = appointmentService.getPatientHistory(
                patients.get(0),
                date.minusDays(1),
                date.plusDays(1)
        );

        for (Appointment appointment : history) {
            System.out.println(
                    appointment.getDateTime()
                            + " | "
                            + appointment.getDoctor().getFullName()
            );
        }

        // Создание диагнозов
        List<Diagnosis> diagnoses = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            diagnoses.add(new Diagnosis(
                    i + 1,
                    patients.get(i),
                    doctors.get(i),
                    "Диагноз №" + (i + 1),
                    date.toLocalDate()
            ));
        }

        // Создание назначений
        for (int i = 0; i < 5; i++) {
            Prescription prescription = new Prescription(
                    i + 1,
                    patients.get(i),
                    doctors.get(i),
                    "Лекарство №" + (i + 1),
                    LocalDate.of(2026, 11, 1),
                    LocalDate.of(2026, 11, 14)
            );

            prescriptionService.addPrescription(prescription);
        }

        System.out.println("\n=== ДИАГНОЗЫ ===");

        for (Diagnosis diagnosis : diagnoses) {
            System.out.println(diagnosis.getSummary());
        }

        // Поиск действующих назначений
        System.out.println("\n=== ДЕЙСТВУЮЩИЕ НАЗНАЧЕНИЯ ===");

        List<Prescription> activePrescriptions =
                prescriptionService.getActivePrescriptions(
                        patients.get(0),
                        LocalDate.of(2026, 11, 12)
                );

        for (Prescription prescription : activePrescriptions) {
            System.out.println(prescription.getSummary());
        }

        // Демонстрация полиморфизма
        System.out.println("\n=== МЕДИЦИНСКАЯ ИСТОРИЯ ===");

        List<MedicalRecordEntry> medicalHistory = new ArrayList<>();
        medicalHistory.addAll(diagnoses);
        medicalHistory.addAll(prescriptionService.getAllPrescriptions());

        for (MedicalRecordEntry entry : medicalHistory) {
            System.out.println(entry.getSummary());
        }

        System.out.println("\n=== РАБОТА ПРОГРАММЫ ЗАВЕРШЕНА ===");
    }
}

