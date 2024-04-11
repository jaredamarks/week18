package loaf.store.service;

import java.util.LinkedList; 
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import loaf.store.controller.model.LoafStoreCustomer;
import loaf.store.controller.model.LoafStoreData;
import loaf.store.controller.model.LoafStoreItem;
import loaf.store.dao.CustomerDao;
import loaf.store.dao.ItemDao;
import loaf.store.dao.LoafStoreDao;
import loaf.store.entity.Customer;
import loaf.store.entity.Item;
import loaf.store.entity.LoafStore;

@Service
public class LoafStoreService {
	
	@Autowired
	private LoafStoreDao loafStoreDao;
	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired 
	private CustomerDao customerDao;

	public LoafStoreData saveLoafStore(LoafStoreData loafStoreData) {
		Long loafStoreId = loafStoreData.getLoafStoreId();
		LoafStore loafStore = findOrCreateLoafStore(loafStoreId);
		copyLoafStoreFields(loafStore, loafStoreData);
		LoafStore dbLoafStore = loafStoreDao.save(loafStore);
		return new LoafStoreData(dbLoafStore);
	}

	private void copyLoafStoreFields(LoafStore loafStore, LoafStoreData loafStoreData) {
		loafStore.setLoafStoreId(loafStoreData.getLoafStoreId());
		loafStore.setLoafStoreName(loafStoreData.getLoafStoreName());
		loafStore.setLoafStoreInfo(loafStoreData.getLoafStoreInfo());
	}

	private LoafStore findOrCreateLoafStore(Long loafStoreId) {
		LoafStore loafStore;
		
		if (Objects.isNull(loafStoreId)) {
			loafStore = new LoafStore();
		} else {
			loafStore = findLoafStoreById(loafStoreId);
		}
		return loafStore;
	}

	private LoafStore findLoafStoreById(Long loafStoreId) {
		return loafStoreDao.findById(loafStoreId).orElseThrow(() -> new NoSuchElementException("Loaf store with id " + loafStoreId + " was not found."));
	}

	@Transactional(readOnly = false)
	public LoafStoreItem saveItem(Long loafStoreId, LoafStoreItem loafStoreItem) {
		LoafStore loafStore = findLoafStoreById(loafStoreId);
		Item item = findOrCreateItem(loafStoreId, loafStoreItem.getItemId());
		copyItemFields(item, loafStoreItem);
		item.setLoafStore(loafStore);
		loafStore.getItems().add(item);
		
		Item dbItem = itemDao.save(item);
		
		return new LoafStoreItem(dbItem);
	}
	
	public void copyItemFields(Item item, LoafStoreItem loafStoreItem) {
		item.setItemId(loafStoreItem.getItemId());
		item.setItemInfo(loafStoreItem.getItemInfo());
	}
	
	public Item findOrCreateItem(Long loafStoreId, Long itemId) {
		Item item;
		if (Objects.isNull(itemId)) {
			item = new Item();
		} else {
			item = findItemById(loafStoreId, itemId);
		}
		return item;
	}
	
	public Item findItemById(Long loafStoreId, Long itemId) {
		Item item = itemDao.findById(itemId)
			.orElseThrow(() -> new NoSuchElementException(
					"Item with ID=" + itemId + " does not exist."));
		
		if(item.getLoafStore().getLoafStoreId() == loafStoreId) {
			return item;
		} else {
			throw new IllegalArgumentException("Loaf store with ID=" + loafStoreId + " does not have an item with ID=" + itemId);
		}
	}
	
	@Transactional(readOnly = false)
	public LoafStoreCustomer saveCustomer(Long loafStoreId, LoafStoreCustomer loafStoreCustomer) {
		LoafStore loafStore = findLoafStoreById(loafStoreId);
		Customer customer = findOrCreateCustomer(loafStoreId, loafStoreCustomer.getCustomerId()); 
		copyCustomerFields(customer, loafStoreCustomer);
		customer.getLoafStores().add(loafStore);
		loafStore.getCustomers().add(customer);
		
		Customer dbCustomer = customerDao.save(customer);
		
		return new LoafStoreCustomer(dbCustomer);
	}
	
	public void copyCustomerFields(Customer customer, LoafStoreCustomer loafStoreCustomer) {
		customer.setCustomerId(loafStoreCustomer.getCustomerId());
		customer.setCustomerInfo(loafStoreCustomer.getCustomerInfo());
	}
	
	public Customer findOrCreateCustomer(Long loafStoreId, Long customerId) {
		Customer customer;
		
		if (Objects.isNull(customerId)) {
			customer = new Customer();
		} else {
			customer = findCustomerById(loafStoreId, customerId);
		}
		
		return customer;
	}
	
	public Customer findCustomerById(Long loafStoreId, Long customerId) {
		boolean loafStoreIdsMatch = false;
		Customer customer = customerDao.findById(customerId)
			.orElseThrow(() -> new NoSuchElementException(
					"Customer with ID= " + customerId + " does not exist."));
		
		Set<LoafStore> loafStores = customer.getLoafStores();
		for (LoafStore loafStore : loafStores) {
			if (loafStore.getLoafStoreId() == loafStoreId) {
				loafStoreIdsMatch = true;
			}
		}
		
		if(loafStoreIdsMatch) {
			return customer;
		} else {
			throw new IllegalArgumentException("Loaf store with ID= " + loafStoreId + " does not have a customer with ID= " + customerId);
		}
	}

	@Transactional
	public List<LoafStoreData> retrieveAllLoafStores() {
		List<LoafStore> loafStores = loafStoreDao.findAll();
		List<LoafStoreData> results = new LinkedList<>();
		
		for (LoafStore loafStore : loafStores) {
			LoafStoreData loafStoreData = new LoafStoreData(loafStore);
			
			loafStoreData.getCustomers().clear();
			loafStoreData.getItems().clear();
			
			results.add(loafStoreData);
		}
		
		return results;
	}
	
	public LoafStoreData retrieveLoafStoreById(Long loafStoreId) {
		return new LoafStoreData(findLoafStoreById(loafStoreId));
	}

	public void deleteLoafStoreById(Long loafStoreId) {
		LoafStore loafStore = findLoafStoreById(loafStoreId);
		loafStoreDao.delete(loafStore);
	}
}