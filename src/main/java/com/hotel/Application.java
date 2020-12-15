package com.hotel;

import java.util.stream.LongStream;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hotel.model.Hotel;
import com.hotel.repository.HotelRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner init(HotelRepository repository){
		return args -> {
			repository.deleteAll();
			LongStream.range(1, 10)
			.mapToObj(i -> {
				Hotel c = new Hotel();
				c.setNome("Hotel name: " + i);
				c.setCapacidade("Capacidade: " + i + " apt");
				c.setLocal("Recife " +i);
				return c;
			})
			.map(m -> repository.save(m))
			.forEach(System.out::println);
		};
	}
	
}
