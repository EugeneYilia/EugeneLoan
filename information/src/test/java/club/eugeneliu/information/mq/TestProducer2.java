package club.eugeneliu.information.mq;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

public class TestProducer2 {
    public static void main(String[] args) {
        Properties props = new Properties();
//      props.put("zookeeper.connect", "10.16.0.200:2181");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("producer.type", "async");//默认是sync
        props.put("compression.codec", "1");
        props.put("metadata.broker.list", "192.168.0.163:9092");
        ProducerConfig config = new ProducerConfig(props);

        Producer<String, Object> producer = new Producer<String, Object>(config);
        KeyedMessage<String, Object> message =
                new KeyedMessage<String, Object>("register", "hello world");

        producer.send(message);
    }
}
