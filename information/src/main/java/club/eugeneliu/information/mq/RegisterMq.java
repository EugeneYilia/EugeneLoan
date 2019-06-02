package club.eugeneliu.information.mq;


import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class RegisterMq {

    private static final Producer<String, String> producer;
    private static final String TOPIC = "register";

    static {
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

    public static void send(String identity, String id_card) {
        producer.send(new ProducerRecord<>(TOPIC, identity, id_card));
        producer.flush();
    }

}
