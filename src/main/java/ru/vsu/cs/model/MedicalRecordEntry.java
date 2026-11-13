package ru.vsu.cs.model;

/**
 * Интерфейс записи в медицинской карте пациента.
 * Реализуется классами диагноза и назначения.
 */
public interface MedicalRecordEntry {

    /**
     * Возвращает краткое текстовое описание записи.
     *
     * @return описание записи
     */
    String getSummary();
}