package hello.kafka;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;

public class App {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        var p = new Properties();
        p.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        try(var admin = Admin.create(p)) {
            var newTopic = new NewTopic("hellojava", 1, (short)1);
            CreateTopicsResult result = admin.createTopics(Arrays.asList(newTopic));
            Map<String, KafkaFuture<Void>> m = result.values();
            Future<Void> future = m.get("hellojava");
            future.get();
        }
    }
}
