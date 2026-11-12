package edu.course.lab03;

import java.util.Arrays;

public class DataSample {
    private final double[] values;

    public DataSample(double[] values) {
        if (values == null) {
            throw new IllegalArgumentException("Массив значений не может быть null");
        }
        this.values = Arrays.copyOf(values, values.length);
    }

    public double[] getValues() {
        return Arrays.copyOf(values, values.length);
    }
}