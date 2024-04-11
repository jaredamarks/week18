package loaf.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import loaf.store.entity.Item;

public interface ItemDao extends JpaRepository<Item, Long> {

}
