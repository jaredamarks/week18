package loaf.store.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;
	private String itemInfo;

	
	@ManyToOne(cascade = CascadeType.ALL) // Item works at a pet store
	@JoinColumn(name = "store_id") // Foreign key
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
	private LoafStore loafStore;
}
