package org.juhewu.sms.yunpian.spring.boot;

import org.juhewu.sms.SmsChannel;
import org.juhewu.sms.yunpian.YunPianSmsChannelImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * 云片短信自动配置
 *
 * @author duanjw
 */
@Slf4j
@Configuration
public class YunPianAutoConfiguration {

    @Bean
    public SmsChannel yunPianSmsChannel() {
        log.debug("开始加载 {}", this.getClass().getSimpleName());
        return new YunPianSmsChannelImpl();
    }
}
