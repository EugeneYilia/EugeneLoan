package club.eugeneliu.trade;

import club.eugeneliu.trade.mq.RegisterConsumer;
import club.eugeneliu.trade.service.IBorrower_accountService;
import club.eugeneliu.trade.service.IDepositoryService;
import club.eugeneliu.trade.service.ILender_accountService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("club.eugeneliu.trade.mapper")
public class TradeApplication {

//    @Autowired
//    static IDepositoryService iDepositoryService;
//
//    @Autowired
//    static ILender_accountService iLender_accountService;
//
//    @Autowired
//    static IBorrower_accountService iBorrower_accountService;

    private static void init() {
//        new Thread(registerConsumer).start();
//        new Thread(new RegisterConsumer(iDepositoryService,iLender_accountService,iBorrower_accountService)).start();
//        System.out.println("注册消费者启动成功");
        new Thread(new RegisterConsumer()).start();
    }

    public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
        init();
    }
}
