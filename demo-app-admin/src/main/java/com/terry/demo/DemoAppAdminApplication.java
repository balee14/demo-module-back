package com.terry.demo;

import jakarta.annotation.PostConstruct;
import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.terry.demo")
@EnableJpaRepositories(basePackages = "com.terry.demo")
@EntityScan(basePackages = "com.terry.demo")
public class DemoAppAdminApplication {

	public static void main(String[] args) {

		SpringApplication api = new SpringApplication(DemoAppAdminApplication.class);
		api.addListeners(new ApplicationPidFileWriter());
		api.run(args);

	}

	@PostConstruct
	public void setTime() {
		// timezone 설정
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}

}
