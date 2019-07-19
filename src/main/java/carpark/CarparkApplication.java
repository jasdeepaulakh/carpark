package carpark;

import Beans.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarparkApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarparkApplication.class, args);
	}


	@Bean(name = "myBean", initMethod = "init")
	public InitializingBean getMyBean(){
		return new InitializingBean();
	}

}
