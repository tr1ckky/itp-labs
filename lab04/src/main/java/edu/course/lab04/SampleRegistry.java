package edu.course.lab04;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SampleRegistry {
    private final Map<SampleId, DataSample> samples = new HashMap<>();

    public DataSample put(SampleId id, DataSample sample) {
        SampleId validId = Objects.requireNonNull(id, "Идентификатор наблюдения не должен быть null");
        DataSample validSample = Objects.requireNonNull(sample, "Данные наблюдения не должны быть null");
        return samples.put(validId, validSample);
    }

    public DataSample get(SampleId id) {
        SampleId validId = Objects.requireNonNull(id, "Идентификатор наблюдения не должен быть null");
        return samples.get(validId);
    }

    public DataSample remove(SampleId id) {
        SampleId validId = Objects.requireNonNull(id, "Идентификатор наблюдения не должен быть null");
        return samples.remove(validId);
    }

    public boolean containsKey(SampleId id) {
        SampleId validId = Objects.requireNonNull(id, "Идентификатор наблюдения не должен быть null");
        return samples.containsKey(validId);
    }

    public int size() {
        return samples.size();
    }
}