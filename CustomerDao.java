package loaf.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import loaf.store.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

}
