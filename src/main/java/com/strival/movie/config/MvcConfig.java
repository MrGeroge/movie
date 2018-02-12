package com.strival.movie.config;

import com.strival.movie.dao.BuoyDao;
import com.strival.movie.dao.SponsorDao;
import com.strival.movie.interceptor.*;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.Arrays;
import java.util.Properties;

/**
 * Author:zhangyu
 * create on 15/11/8.
 */

@Configuration
@EnableTransactionManagement
@ImportResource("classpath:daos.xml")
@ComponentScan(basePackages = "com.strival.movie.controller")
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private BuoyDao buoyDao;
    @Autowired
    private SponsorDao sponsorDao;
    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Bean
    public TaskExecutor taskExecutor()
    {
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(20);

        return executor;
    }

    @Bean
    public JavaMailSender mailSender()
    {
        JavaMailSenderImpl sender=new JavaMailSenderImpl();
        sender.setHost(WebConstants.Email.HOST);
        sender.setUsername(WebConstants.Email.USERNAME);
        sender.setPassword(WebConstants.Email.PASSWORD);
        sender.setPort(WebConstants.Email.PORT);

        if(WebConstants.Email.SSL){
            Properties properties=new Properties();
            properties.put("mail.smtp.auth",true);
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            sender.setJavaMailProperties(properties);
        }

        return sender;
    }

    @Bean
    public DozerBeanMapper initDozerBeanMapper(){
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList("dozer-mappers.xml"));

        return mapper;
    }

   @Override
    public void addInterceptors(InterceptorRegistry registry) {
       // registry.addInterceptor(new ConstantsInterceptor());
        registry.addInterceptor(new LocaleChangeInterceptor());
        registry.addInterceptor(new CROSInterceptor());
       // registry.addInterceptor(new AdminAuthInteceptor());
      // registry.addInterceptor(new AccountAuthInteceptor()).addPathPatterns("/index", "/account/**").excludePathPatterns("/account/login","/account/register");
       //registry.addInterceptor(new AccountAuthInteceptor()).addPathPatterns("/**").excludePathPatterns("/account/login","/account/register","/admin/**","/maplight/get","/movie/name/get","/upload/img","/upload/form");
       registry.addInterceptor(new AccountAuthInteceptor()).addPathPatterns("/index","/account/logout","/account/password","/account/profile","/account/form/save","/account/form/apply","/account/user_center/user_apply","/account/my/form/save","/account/my/form/apply","/event/**", "/filmtest/**", "/media_center/**", "/service/**", "/showing/**", "/training/**", "/user_center/**", "/venture_capital/**", "/keyword/search_result");
       //registry.addInterceptor(new AccountAuthInteceptor()).addPathPatterns("/index","/","/account/logout","/account/password","/account/profile","/account/form/save","/account/form/apply","/account/user_center/user_apply","/account/my/form/save","/account/my/form/apply","/event/**", "/filmtest/**", "/media_center/**", "/service/**", "/showing/**", "/training/**", "/user_center/**", "/venture_capital/**", "/keyword/search_result");

       BuoyAndSponsorInterceptor buoyAndSponsorInterceptor=new BuoyAndSponsorInterceptor();
        buoyAndSponsorInterceptor.setBuoyDao(buoyDao);
        buoyAndSponsorInterceptor.setDozerBeanMapper(dozerBeanMapper);
        buoyAndSponsorInterceptor.setSponsorDao(sponsorDao);
        registry.addInterceptor(buoyAndSponsorInterceptor).addPathPatterns("/event/**", "/filmtest/**", "/media_center/**", "/service/**", "/showing/**", "/training/**", "/user_center/**", "/venture_capital/**", "/index", "/keyword/search_result", "/account/user_center/user_apply");
    }

    /**
     * 定义静态资源访问路径
     * */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
        registry.addResourceHandler("/views/**").addResourceLocations("/views/");
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    /**same as <mvc:default-servlet-handler/>*/
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

        //配置thymeleaf为模版引擎
        ServletContextTemplateResolver tplResolver=new ServletContextTemplateResolver();
        tplResolver.setPrefix("/WEB-INF/template/");
        tplResolver.setSuffix(".html");
        tplResolver.setCharacterEncoding("UTF-8");
        tplResolver.setTemplateMode("HTML5");
        //TODO:发布时候需要修改缓存时间
        tplResolver.setCacheTTLMs(1L);


        SpringTemplateEngine engine=new SpringTemplateEngine();
        engine.setTemplateResolver(tplResolver);

        ThymeleafViewResolver viewResolver=new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(engine);
        viewResolver.setCharacterEncoding("UTF-8");
        registry.viewResolver(viewResolver);
    }

    @Bean(name = "localeResolver")
    public CookieLocaleResolver buidLocaleResolver(){
        CookieLocaleResolver localeResolver=new CookieLocaleResolver();
        localeResolver.setDefaultLocale(new java.util.Locale("en"));
        return localeResolver;
    }

    @Bean(name = "multipartResolver")
    public StandardServletMultipartResolver initMultipartResolver(){
        StandardServletMultipartResolver resolver=new StandardServletMultipartResolver();
        //resolver.setDefaultEncoding("UTF-8");
        //resolver.setMaxUploadSize(512000);
        return resolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/event/bingshi_lab").setViewName(front("event/bingshi_lab"));
        registry.addViewController("/event/cinematheque").setViewName(front("event/cinematheque"));
        registry.addViewController("/event/exhibition").setViewName(front("event/exhibition"));
        registry.addViewController("/event/longyue_plan").setViewName(front("event/longyue_plan"));
        registry.addViewController("/filmtest/about").setViewName(front("filmtest/about"));
        registry.addViewController("/filmtest/comp_regulations").setViewName(front("filmtest/comp_regulations"));
        registry.addViewController("/filmtest/volunteer_manual").setViewName(front("filmtest/volunteer_manual"));
        registry.addViewController("/filmtest/youth").setViewName(front("filmtest/youth"));
        registry.addViewController("/media_center/download_center").setViewName(front("media_center/download_center"));
        registry.addViewController("/media_center/vision_system").setViewName(front("media_center/vision_system"));
        registry.addViewController("/service/diet").setViewName(front("service/diet"));
        registry.addViewController("/service/exhibition_venue").setViewName(front("service/exhibition_venue"));
        registry.addViewController("/service/hotel").setViewName(front("service/hotel"));
        registry.addViewController("/service/keepsake").setViewName(front("service/keepsake"));
        registry.addViewController("/service/manual_download").setViewName(front("service/manual_download"));
        registry.addViewController("/service/ticketing").setViewName(front("service/ticketing"));
        registry.addViewController("/service/tips").setViewName(front("service/tips"));
        registry.addViewController("/service/transport_line").setViewName(front("service/transport_line"));
        registry.addViewController("/training/student").setViewName(front("training/student"));
        registry.addViewController("/training/training_introduction").setViewName(front("training/training_introduction"));
        registry.addViewController("/venture_capital/introduction").setViewName(front("venture_capital/introduction"));
        registry.addViewController("/venture_capital/user_rights").setViewName(front("venture_capital/user_rights"));
    }

    private String front(String view){
        return "frontend/"+view;
    }

}
