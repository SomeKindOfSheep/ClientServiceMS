package com.pss.clientservice.service.exception;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class MessagingAPII18nMessageResolver {

    private static final String MCAP_I18N_LANGUAGE = "mcap.i18n.language";
    private static final String MCAP_I18N_COUNTRY = "mcap.i18n.country";

    private ResourceBundle resourceBundle;
    private Locale locale;

    @PostConstruct
    public void init() {
        String language = System.getProperty(MCAP_I18N_LANGUAGE, "en");
        String country = System.getProperty(MCAP_I18N_COUNTRY, "za");
        this.locale = new Locale(language, country);
        this.resourceBundle = ResourceBundle.getBundle("i18n/messages", locale);
    }

    public String getMessage(String key) {
        return resourceBundle.getString(key);
    }

    public String getMessage(MessagingAPIExceptionMessage exceptionEnum) {
        return resourceBundle.getString(exceptionEnum.getCode());
    }

    public String getMessage(String key, Object[] args) {
        return String.format(locale, key, args);
    }

}
