package club.eugeneliu.trade.configuration;

import club.eugeneliu.trade.interceptor.CertificationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CertificationInterceptor()).addPathPatterns("/trade/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
