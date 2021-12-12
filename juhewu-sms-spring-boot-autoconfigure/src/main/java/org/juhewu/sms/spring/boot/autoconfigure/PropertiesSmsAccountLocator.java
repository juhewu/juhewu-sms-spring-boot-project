package org.juhewu.sms.spring.boot.autoconfigure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.juhewu.sms.SmsAccount;
import org.juhewu.sms.SmsAccountLocator;

/**
 * 配置文件中的短信账户
 *
 * @author duanjw
 */
public class PropertiesSmsAccountLocator implements SmsAccountLocator {

    private final SmsAccessProperties smsAccessProperties;

    public PropertiesSmsAccountLocator(SmsAccessProperties smsAccessProperties) {
        this.smsAccessProperties = smsAccessProperties;
    }

    /**
     * 所有短信账户
     *
     * @return smsAccounts
     */
    @Override
    public List<SmsAccount> getSmsAccounts() {
        return Optional.ofNullable(smsAccessProperties.getAccounts()).orElse(new ArrayList<>());
    }

    /**
     * 根据短信账户id获取短信账户
     *
     * @param id id
     * @return smsAccount
     */
    @Override
    public SmsAccount getSmsAccount(String id) {
        return getSmsAccounts().stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
    }
}
