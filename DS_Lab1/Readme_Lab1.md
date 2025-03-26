# Hướng dẫn tải và sử dụng DS_Lab1

## Bước 1: Tải và giải nén

Tải gói DS_Lab1 và giải nén vào thư mục mong muốn.

## Bước 2: Chuyển vào thư mục DS_Lab1

```sh
cd DS_Lab1
```

## Bước 3: Cài đặt Maven (nếu chưa có)

```sh
sudo apt install maven
```

## Bước 4: Khởi động Kafka và tạo các topic

Cấu hình server properties theo thiết lập của bạn, sau đó tạo 3 topic như sau:

```sh
kafka-topics.sh --create --topic AIR --partitions 2 --replication-factor 1 --bootstrap-server <kafka_server>
kafka-topics.sh --create --topic EARTH --partitions 1 --replication-factor 1 --bootstrap-server <kafka_server>
kafka-topics.sh --create --topic WATER --partitions 1 --replication-factor 1 --bootstrap-server <kafka_server>
```

### Phân vùng topic:

- **AIR**: Có 2 partition, phục vụ cho **SVDT1** và **SVDT3**.
- **EARTH**: Có 1 partition, phục vụ cho **SVDT3**.
- **WATER**: Có 1 partition, phục vụ cho **SVDT2**.

## Bước 5: Cấu hình Kafka Server trong mã nguồn

Mở các file sau để cập nhật địa chỉ các máy trong Kafka Server:

- **src/main/java/producer/SensorDataProducer.java** (cập nhật `BOOTSTRAP_SERVERS` thành địa chỉ của 3 máy Kafka).
- **src/main/java/consumer/SensorDataConsumer.java** (cập nhật tương tự).

## Bước 6: Build và chạy Producer

```sh
mvn clean package
```

Nếu build thành công, chạy lệnh sau để gửi dữ liệu:

```sh
mvn exec:java -Dexec.mainClass="producer.SensorDataProducer"
```

## Bước 7 (Optional): Kiểm tra dữ liệu bằng Consumer

```sh
mvn exec:java -Dexec.mainClass="consumer.SensorDataConsumer"
```

Lưu ý:

- Hiện tại, file `SensorDataConsumer.java` chỉ hiển thị partition 1 của trạm **SVDT3** trong topic **AIR**.
- Bạn có thể thay đổi `group_id`, topic hoặc lọc thông tin khác để xem dữ liệu mong muốn.

Chúc các bạn áp dụng thành công!
