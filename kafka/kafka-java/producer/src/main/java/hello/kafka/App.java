package hello.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class App {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        var p = new Properties();
        p.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        p.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "codeshow.javaclient");
        p.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class.getName());
        p.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class.getName());
        p.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        try(var producer = new KafkaProducer<String, String>(p)) {
            final ProducerRecord<String, String> record = new ProducerRecord<>("hello", "a", "a");
            Future<RecordMetadata> f = producer.send(record);
            f.get();
        }
    }
}
