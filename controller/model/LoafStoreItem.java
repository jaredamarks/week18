package loaf.store.controller.model;

import loaf.store.entity.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoafStoreItem {
	private Long itemId;
	private String itemInfo;
	
		 public LoafStoreItem(Item item) {
			 	this.itemId = item.getItemId();
	            this.itemInfo = item.getItemInfo();
	            
}

//		{
//			return getItemId();
//		}
//
//		{
//			setItemId(itemId);
//		}
}

