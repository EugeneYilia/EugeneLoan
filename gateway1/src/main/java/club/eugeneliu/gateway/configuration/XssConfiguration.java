//package club.eugeneliu.gateway.configuration;
//
//import club.eugeneliu.gateway.filter.XssFilter;
//import com.google.common.collect.Maps;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Map;
//
//@Configuration
//public class XssConfiguration {
//    @Bean
//    public FilterRegistrationBean xssFilterRegistrationBean(){
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new XssFilter());
////        filterRegistrationBean.setOrder(1);
//        filterRegistrationBean.setEnabled(true);
//        filterRegistrationBean.addUrlPatterns("/*");
//        Map<String,String> initParameters = Maps.newHashMap();
//        initParameters.put("excludes","/favicon.ico,/img/*,/js/*,/css/*,/assets/*");
//        initParameters.put("isIncludeRichText","true");
//        filterRegistrationBean.setInitParameters(initParameters);
//        return filterRegistrationBean;
//    }
//}
