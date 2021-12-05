package org.juhewu.sms.spring.boot.actuator;

import java.util.List;

import org.juhewu.sms.SmsAccountLocator;
import org.juhewu.sms.SmsAccountRepository;
import org.juhewu.sms.SmsChannel;
import org.juhewu.sms.spring.boot.autoconfigure.SmsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 短信监控自动配置
 *
 * @author duanjw
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(SmsChannel.class)
@ConditionalOnAvailableEndpoint(endpoint = SmsEndpoint.class)
@AutoConfigureAfter(SmsAutoConfiguration.class)
public class SmsActuatorAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SmsEndpoint smsEndpoint(List<SmsChannel> smsChannels, SmsAccountLocator smsAccountLocator, SmsAccountRepository smsAccountRepository) {
        return new SmsEndpoint(smsChannels, smsAccountLocator, smsAccountRepository);
    }
}
