package edu.course.lab03;

public class FeatureProcessor {
    private final NormalizationStrategy normalizationStrategy;

    public FeatureProcessor(NormalizationStrategy normalizationStrategy) {
        if (normalizationStrategy == null) {
            throw new IllegalArgumentException("Стратегия нормализации не может быть null");
        }
        this.normalizationStrategy = normalizationStrategy;
    }

    public double[] process(double[] values) {
        return normalizationStrategy.normalize(values);
    }

    public double[] processSample(DataSample sample) {
        if (sample == null) {
            throw new IllegalArgumentException("Объект DataSample не может быть null");
        }
        return normalizationStrategy.normalize(sample.getValues());
    }
}