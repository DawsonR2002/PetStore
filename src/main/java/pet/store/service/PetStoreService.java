package pet.store.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {
	
	//@Autowired allows spring to inject the the DAO object 
	//into the variable
	@Autowired
	//instance variable
	private PetStoreDao petStoreDao;
	
	 
	    
    public PetStoreData savePetStore(PetStoreData petStoreData) {
    	
    	Long petStoreId = petStoreData.getPetStoreId();
    	System.out.println(petStoreId);
    	PetStore petStore = findOrCreatePetStore(petStoreId);
    	copyPetStoreFields(petStore, petStoreData);
    	return new PetStoreData(petStoreDao.save(petStore));
    }
    
    public void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
    	
    	//PetStore petStoreContainer = new PetStore();
    	petStore.setPetStoreId(petStoreData.getPetStoreId());
    	petStore.setPetStoreName(petStoreData.getPetStoreName());
    	petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
    	petStore.setPetStoreCity(petStoreData.getPetStoreCity());
    	petStore.setPetStoreState(petStoreData.getPetStoreState());
    	petStore.setPetStoreZip(petStoreData.getPetStoreZip());
    	petStore.setPetStorePhone(petStoreData.getPetStorePhone());
    	
    }

    
   
	public PetStore findOrCreatePetStore(Long petStoreId) {
		if(Objects.isNull(petStoreId)) {
			return new PetStore();
		}
		
		else {
			return findPetStoreById(petStoreId);
		}
	}
    

	private PetStore findPetStoreById(Long petStoreId) {
		return petStoreDao.findById(petStoreId).orElseThrow(() -> new NoSuchElementException("Pet store with ID={}" + petStoreId + " was not found."));
		

	}





}
