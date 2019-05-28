package club.eugeneliu.trade.db;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class Generator {
    public static void main(String[] args) {
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor("EugeneLiu");
        globalConfig.setOutputDir("/home/eugeneliu/EEugeneSoft/EugeneLoan/trade/src/main/java");
        globalConfig.setFileOverride(false);
        globalConfig.setActiveRecord(true);
        globalConfig.setEnableCache(false);
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(false);

        autoGenerator.setGlobalConfig(globalConfig);
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型:" + fieldType);
                return super.processTypeConvert(fieldType);
            }
        });
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("liuyichen");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/qingsongdai?useUnicode=true&autoReconnect=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&useSSL=false");
        autoGenerator.setDataSource(dataSourceConfig);

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.nochange);
        strategyConfig.setInclude(new String[]{"configuration","borrow_money_flow","repay_money_flow","borrower_account","depository","intend_borrow","intend_lend","lender_account","recharge_record","trade","withdraw_record"});

        autoGenerator.setStrategy(strategyConfig);

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("club.eugeneliu.trade");//设置生成类class的包package
        autoGenerator.setPackageInfo(packageConfig);

        autoGenerator.execute();//Mybatis-Plus生成代码过程使用了构造器模式
    }
}
