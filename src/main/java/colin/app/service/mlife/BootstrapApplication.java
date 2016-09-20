package colin.app.service.mlife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * Created by joker on 16/8/17.
 */
@SpringBootApplication(scanBasePackages = "colin.app.service.mlife")
@EnableScheduling
@EnableWebSocket
public class BootstrapApplication {

    public static void main(String[] args) {
        SpringApplication springApplication=new SpringApplication(BootstrapApplication.class);
        springApplication.setWebEnvironment(true);
        springApplication.run(BootstrapApplication.class);
    }
}
