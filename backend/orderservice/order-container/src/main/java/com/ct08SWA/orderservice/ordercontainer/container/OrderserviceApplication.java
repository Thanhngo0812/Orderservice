package com.ct08SWA.orderservice.ordercontainer.container;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.ct08SWA.orderservice.orderdataaccess.entity")           // ← Scan entity package
@EnableJpaRepositories(basePackages = "com.ct08SWA.orderservice.orderdataaccess.repository") // <--- THÊM DÒNG NÀY
@ComponentScan(basePackages = {
	    			"com.ct08SWA.orderservice.ordercontainer",
	    			"com.ct08SWA.orderservice.orderapplicationservice",
	    			"com.ct08SWA.orderservice.orderdataaccess",
	    			"com.ct08SWA.orderservice.orderdomaincore",
//			    // Gói của Application Service 
//			    "handler",         			    
//			    "adapter",  
//			    //Gói của data-access
//			    "mapper",
//			    "repository",
//			    //Gói của domain-core
//			    "service"
			    
	})
public class OrderserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderserviceApplication.class, args);
	}

}
