package com.state.pattern;

import com.state.pattern.entity.VendingMachine;
import com.state.pattern.state.States;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.state.pattern.repository.VendingMachineRepository;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Vending Machine REST API Documentation",
				description = "Vending Machine REST API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Sandeep kumar",
						url = "https://www.linkedin.com/in/sandeep-kumar-sky/"
				)
		)
)
public class VendingMachineService implements ApplicationRunner {

	@Autowired
	private VendingMachineRepository VendingMachineRepository;
	public static void main(String[] args) {
		SpringApplication.run(VendingMachineService.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO :Loading one entry on the database for testing purpose only

		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.setCapacity(30);
		vendingMachine.setQuantity(30);
		vendingMachine.setLocation("Kanpur");
		vendingMachine.setMachineName("Candy Machine");
		vendingMachine.setState(States.No_Coin);
		vendingMachine.setMachineName("ski1703");
		VendingMachineRepository.save(vendingMachine);
	}
}
