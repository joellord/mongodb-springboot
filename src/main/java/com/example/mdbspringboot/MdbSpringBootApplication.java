package com.example.mdbspringboot;


import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.mdbspringboot.model.GroceryItem;
import com.example.mdbspringboot.repository.CustomItemRepository;
import com.example.mdbspringboot.repository.ItemRepository;

@SpringBootApplication
@EnableMongoRepositories
public class MdbSpringBootApplication implements CommandLineRunner{
	
	@Autowired
	ItemRepository itemRepo;
	
	@Autowired
	CustomItemRepository customRepo;

	public static void main(String[] args) {
		SpringApplication.run(MdbSpringBootApplication.class, args);
	}
	// CRUD operations

	//CREATE
	void createDocuments() throws ParseException {
		System.out.println("data creation started...");
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		itemRepo.save(new GroceryItem("Whole Wheat Biscuit", "Whole Wheat Biscuit", 20, formatter.parse("20-07-2021"), "snacks"));
		itemRepo.save(new GroceryItem("Kodo Millet", "XYZ Kodo Millet healthy", 40, formatter.parse("22-07-2021"), "millets"));
		itemRepo.save(new GroceryItem("Dried Red Chilli", "Dried Whole Red Chilli", 10, formatter.parse("20-06-2021"), "spices"));
		itemRepo.save(new GroceryItem("Pearl Millet", "Healthy Pearl Millet", 42, formatter.parse("21-05-2020"), "millets"));
		itemRepo.save(new GroceryItem("Cheese Crackers", "Bonny Cheese Crackers Plain", 14, formatter.parse("25-07-2021"), "snacks"));
		System.out.println("data creation complete...");
	}
	
	// READ
	// 1. Show all the data
	public void showAllDocuments() {
		System.out.println("Showing all the documents...");
		
		itemRepo.findAll().forEach(item -> System.out.println(getItemDetails(item)));
	}
	
	// 2. Get item by name
	public void getItemByName(String name) {
		System.out.println("Getting item by name: " + name);
		GroceryItem item = itemRepo.findItemByName(name);
		System.out.println(getItemDetails(item));
	}
	
	// 3. Get name and items of a all items of a particular category
	public void getItemByCategory(String category) {
		System.out.println("Getting items for the category " + category);
		List<GroceryItem> list = itemRepo.findAll(category);
		
		list.forEach(item -> System.out.println("Name: " + item.getItemName() + ", Price: " + item.getItemPrice()));
	}
	
	// 4. Get count of documents in the collection
	public void findCountOfItems() {
		long count = itemRepo.count();
		System.out.println("Number of documents in the collection = " + count);
	}
	
	// UPDATE
	public void updatePrice(String itemName, float newPrice) {
		System.out.println("Updating price for " + itemName);
		customRepo.updateItemPrice(itemName, newPrice);
	}
	
	// DELETE
	public void deleteDocument(String id) {
		itemRepo.deleteById(id);
		System.out.println("Item with id " + id + " deleted...");
	}
	
	// Print details in readable form
	
	public String getItemDetails(GroceryItem item) {

		System.out.println(
		"Item Name: " + item.getItemName() + 
		", Item Price: " + item.getItemPrice() +
		", Item Date: " + item.getItemDate() + 
		", Item Category: " + item.getCategory()
		);
		
		return "";
	} 
	
	public void run(String... args) throws ParseException {
		System.out.println("Running queries...");
		System.out.println("----------------------------------------------");
		createDocuments();
		System.out.println("----------------------------------------------");
		showAllDocuments();
		System.out.println("----------------------------------------------");
		getItemByName("Whole Wheat Biscuit");
		System.out.println("----------------------------------------------");
		getItemByCategory("millets");
		System.out.println("----------------------------------------------");
		updatePrice("Bonny Cheese Crackers Plain", 41);
		System.out.println("----------------------------------------------");
		deleteDocument("Kodo Millet");
		System.out.println("----------------------------------------------");
		findCountOfItems();
		System.out.println("----------------------------------------------");

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		List<GroceryItem> list = itemRepo.findItemByDate(formatter.parse("20-06-2021"));
		list.forEach(item -> System.out.println("Name: " + item.getItemName() + " Date: " + formatter.format(item.getItemDate())));

	}
}