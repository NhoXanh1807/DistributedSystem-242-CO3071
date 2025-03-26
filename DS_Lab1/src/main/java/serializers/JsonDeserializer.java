package serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import java.util.Map;

public class JsonDeserializer<T> implements Deserializer<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Class<T> type;

    public static final String VALUE_CLASS_NAME_CONFIG = "value.deserializer.class";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        String className = (String) configs.get(VALUE_CLASS_NAME_CONFIG);
        try {
            this.type = (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found for deserialization: " + className, e);
        }
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        try {
            if (data == null) return null;
            return objectMapper.readValue(data, type);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing object", e);
        }
    }

    @Override
    public void close() {
    }
}
