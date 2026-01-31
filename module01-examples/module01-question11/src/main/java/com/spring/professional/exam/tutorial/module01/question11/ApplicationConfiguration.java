package com.spring.professional.exam.tutorial.module01.question11;

import com.spring.professional.exam.tutorial.module01.question11.beans.SpringBean7;
import com.spring.professional.exam.tutorial.module01.question11.beans.SpringBean8;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@ComponentScan
//@ComponentScan(lazyInit = true)
@Configuration
@Lazy
public class ApplicationConfiguration {
    @Bean
    //@Lazy
    public SpringBean7 springBean7() {
        return new SpringBean7();
    }

    @Bean
    public SpringBean8 springBean8() {
        return new SpringBean8();
    }
}
