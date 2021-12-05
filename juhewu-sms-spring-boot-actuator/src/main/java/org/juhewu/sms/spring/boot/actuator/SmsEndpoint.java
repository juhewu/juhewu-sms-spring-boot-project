package org.juhewu.sms.spring.boot.actuator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.juhewu.sms.SmsAccount;
import org.juhewu.sms.SmsAccountLocator;
import org.juhewu.sms.SmsAccountRepository;
import org.juhewu.sms.SmsChannel;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;

/**
 * 短信监控
 *
 * @author duanjw
 */
@Endpoint(id = "juhewu-sms")
public class SmsEndpoint {

    private final List<SmsChannel> smsChannels;
    private final SmsAccountLocator smsAccountLocator;
    private final SmsAccountRepository smsAccountRepository;

    public SmsEndpoint(List<SmsChannel> smsChannels, SmsAccountLocator smsAccountLocator, SmsAccountRepository smsAccountRepository) {
        this.smsChannels = smsChannels;
        this.smsAccountLocator = smsAccountLocator;
        this.smsAccountRepository = smsAccountRepository;
    }

    /**
     * 短信配置信息
     *
     * @return
     */
    @ReadOperation
    public Map<String, Object> smsInfo() {
        Map<String, Object> result = new HashMap<>();
        result.put("smsChannels", listSmsChannelDescriptions());
        result.put("smsAccounts", smsAccountLocator.getSmsAccounts());
        result.put("smsAccountRepository", smsAccountRepository.getClass().getName());
        return result;
    }

    private List<SmsChannelDescription> listSmsChannelDescriptions() {
        return this.smsChannels.stream().map(SmsChannelDescription::new).collect(Collectors.toList());
    }

    /**
     * 短信渠道描述
     *
     * @author duanjw
     */
    public static class SmsChannelDescription {

        private final String smsChannelCode;
        private final String className;

        public String getSmsChannelCode() {
            return smsChannelCode;
        }

        public String getClassName() {
            return className;
        }

        public SmsChannelDescription(SmsChannel smsChannel) {
            this.smsChannelCode = smsChannel.getChannelCode();
            this.className = smsChannel.getClass().getName();
        }
    }

    /**
     * 根据id获取短信账户
     *
     * @return
     */
    @ReadOperation
    public SmsAccount getSmsAccountByKey(@Selector String key) {
        return this.smsAccountLocator.getSmsAccount(key);
    }
}
