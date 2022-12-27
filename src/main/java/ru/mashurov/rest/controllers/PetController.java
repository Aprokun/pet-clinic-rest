package ru.mashurov.rest.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mashurov.rest.dto.PetUpdateDto;
import ru.mashurov.rest.model.Pet;
import ru.mashurov.rest.services.PetService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PetController {

	private final PetService petService;

	@PostMapping("/pets/save")
	public ResponseEntity<Pet> save(@RequestBody final Pet pet) {
		return ResponseEntity.ok(petService.save(pet));
	}

	@GetMapping("/pets/{id}/get")
	public ResponseEntity<Pet> find(@PathVariable final Long id) {
		return ResponseEntity.ok(petService.findById(id));
	}

	@PutMapping("/pets/update")
	public ResponseEntity<Void> update(@RequestBody final PetUpdateDto updateDto) {

		final Pet pet = petService.findById(updateDto.getId());

		pet.setName(updateDto.getName());
		pet.setAge(updateDto.getAge());
		pet.setGender(updateDto.getGender());

		petService.save(pet);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/pets/{id}/delete")
	public ResponseEntity<Void> delete(@PathVariable final Long id) {

		petService.deleteById(id);

		return ResponseEntity.noContent().build();
	}
}
