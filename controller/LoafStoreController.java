package loaf.store.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import loaf.store.controller.model.LoafStoreCustomer;
import loaf.store.controller.model.LoafStoreData;
import loaf.store.controller.model.LoafStoreItem;
import loaf.store.service.LoafStoreService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/loaf_store")
@Slf4j
public class LoafStoreController {
	
	@Autowired
    private LoafStoreService loafStoreService;
    
    @PostMapping
    public LoafStoreData insertLoafStoreData(@RequestBody LoafStoreData loafStoreData) {
        log.info("Inserting Loaf store {}", loafStoreData);
        return loafStoreService.saveLoafStore(loafStoreData);
    }
    
    @PutMapping("/{storeId}")
    public LoafStoreData updateLoafStoreData(@PathVariable Long storeId, @RequestBody LoafStoreData loafStoreData) {
        log.info("Updating Loaf store data for storeId: {}", storeId);
        loafStoreData.setLoafStoreId(storeId);
        return loafStoreService.saveLoafStore(loafStoreData);
    }
    @GetMapping("/{loafStoreId}")
	public LoafStoreData retrieveLoafStoreById(@PathVariable Long LoafStoreId) {
		log.info("Retrieving Loaf store by ID={}", LoafStoreId);
		return loafStoreService.retrieveLoafStoreById(LoafStoreId);
	}
    @PostMapping("/{loafStoreId}/item")
    @ResponseStatus(HttpStatus.CREATED)
    public LoafStoreItem addItemToLoafStore(@PathVariable Long loafStoreId, @RequestBody LoafStoreItem item) {
      log.info("Adding item to Loaf store: {}", loafStoreId);
      return loafStoreService.saveItem(loafStoreId, item);
    }
    @GetMapping()
	public List<LoafStoreData> retrieveAllLoafStores() {
		log.info("Retrieve all Loaf stores.");
		return loafStoreService.retrieveAllLoafStores();
	}
	@PostMapping("/loaf/{loafStoreId}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public LoafStoreCustomer saveCustomer(@PathVariable Long LoafStoreId,
			@RequestBody LoafStoreCustomer loafStoreCustomer) {
		log.info("Adding customer {} for Loaf store with ID={}", loafStoreCustomer, LoafStoreId);

		return loafStoreService.saveCustomer(LoafStoreId, loafStoreCustomer);
	}
    @DeleteMapping("/loaf_store/{loafStoreId}")
    public ResponseEntity<Map<String, String>> deleteLoafStoreById(@PathVariable Long LoafStoreId) {
    	log.info("Received delete request for Loaf store with ID: " + LoafStoreId);
    	loafStoreService.deleteLoafStoreById(LoafStoreId);
    	Map<String, String> response = new HashMap<>();
    	response.put("message", "Loaf store deleted sucessfully");
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}
