package consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class SensorDataConsumer {
    private static final String BOOTSTRAP_SERVERS = "192.168.239.137:9092";
    private static final String TOPIC = "AIR";
    private static final int PARTITION = 1;
    private static final String GROUP_ID = "air_partition1_consumer_group"; // Tên group ID

    public static void main(String[] args) {
        // Thiết lập cấu hình cho consumer
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true"); // Commit offset tự động
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // Đọc từ đầu nếu chưa có offset

        // Tạo Kafka consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // Chỉ định partition cụ thể để đọc
        TopicPartition partition = new TopicPartition(TOPIC, PARTITION);
        consumer.assign(Collections.singletonList(partition));

        System.out.println("[Consumer] Listening on " + TOPIC + " - Partition " + PARTITION);

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("[Consumer] Partition %d - Offset %d: Key=%s, Value=%s%n",
                            record.partition(), record.offset(), record.key(), record.value());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
