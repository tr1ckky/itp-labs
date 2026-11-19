package edu.course.lab04;

import java.util.Arrays;
import java.util.Objects;

public final class DataSample {
    private final String label;
    private final double[] features;

    public DataSample(String label, double... features) {
        this.label = Objects.requireNonNull(label, "Метка не должна быть null");
        Objects.requireNonNull(features, "Признаки не должны быть null");
        this.features = Arrays.copyOf(features, features.length);
    }

    public String getLabel() {
        return label;
    }

    public double[] getFeatures() {
        return Arrays.copyOf(features, features.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataSample other)) {
            return false;
        }
        return Objects.equals(label, other.label) && Arrays.equals(features, other.features);
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(label) + Arrays.hashCode(features);
    }

    @Override
    public String toString() {
        return "DataSample{label='" + label + "', features=" + Arrays.toString(features) + "}";
    }
}