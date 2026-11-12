package edu.course.lab02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class DataSampleTest {

    @Test
    void shouldCreateValidDataSample() {
        double[] inputFeatures = {2.0, 4.0, 6.0};
        DataSample sample = new DataSample("SAMPLE-42", "dog", inputFeatures);

        assertEquals("SAMPLE-42", sample.getId());
        assertEquals("dog", sample.getLabel());
        assertEquals(SampleStatus.RAW, sample.getStatus());
        assertFalse(sample.isReady());
        
        // Проверяем математику: (2 + 4 + 6) / 3 = 4.0
        assertEquals(4.0, sample.calculateMeanFeature(), 1e-9);
    }

    @Test
    void shouldThrowExceptionForInvalidIdOrLabel() {
        double[] features = {1.0, 2.0};
        // Проверяем пустые строки, пробелы и null для id и label
        assertThrows(IllegalArgumentException.class, () -> new DataSample("", "cat", features));
        assertThrows(IllegalArgumentException.class, () -> new DataSample(null, "cat", features));
        assertThrows(IllegalArgumentException.class, () -> new DataSample("ID-1", "   ", features));
        assertThrows(IllegalArgumentException.class, () -> new DataSample("ID-1", null, features));
    }

    @Test
    void shouldThrowExceptionForInvalidFeatures() {
        // Проверяем передачу null или пустого массива
        assertThrows(IllegalArgumentException.class, () -> new DataSample("ID-1", "cat", null));
        assertThrows(IllegalArgumentException.class, () -> new DataSample("ID-1", "cat", new double[]{}));
    }

    @Test
    void shouldProtectArrayEncapsulation() {
        double[] inputFeatures = {10.0, 20.0};
        DataSample sample = new DataSample("ID-1", "test", inputFeatures);

        // Попытка изменить исходный массив снаружи
        inputFeatures[0] = 999.0;
        // Среднее не должно измениться (остается (10+20)/2 = 15)
        assertEquals(15.0, sample.calculateMeanFeature(), 1e-9);

        // Попытка изменить массив через геттер
        double[] hackedFeatures = sample.getFeatures();
        hackedFeatures[0] = -500.0;
        // Внутреннее состояние все равно защищено копированием
        assertEquals(15.0, sample.calculateMeanFeature(), 1e-9);
    }

    @Test
    void shouldChangeStatusCorrectly() {
        DataSample sample = new DataSample("ID-1", "test", new double[]{1.0});
        sample.changeStatus(SampleStatus.READY);
        assertEquals(SampleStatus.READY, sample.getStatus());
        assertTrue(sample.isReady());
    }
}