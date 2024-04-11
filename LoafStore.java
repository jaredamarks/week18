package loaf.store.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class LoafStore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loafStoreId;
	private String loafStoreName;
	private String loafStoreInfo;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "customer_item", 
	joinColumns = @JoinColumn(name = "customer_item"), 
	inverseJoinColumns = @JoinColumn(name = "customer_item"))
	private Set<Customer> customers = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "store_id", cascade = CascadeType.ALL, 
			orphanRemoval = true)
	private Set<Item> items = new HashSet<>();
}
