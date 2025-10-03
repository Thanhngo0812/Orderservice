package ordercontainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("entity")           // ← Scan entity package
@EnableJpaRepositories("repository") // ← Scan repository package
@ComponentScan(basePackages = {
	 "rest"
			    // Gói gốc của dự án bạn
			    ,
			    // Gói của Application Service (nơi chứa OrderApplicationServiceImpl)
			    "handler"       ,         // package chứa service
			    "service",
			    "adapter",
			    "repository",
			    "mapper"
			    
	})
public class OrderserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderserviceApplication.class, args);
	}

}
