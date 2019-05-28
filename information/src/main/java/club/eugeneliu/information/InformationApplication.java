package club.eugeneliu.information;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("club.eugeneliu.information.mapper")
public class InformationApplication {
    public static void main(String[] args) {
        SpringApplication.run(InformationApplication.class,args);
    }
}
