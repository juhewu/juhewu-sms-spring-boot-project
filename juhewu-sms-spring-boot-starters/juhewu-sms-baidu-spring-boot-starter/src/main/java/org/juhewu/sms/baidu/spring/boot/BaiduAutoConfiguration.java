package org.juhewu.sms.baidu.spring.boot;

import org.juhewu.sms.SmsChannel;
import org.juhewu.sms.baidu.BaiduSmsChannelImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * 阿里云短信自动配置
 *
 * @author duanjw
 */
@Slf4j
@Configuration
public class BaiduAutoConfiguration {

    @Bean
    public SmsChannel baiduSmsChannel() {
        log.debug("开始加载 {}", this.getClass().getSimpleName());
        return new BaiduSmsChannelImpl();
    }
}
