package com.addnumbers.service.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.addnumbers.service.core.QuestionService;

@Configuration
public class AddNumbersServiceConfiguration {

    @Bean
    QuestionService questionService() {
        return new QuestionService();
    }

}
