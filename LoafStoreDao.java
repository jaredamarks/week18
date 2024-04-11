package loaf.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import loaf.store.entity.LoafStore;

public interface LoafStoreDao extends JpaRepository<LoafStore, Long> {

}
