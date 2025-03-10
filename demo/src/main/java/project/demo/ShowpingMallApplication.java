package project.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//@MapperScan("project.demo.post.repository") 
public class ShowpingMallApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShowpingMallApplication.class, args);
	}

}
