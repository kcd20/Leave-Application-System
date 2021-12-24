package nus.iss.ca.leave_application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


/**
 * @Author Fusheng Tan
 * @Version 1.0
 */
@Configuration
/*@EnableWebSecurity*/
public class SecurityConfig{
   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home","/staffLogin","/adminLogin","/home/authenticate").permitAll().and().formLogin().loginPage("/home").permitAll();
    }*/
    
    @Bean
    public JavaMailSender javaMailSender() { 
    	JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
         return javaMailSender;
    }
}
