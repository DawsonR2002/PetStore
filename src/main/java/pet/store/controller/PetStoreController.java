package pet.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {

	@Autowired
	PetStoreService petStoreService;
	
	 @PostMapping
	 @ResponseStatus(HttpStatus.CREATED)
	    public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) {
	        System.out.println("Received POST request to /pet_store with data: " + petStoreData);
	        petStoreService.savePetStore(petStoreData);
	        return petStoreData;
	    }
	 
	 @PutMapping("/{StoreId}")
	 public PetStoreData modifyPetStore(@PathVariable Long storeId, @RequestBody PetStoreData petStoreData) {
		 petStoreData.setPetStoreId(storeId);
		 log.info("Updated data using PUT");
		 return petStoreService.savePetStore(petStoreData);
		 
	 }
	 
}
