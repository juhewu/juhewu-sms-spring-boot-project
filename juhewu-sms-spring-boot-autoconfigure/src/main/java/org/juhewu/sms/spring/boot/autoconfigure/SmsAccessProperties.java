package org.juhewu.sms.spring.boot.autoconfigure;

import java.util.List;

import org.juhewu.sms.SmsAccount;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 短信账户配置
 *
 * @author duanjw
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "juhewu.sms")
@EnableConfigurationProperties(SmsAccessProperties.class)
@Slf4j
public class SmsAccessProperties {

    private List<SmsAccount> accounts;
    /**
     * 短信账户是否从jdbc读取
     */
    private boolean jdbcAccess;
}
