package partitioners;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import java.util.Map;

public class StationPartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        String station = (String) key; // Key là tên trạm (SVDT1, SVDT2, SVDT3)

        switch (topic) {
            case "AIR":
                if (station.equals("SVDT1"))
                    return 0; // Partition 0 cho SVDT1
                if (station.equals("SVDT3"))
                    return 1; // Partition 1 cho SVDT3
                break; // Nếu không phải SVDT1 hoặc SVDT3, không hợp lệ
            case "EARTH":
                if (station.equals("SVDT3"))
                    return 0; // Partition 0 cho SVDT3
                break; // Nếu không phải SVDT3, không hợp lệ
            case "WATER":
                if (station.equals("SVDT2"))
                    return 0; // Partition 0 cho SVDT2
                break; // Nếu không phải SVDT2, không hợp lệ
        }

        // Nếu station không hợp lệ, đặt vào partition mặc định (có thể là partition 0)
        return 0;
    }

    @Override
    public void configure(Map<String, ?> configs) {
        // Không cần cấu hình đặc biệt
    }

    @Override
    public void close() {
        // Không cần xử lý gì khi đóng
    }
}
