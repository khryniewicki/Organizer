package config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@ComponentScan(basePackages = { "com.khryniewicki.organizer.main_content.controllers" })
@ComponentScan(basePackages = { "com.khryniewicki.organizer.project_menu.controllers" })
@ComponentScan(basePackages = { "com.khryniewicki.organizer.sign_up.controllers" })

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/layout");
        registry.addViewController("/layout").setViewName("layout");


    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/webjars/**",
                "/img/**",
                "/css/**",
                "/static/js/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/js/");
    }

    //zmiana locale
//    @Bean
//    public LocaleResolver localeResolver() {
//        SessionLocaleResolver slr = new SessionLocaleResolver();
//        slr.setDefaultLocale(Locale.UK);
//        return slr;
//    }
//
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
//        lci.setParamName("lang");
//        return lci;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//    }
//
//    //
//    @Bean
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource
//                = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("classpath:messages");
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
//    }
//
//    @Bean
//    public LocalValidatorFactoryBean getValidator() {
//        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//        bean.setValidationMessageSource(messageSource());
//        return bean;
//    }
}