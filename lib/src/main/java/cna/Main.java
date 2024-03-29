package cna;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class Main {
	
	public static final Logger log = LoggerFactory.getLogger(Main.class);
	
	public static void main(String [] args) {
		SpringApplication.run(Main.class, args);
	}
}
