package edu.course.lab04;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class SampleRegistryTest {

    @Test
    void twoEqualKeysFindTheSameSample() {
        SampleRegistry registry = new SampleRegistry();
        SampleId putId = new SampleId("mnist", 10);
        SampleId lookupId = new SampleId("mnist", 10);
        DataSample sample = new DataSample("digit_3", 0.1, 0.8, 0.2);

        registry.put(putId, sample);

        assertEquals(sample, registry.get(lookupId));
    }

    @Test
    void puttingWithEqualKeyReplacesValueAndKeepsSize() {
        SampleRegistry registry = new SampleRegistry();
        SampleId id = new SampleId("cifar10", 5);
        DataSample firstSample = new DataSample("cat", 1.0, 2.0);
        DataSample secondSample = new DataSample("dog", 3.0, 4.0);

        registry.put(id, firstSample);
        registry.put(new SampleId("cifar10", 5), secondSample);

        assertEquals(1, registry.size());
        assertEquals(secondSample, registry.get(id));
    }

    @Test
    void twoUnequalKeysWithSameHashCodeStoreDistinctValues() {
        Map<CollidingKey, String> map = new HashMap<>();
        CollidingKey firstKey = new CollidingKey("setA", 1);
        CollidingKey secondKey = new CollidingKey("setB", 2);

        assertEquals(firstKey.hashCode(), secondKey.hashCode());
        assertNotEquals(firstKey, secondKey);

        map.put(firstKey, "Наблюдение 1");
        map.put(secondKey, "Наблюдение 2");

        assertEquals(2, map.size());
        assertEquals("Наблюдение 1", map.get(firstKey));
        assertEquals("Наблюдение 2", map.get(secondKey));
    }

    @Test
    void equalsReturnsFalseForNull() {
        SampleId id = new SampleId("imagenet", 1);

        assertFalse(id.equals(null));
    }

    @Test
    void equalsReturnsFalseForDifferentType() {
        SampleId id = new SampleId("imagenet", 1);

        assertFalse(id.equals("imagenet:1"));
    }

    @Test
    void mutatingKeyAfterInsertBreaksLookupInHashMap() {
        Map<MutableSampleId, DataSample> map = new HashMap<>();
        MutableSampleId key = new MutableSampleId("train", 1);
        DataSample sample = new DataSample("class_a", 0.5, 0.5);

        map.put(key, sample);
        assertEquals(sample, map.get(key));

        key.setSampleNumber(999);

        assertNull(map.get(key));
    }

    @Test
    void removeDeletesEntryAndUpdatesSize() {
        SampleRegistry registry = new SampleRegistry();
        SampleId id = new SampleId("speech", 7);
        DataSample sample = new DataSample("hello", 0.25);

        registry.put(id, sample);
        DataSample removed = registry.remove(id);

        assertEquals(sample, removed);
        assertFalse(registry.containsKey(id));
        assertEquals(0, registry.size());
    }

    private static final class CollidingKey {
        private final String datasetName;
        private final int sampleNumber;

        private CollidingKey(String datasetName, int sampleNumber) {
            this.datasetName = datasetName;
            this.sampleNumber = sampleNumber;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof CollidingKey other)) {
                return false;
            }
            return sampleNumber == other.sampleNumber && Objects.equals(datasetName, other.datasetName);
        }

        @Override
        public int hashCode() {
            return 42;
        }
    }

    private static final class MutableSampleId {
        private String datasetName;
        private int sampleNumber;

        private MutableSampleId(String datasetName, int sampleNumber) {
            this.datasetName = datasetName;
            this.sampleNumber = sampleNumber;
        }

        private void setSampleNumber(int sampleNumber) {
            this.sampleNumber = sampleNumber;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof MutableSampleId other)) {
                return false;
            }
            return sampleNumber == other.sampleNumber && Objects.equals(datasetName, other.datasetName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(datasetName, sampleNumber);
        }
    }
}