package kafka;

import java.net.InetAddress;
import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class App {
    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        config.put("client.id", InetAddress.getLocalHost().getHostName());
        config.put("bootstrap.servers", "kafka:9092");
        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("acks", "all");
        var record = new ProducerRecord<>("hello2", "k", "v");
        final Producer<String, String> producer = new KafkaProducer<>(config);
        Future<RecordMetadata> future = producer.send(record);
        RecordMetadata r = future.get();
        future = producer.send(record);
        r = future.get();
        future = producer.send(record);
        r = future.get();
        System.out.println(r);
    }
}
