package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class App2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // Kafka broker 설정
        String bootstrapServers = "kafka:9092";

        // Producer 설정
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("linger.ms", 1);
        props.put("request.timeout.ms", 10);
        props.put("delivery.timeout.ms", 30);
        props.put("max.block.ms", 50);
        props.put("max.request.size", 2);

        // props.put("auto.create.topics.enable", "false");

        // Producer 인스턴스 생성
        Producer<String, String> producer = new KafkaProducer<>(props);

        // 메시지 발행
        String topic = "my_topic2";
        String message = "Hello, Kafka!";
        var f = producer.send(new ProducerRecord<>(topic, message));
        var r = f.get();
        System.out.println(r);

        // Producer 종료
        producer.close();
    }
}

