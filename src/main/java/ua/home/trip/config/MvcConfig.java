package ua.home.trip.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.text.SimpleDateFormat;
import java.util.List;

@Configuration
@ComponentScan(basePackages = { "ua.home.trip.controller", "ua.home.trip.service", "ua.home.trip.repository" })
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(createJacksonConverter());
        addDefaultHttpMessageConverters(converters);
    }

    private MappingJacksonHttpMessageConverter createJacksonConverter() {
        MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
        converter.getSupportedMediaTypes().add(MediaType.APPLICATION_JSON);
        // converter.setObjectMapper(mapper);
        return converter;
    }

    @Bean
    public ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        return mapper;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public RequestMappingHandlerAdapter createHandler() {
        RequestMappingHandlerAdapter handler = new RequestMappingHandlerAdapter();
        return handler;
    }

    @Bean
    public RequestMappingHandlerMapping createAnnotationMapping() {
        RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
        mapping.setAlwaysUseFullPath(true);
        return mapping;
    }

}
