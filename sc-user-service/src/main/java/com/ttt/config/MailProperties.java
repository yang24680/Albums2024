package com.ttt.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置类注入
 * 方便修改  发件人 email
 */
@Component
@Data
public class MailProperties {

    @Value("${spring.mail.username}")
    private String FORM_EMAIL;

}