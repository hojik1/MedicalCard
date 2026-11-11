package ru.vsu.cs.model;

public class Prescription {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private String description;
    private String date;

    public Prescription(int id, Patient patient, Doctor doctor, String description, String date) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.description = description;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }
}