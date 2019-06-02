package club.eugeneliu.information.mq;

import java.util.Properties;

import org.apache.kafka.clients.producer.*;


/**
 * Author  : RandySun
 * Date    : 2017-08-13  16:23
 * Comment :
 */
public class TestProducer3 {

    private static final String TOPIC = "register";
//    private final Producer<String, String> producer;
//
//    public TestProducer3() {
//        Properties properties = new Properties();
//        properties.put("bootstrap.servers", "192.168.0.163:9092");
//        properties.put("acks", "all");
//        properties.put("retries", 0);
//        properties.put("batch.size", 16384);
//        properties.put("linger.ms", 1);
//        properties.put("buffer.memory", 33554432);
//        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        producer = new KafkaProducer<String, String>(properties);
//    }


    public void send(String identity, String id_card) {
        Producer<String, String> producer;
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

        producer.send(new ProducerRecord<>(TOPIC, identity, id_card), new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                System.out.println("消息发送完成");
            }
        });
        producer.flush();
    }

    public static void main(String[] args) {
        new TestProducer3().send("0", "370602199801115212");
    }
}