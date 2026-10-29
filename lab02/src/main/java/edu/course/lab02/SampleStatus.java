package edu.course.lab02;

public enum SampleStatus {
    RAW,        // Сырые данные (исходное состояние)
    PROCESSED,  // Данные после предобработки
    READY,      // Готовы к использованию моделью ML
    INVALID     // Отбракованные данные
}
