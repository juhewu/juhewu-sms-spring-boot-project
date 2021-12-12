package org.juhewu.sms.spring.boot.autoconfigure;

import java.util.List;

import org.juhewu.sms.CompositeSmsAccountLocator;
import org.juhewu.sms.InMemorySmsAccountRepository;
import org.juhewu.sms.SmsAccountLocator;
import org.juhewu.sms.SmsAccountRepository;
import org.juhewu.sms.SmsChannel;
import org.juhewu.sms.SmsChannelFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 短信自动配置
 *
 * @author duanjw
 */
@Configuration
@EnableConfigurationProperties({ SmsAccessProperties.class })
public class SmsAutoConfiguration {

    /**
     * 内存中的短信账户
     *
     * @return InMemorySmsAccountRepository
     */
    @Bean
    @ConditionalOnMissingBean(SmsAccountRepository.class)
    public InMemorySmsAccountRepository inMemorySmsAccountStore() {
        return new InMemorySmsAccountRepository();
    }

    /**
     * 配置文件中的短信账户
     *
     * @param smsAccessProperties 短信账户
     * @return PropertiesSmsAccountLocator
     */
    @Bean
    public PropertiesSmsAccountLocator propertiesSmsAccountLocator(SmsAccessProperties smsAccessProperties) {
        return new PropertiesSmsAccountLocator(smsAccessProperties);
    }

    /**
     * 短信账户定位器，包括所有的短信定位器
     *
     * @param smsAccountLocators 短信账户定位器
     * @return SmsAccountLocator
     */
    @Bean
    @Primary
    public SmsAccountLocator smsAccountLocator(
            List<SmsAccountLocator> smsAccountLocators) {
        return new CompositeSmsAccountLocator(smsAccountLocators);
    }

    /**
     * 短信渠道
     *
     * @param smsChannels 短信渠道集合
     * @return SmsChannelFactory
     */
    @Bean
    public SmsChannelFactory smsChannelStrategy(List<SmsChannel> smsChannels) {
        return new SmsChannelFactory(smsChannels);
    }
}
