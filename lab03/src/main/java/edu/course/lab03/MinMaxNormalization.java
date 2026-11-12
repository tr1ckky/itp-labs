package edu.course.lab03;

public class MinMaxNormalization implements NormalizationStrategy {

    @Override
    public double[] normalize(double[] values) {
        if (values == null) {
            throw new IllegalArgumentException("Входной массив не может быть null");
        }
        if (values.length == 0) {
            return new double[0];
        }

        double min = values[0];
        double max = values[0];

        for (double v : values) {
            if (v < min) min = v;
            if (v > max) max = v;
        }

        double[] result = new double[values.length];
        double range = max - min;

        for (int i = 0; i < values.length; i++) {
            if (range == 0.0) {
                result[i] = 0.0; // Если все элементы равны, нормализуем в 0
            } else {
                result[i] = (values[i] - min) / range;
            }
        }

        return result;
    }
}