package club.eugeneliu.log;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

public class LogApplication {

    private final KafkaConsumer<String, String> kafkaConsumer;//日志的类别和内容
    private static final String BASE_PATH = "/home/eugeneliu/EEugeneSoft/EugeneLoan/logs/";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private LogApplication() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.0.163:9092");
        properties.put("group.id", "register");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaConsumer = new KafkaConsumer<String, String>(properties);
    }

    private void startLog() {
        kafkaConsumer.subscribe(Arrays.asList("log"));
        while (true) {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                String filePath = BASE_PATH + consumerRecord.key() + ".log";
                FileWriter fileWriter = null;
                BufferedWriter bufferedWriter = null;
                try {
                    fileWriter = new FileWriter(filePath, true);
                    bufferedWriter = new BufferedWriter(fileWriter);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(simpleDateFormat.format(new Date().getTime()) + "   ");
                    stringBuilder.append(consumerRecord.value());
                    bufferedWriter.write(stringBuilder.toString());
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (bufferedWriter != null) {
                            bufferedWriter.flush();
                            bufferedWriter.close();
                        }
                        if (fileWriter != null) {
                            fileWriter.flush();
                            fileWriter.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new LogApplication().startLog();
    }
}
