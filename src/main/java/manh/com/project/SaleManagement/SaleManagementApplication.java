package manh.com.project.SaleManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SaleManagementApplication {


	public static void main(String[] args) {
		SpringApplication.run(SaleManagementApplication.class, args);
            System.out.println(new BCryptPasswordEncoder().encode("123"));
	}



}
