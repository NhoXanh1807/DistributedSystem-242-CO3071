package producer;
import models.AirData;
import models.EarthData;
import models.WaterData;
import serializers.JsonSerializer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.*;
import partitioners.StationPartitioner;
import java.io.*;
import java.util.Properties;

public class SensorDataProducer {
    private static final String BOOTSTRAP_SERVERS = "192.168.239.137:9092,192.168.239.138:9092,192.168.239.139:9092";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, StationPartitioner.class.getName());

        KafkaProducer<String, Object> producer = new KafkaProducer<>(props);

        sendDataFromCSV(producer, "AIR2308.csv", "AIR");
        sendDataFromCSV(producer, "EARTH2308.csv", "EARTH");
        sendDataFromCSV(producer, "WATER2308.csv", "WATER");

        producer.close();
    }

    private static void sendDataFromCSV(KafkaProducer<String, Object> producer, String resourcePath, String topic) {
        try (InputStream inputStream = SensorDataProducer.class.getClassLoader().getResourceAsStream(resourcePath);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            // Kiểm tra nếu file không tồn tại
            if (inputStream == null) {
                throw new FileNotFoundException("Không tìm thấy file trong resources: " + resourcePath);
            }

            String line;
            br.readLine(); // Bỏ qua dòng header

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                Object sensorData = null;
                String station = data[1]; // Lấy station làm key

                if (topic.equals("AIR")) {
                    sensorData = new AirData(data[0], data[1], Double.parseDouble(data[2]), Double.parseDouble(data[3]),
                            Double.parseDouble(data[4]), Double.parseDouble(data[5]), Double.parseDouble(data[6]),
                            Double.parseDouble(data[7]), Double.parseDouble(data[8]), Double.parseDouble(data[9]),
                            Double.parseDouble(data[10]), Double.parseDouble(data[11]), Double.parseDouble(data[12]));
                } else if (topic.equals("EARTH")) {
                    sensorData = new EarthData(data[0], data[1], Double.parseDouble(data[2]),
                            Double.parseDouble(data[3]), Double.parseDouble(data[4]), Double.parseDouble(data[5]),
                            Double.parseDouble(data[6]), Double.parseDouble(data[7]), Double.parseDouble(data[8]),
                            Double.parseDouble(data[9]));
                } else if (topic.equals("WATER")) {
                    sensorData = new WaterData(data[0], data[1], Double.parseDouble(data[2]),
                            Double.parseDouble(data[3]), Double.parseDouble(data[4]), Double.parseDouble(data[5]));
                }

                if (sensorData != null) {
                    ProducerRecord<String, Object> record = new ProducerRecord<>(topic, station, sensorData);
                    producer.send(record, (metadata, exception) -> {
                        if (exception == null) {
                            System.out.println("Sent: " + record.value() + " to partition " + metadata.partition());
                        } else {
                            exception.printStackTrace();
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
