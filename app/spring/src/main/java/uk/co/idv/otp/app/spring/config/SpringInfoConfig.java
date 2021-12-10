package uk.co.idv.otp.app.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.co.idv.otp.app.spring.info.AppNameInfoContributor;
import uk.co.idv.otp.app.spring.info.SecureProperties;
import uk.co.idv.otp.app.spring.info.SystemPropertyInfoContributor;

@Configuration
public class SpringInfoConfig {

    @Bean
    public InfoContributor appNameInfoContributor(@Value("${spring.application.name}") String appName) {
        return new AppNameInfoContributor(appName);
    }

    @Bean
    public InfoContributor profileContributor() {
        return new SystemPropertyInfoContributor(new SecureProperties());
    }

}
