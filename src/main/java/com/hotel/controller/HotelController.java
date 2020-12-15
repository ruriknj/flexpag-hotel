package com.hotel.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.model.Hotel;
import com.hotel.repository.HotelRepository;

@RestController
@RequestMapping({ "/hotel" })
public class HotelController {

	@Autowired
	private HotelRepository repository;

// 		List All
	@GetMapping
// 		http://localhost:8090/hotel 	
	public List findAll() {
		return repository.findAll();
	}

//		Find by id - Busca valor pelo ID especifico
	@GetMapping(value = "{id}")
//		http://localhost:8090/hotel/1			
	public ResponseEntity findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

//		Create
	@PostMapping
//		http://localhost:8090/hotel		
	public Hotel create(@RequestBody Hotel hotel) {
		return repository.save(hotel);
	}

	@DeleteMapping(path = { "/{id}" })
//		http://localhost:8090/hotel/10
	public ResponseEntity<?> delete(@PathVariable long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
