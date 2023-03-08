package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.*;
import java.util.*;

// batch 테스트
public class App3 {
    public static void main(String[] args) {
        // Kafka Producer 설정
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("max.request.size", "2"); // 최대 메시지 크기를 2로 설정
        props.put("batch.size", "1000"); // Batch 크기를 1000으로 설정
        props.put("linger.ms", "10"); // 10ms 대기 후 Batch 전송

        // Kafka Producer 생성
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        // 메시지 전송
        for (int i = 0; i < 5; i++) {
            String messageValue = "message" + i;
            ProducerRecord<String, String> record = new ProducerRecord<>("test-topic", messageValue);
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception == null) {
                        System.out.printf("Produced record at offset %d to topic %s%n",
                                metadata.offset(), metadata.topic());
                    } else {
                        exception.printStackTrace();
                    }
                }
            });
        }

        // Kafka Producer 종료
        producer.close();
    }
}
