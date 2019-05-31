package club.eugeneliu.trade.mq;

import club.eugeneliu.trade.entity.Borrower_account;
import club.eugeneliu.trade.entity.Depository;
import club.eugeneliu.trade.entity.Lender_account;
import club.eugeneliu.trade.service.IBorrower_accountService;
import club.eugeneliu.trade.service.IDepositoryService;
import club.eugeneliu.trade.service.ILender_accountService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;

public class RegisterConsumer implements Runnable {

    private final KafkaConsumer<String, String> kafkaConsumer;
    public static final String BORROWER = "0";
    public static final String LENDER = "1";

    @Autowired
    IDepositoryService iDepositoryService;

    @Autowired
    ILender_accountService iLender_accountService;

    @Autowired
    IBorrower_accountService iBorrower_accountService;


    private RegisterConsumer() {
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

    //使用身份证开第三方存管账户和资金账户
    private Depository openDeposit(String id_card) {

        Depository depository = new Depository();

        BufferedReader bufferedReader = null;
        int temp_content = 0;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File("/home/eugeneliu/EEugeneSoft/EugeneLoan/tmp/depository")));
            temp_content = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(new File("/home/eugeneliu/EEugeneSoft/EugeneLoan/tmp/depository")));
            bufferedWriter.write((temp_content + 1));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        depository.setDepository_account(String.valueOf(temp_content));
        depository.setFunds_account(String.valueOf(temp_content));

        return depository;
    }

    private int getCreditScore(String id_card) {//信用范围0-1000
        return 500;
    }

    private int getTotalLimit(int score) {
        if (score < 100) {
            return 0;
        } else if (score < 200) {
            return getFiftyNumber(1 * score);
        } else if (score < 300) {
            return getFiftyNumber(2 * score);
        } else if (score < 400) {
            return getFiftyNumber(3 * score);
        } else if (score < 500) {
            return getFiftyNumber(4 * score);
        } else if (score < 600) {
            return getFiftyNumber(5 * score);
        } else if (score < 700) {
            return getFiftyNumber(6 * score);
        } else if (score < 800) {
            return getFiftyNumber(7 * score);
        } else if (score < 900) {
            return getFiftyNumber(8 * score);
        } else if (score < 1000) {
            return getFiftyNumber(9 * score);
        } else if (score == 1000) {
            return 10000;
        } else {
            return 0;
        }
    }

    private int getFiftyNumber(int number) {
        int base = 50;
        for (int i = 3; i < 200; i++) {
            int temp = base * i;
            if (temp >= number) {
                return temp;
            }
        }
        return 0;
    }

    public void run() {
        kafkaConsumer.subscribe(Arrays.asList("register"));
        while (true) {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                String id_card = consumerRecord.value();
                if (consumerRecord.key().equals(BORROWER)) {
                    //执行开户操作，并将开户结果保存到数据库中
                    Depository depository = openDeposit(id_card);
                    boolean result1 = iDepositoryService.insertDepository(depository);
                    Borrower_account borrower_account = new Borrower_account();
                    int credit_score = getCreditScore(id_card);
                    int total_limit = getTotalLimit(credit_score);
                    borrower_account.setAccount_balance(0.0);
                    borrower_account.setAvailable_limit((double) total_limit);
                    borrower_account.setId_card(id_card);
                    borrower_account.setTotal_limit((double) total_limit);
                    borrower_account.setFunds_account(depository.getFunds_account());
                    borrower_account.setCredit_score(credit_score);
                    borrower_account.setBorrowed_money(0.0);
                    boolean result2 = iBorrower_accountService.insertBorrower(borrower_account);
                    if (result1 && result2) {
                        System.out.println(id_card + "注册成功借入者身份");
                    }
                } else if (consumerRecord.key().equals(LENDER)) {
                    //执行开户操作，并将开户结果保存到数据库中
                    Depository depository = openDeposit(id_card);
                    boolean result1 = iDepositoryService.insertDepository(depository);
                    Lender_account lender_account = new Lender_account();
                    lender_account.setFunds_account(depository.getFunds_account());
                    lender_account.setId_card(id_card);
                    lender_account.setAccount_balance(0.00);
                    lender_account.setCurrent_income(0.00);
                    lender_account.setExpected_income(0.00);
                    lender_account.setLent_money(0.00);
                    boolean result2 = iLender_accountService.insertLender(lender_account);
                    if (result1 && result2) {
                        System.out.println(id_card + "注册成功借出者身份");
                    }
                }
//                System.out.printf("offset = %d , key = %s , value = %s%n",consumerRecord.offset(),consumerRecord.key(),consumerRecord.value());
            }
        }
    }

}
