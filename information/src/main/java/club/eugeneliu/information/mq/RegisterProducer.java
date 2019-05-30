package club.eugeneliu.information.mq;


import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class RegisterProducer {

    private final Producer<String,String > producer;
    public final static String TOPIC = "register";

    private RegisterProducer(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers","192.168.0.163:9092");
        properties.put("acks", "all");
        properties.put("retries",0);
        properties.put("batch.size",16384);
        properties.put("linger.ms",1);
        properties.put("buffer.memory",33554432);
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<String, String>(properties);
    }

    private void startProduce(){
        for(int i =0;i<10;i++){
            String key = "key--" + i;
            String data = "value--"+i;
            producer.send(new ProducerRecord<>(TOPIC, key, data), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.println("消息发送完成");//消息发送完成，不管成功与否，消息发送步骤完成后触发回调函数
                }
            });
        }
        producer.close();
    }



    public static void main(String[] args) {
        new RegisterProducer().startProduce();
    }
}
