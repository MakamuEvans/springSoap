package com.last.last.config;

import com.last.last.api.ArticleClient;
import com.last.last.api.MathClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapClient {

    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("com.medium.article");
        return jaxb2Marshaller;
    }

    @Bean
    public ArticleClient articleClient(Jaxb2Marshaller jaxb2Marshaller){
        ArticleClient articleClient = new ArticleClient();
        articleClient.setDefaultUri("http://localhost:8080/ws/article");
        articleClient.setMarshaller(jaxb2Marshaller);
        articleClient.setUnmarshaller(jaxb2Marshaller);
        return articleClient;
    }

    @Bean
    public MathClient mathClient(Jaxb2Marshaller jaxb2Marshaller){
        MathClient mathClient = new MathClient();
        mathClient.setDefaultUri("https://cdata.free.beeceptor.com");
        mathClient.setMarshaller(jaxb2Marshaller);
        mathClient.setUnmarshaller(jaxb2Marshaller);
        return mathClient;
    }
}
