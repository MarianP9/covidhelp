package ro.scoalainformala.covidhelp.webapp.config;

import ro.scoalainformala.covidhelp.webapp.domain.Account;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Encoder implements WebMvcConfigurer {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    String mpCryptoPassword = "thesecretword";

    public void encrypt(Account account) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(mpCryptoPassword);
        String encryptedPassword = encryptor.encrypt(account.getPassword());
        account.setPassword(encryptedPassword);
    }

    public void decrypt(Account account) {
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword(mpCryptoPassword);
        String decrypt = decryptor.decrypt(account.getPassword());
        account.setPassword(decrypt);
    }

}
