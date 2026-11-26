package com.microservices.ecominventoryservice.Repository;

import com.microservices.ecominventoryservice.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<Inventory, Long> {
}
