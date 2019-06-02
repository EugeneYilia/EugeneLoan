package club.eugeneliu.information.mq;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class TestConsumer {

    private final static KafkaConsumer<String, String> kafkaConsumer;
    public static final String BORROWER = "0";
    public static final String LENDER = "1";


    static {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.0.163:9092");
        properties.put("group.id", "register");//消费者指定组，名称可以随意，注意相同消费组中的消费者只能对同一个分区消费一次
        properties.put("enable.auto.commit", "true");//是否启动自动提交，默认为true
        properties.put("auto.commit.interval.ms", "1000");//自动提交间隔时间1s
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");//key反序列化指定类
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");//value反序列化指定类，注意生产者与消费者要保持一致，否则解析出问题
        //消费者对象
        kafkaConsumer = new KafkaConsumer<String, String>(properties);
    }


    public static void main(String... args) {
        kafkaConsumer.subscribe(Arrays.asList("register"));
        while (true) {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                String id_card = consumerRecord.value();
                System.out.println("收到" + id_card + "的开户消息");
            }
        }
    }
}

