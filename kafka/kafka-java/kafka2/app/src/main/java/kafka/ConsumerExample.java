package kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.*;

import java.io.*;
import java.net.InetAddress;
import java.nio.file.*;
import java.time.Duration;
import java.util.*;

public class ConsumerExample {

    public static void main(final String[] args) throws IOException {
        Properties config = new Properties();
        config.put("client.id", InetAddress.getLocalHost().getHostName());
        config.put("bootstrap.servers", "kafka:9092");
        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-java-getting-started1");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        var consumer = new KafkaConsumer<String, String>(config);
        consumer.subscribe(List.of("hello"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofDays(1));
            System.out.println(records);
            consumer.commitSync();
          }
        

    }

}