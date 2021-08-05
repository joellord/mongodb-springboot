package com.example.mdbspringboot.repository;

import java.util.List;
import java.util.Date;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mdbspringboot.model.GroceryItem;

public interface ItemRepository extends MongoRepository<GroceryItem, String> {
	
	@Query("{itemName:'?0'}")
	GroceryItem findItemByName(String itemName);
	
	@Query(value="{category:'?0'}", fields="{'itemName' : 1, 'itemPrice' : 1}")
	List<GroceryItem> findAll(String category);
	
	@Query("{itemDate : {$lt: ?0}}")
	List<GroceryItem> findItemByDate(Date itemDate);

	public long count();

}