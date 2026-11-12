package edu.course.lab03;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

class FeatureProcessorTest {

    @Test
    void minMaxNormalizationAppliesCorrectly() {
        NormalizationStrategy strategy = new MinMaxNormalization();
        FeatureProcessor processor = new FeatureProcessor(strategy);

        double[] input = {10.0, 20.0, 30.0};
        double[] expected = {0.0, 0.5, 1.0};

        double[] result = processor.process(input);

        assertArrayEquals(expected, result, 1e-9);
    }

    @Test
    void minMaxNormalizationHandlesIdenticalValues() {
        NormalizationStrategy strategy = new MinMaxNormalization();
        FeatureProcessor processor = new FeatureProcessor(strategy);

        double[] input = {5.0, 5.0, 5.0};
        double[] expected = {0.0, 0.0, 0.0};

        double[] result = processor.process(input);

        assertArrayEquals(expected, result, 1e-9);
    }

    @Test
    void meanCenteringNormalizationAppliesCorrectly() {
        NormalizationStrategy strategy = new MeanCenteringNormalization();
        FeatureProcessor processor = new FeatureProcessor(strategy);

        double[] input = {1.0, 2.0, 3.0};
        // Среднее = 2.0, ожидаем: 1-2=-1, 2-2=0, 3-2=1
        double[] expected = {-1.0, 0.0, 1.0};

        double[] result = processor.process(input);

        assertArrayEquals(expected, result, 1e-9);
    }

    @Test
    void meanCenteringNormalizationHandlesSingleElement() {
        NormalizationStrategy strategy = new MeanCenteringNormalization();
        FeatureProcessor processor = new FeatureProcessor(strategy);

        double[] input = {42.0};
        double[] expected = {0.0};

        double[] result = processor.process(input);

        assertArrayEquals(expected, result, 1e-9);
    }

    @Test
    void processorAllowsStrategyReplacementWithoutChangingProcessorCode() {
        double[] input = {2.0, 4.0, 6.0};

        // Используем первую стратегию
        FeatureProcessor minMaxProcessor = new FeatureProcessor(new MinMaxNormalization());
        assertArrayEquals(new double[]{0.0, 0.5, 1.0}, minMaxProcessor.process(input), 1e-9);

        // Заменяем стратегию на лету через пересоздание процессора с другой реализацией
        FeatureProcessor meanCenterProcessor = new FeatureProcessor(new MeanCenteringNormalization());
        assertArrayEquals(new double[]{-2.0, 0.0, 2.0}, meanCenterProcessor.process(input), 1e-9);
    }

    @Test
    void inputDataIsNotModifiedByProcessor() {
        double[] input = {10.0, 20.0, 30.0};
        double[] inputCopy = {10.0, 20.0, 30.0};

        FeatureProcessor processor = new FeatureProcessor(new MinMaxNormalization());
        processor.process(input);

        assertArrayEquals(inputCopy, input, 1e-9);
    }
}