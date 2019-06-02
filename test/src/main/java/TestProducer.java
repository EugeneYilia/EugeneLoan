import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class TestProducer {
    private final Producer<String, String> producer;
    private static final String TOPIC = "test";

    public TestProducer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.0.163:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<String, String>(properties);
    }

    public  void send(String identity, String id_card) {
        producer.send(new ProducerRecord<>(TOPIC,"dqwfqwfq", "ascaw"), new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                System.out.println("消息发送完成");
            }
        });
    }

    public static void main(String[] args) {
        new TestProducer().send("0","370602199801115212");
    }
}
