package ru.vsu.cs.exceptions;

/**
 * Исключение, возникающее при попытке записи пациента
 * на занятое время или в нерабочее время кабинета.
 */
public class AppointmentConflictException extends Exception {

    /**
     * Создаёт исключение с описанием причины конфликта.
     *
     * @param message сообщение об ошибке
     */
    public AppointmentConflictException(String message) {
        super(message);
    }
}