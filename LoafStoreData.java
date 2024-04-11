package loaf.store.controller.model;

import java.util.HashSet;
import java.util.Set;

import loaf.store.entity.Customer;
import loaf.store.entity.Item;
import loaf.store.entity.LoafStore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoafStoreData {
	private Long loafStoreId;
	private String loafStoreName;
	private String loafStoreInfo;
	private Set<LoafStoreCustomer> customers = new HashSet<>();
	private Set<LoafStoreItem> items = new HashSet<>();
	
    public LoafStoreData(LoafStore loafStore) {
    	this.loafStoreId = loafStore.getLoafStoreId();
        this.loafStoreName = loafStore.getLoafStoreName();
        this.loafStoreInfo = loafStore.getLoafStoreInfo();
        
        for (Customer customer : loafStore.getCustomers()) {
        	customers.add(new LoafStoreCustomer(customer));
        }
        
        for (Item item : loafStore.getItems()) {
        	items.add(new LoafStoreItem(item));
        }
        	
}

	{
		return getLoafStore();
	}

	{
		setLoafStoreName(loafStoreName);
	}
}
