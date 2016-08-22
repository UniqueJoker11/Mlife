package colin.app.service.mlife.config;

import colin.app.service.mlife.interceptor.MlifeAuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

/**
 * Created by joker on 16/8/19.
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // MlifeAuthInterceptor 用户认证拦截
        registry.addInterceptor(new MlifeAuthInterceptor()).addPathPatterns("/mlife/admin/*");
        super.addInterceptors(registry);
    }


}
