package loaf.store.controller.model;

import loaf.store.entity.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoafStoreCustomer {
	private Long customerId;
	private String customerInfo;
	
	public LoafStoreCustomer (Customer customer ) {
		this.customerId = customer.getCustomerId();
		this.customerInfo = customer.getCustomerInfo();
	}
}
