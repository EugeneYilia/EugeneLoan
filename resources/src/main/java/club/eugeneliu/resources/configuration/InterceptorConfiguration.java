package club.eugeneliu.resources.configuration;

import club.eugeneliu.resources.interceptor.CertificationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CertificationInterceptor()).addPathPatterns("/resources/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
