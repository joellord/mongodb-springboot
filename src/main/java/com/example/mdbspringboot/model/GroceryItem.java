package com.example.mdbspringboot.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("GroceryItem")
public class GroceryItem {



		@Id
		private String id;

		private String itemName;
		private float itemPrice;
		private Date itemDate;
		private String category;
		
		public GroceryItem(String id, String itemName, float itemPrice, Date itemDate, String category) {
			super();
			this.id = id;
			this.itemName = itemName;
			this.itemPrice = itemPrice;
			this.itemDate = itemDate;
			this.category = category;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getItemName() {
			return itemName;
		}

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}

		public float getItemPrice() {
			return itemPrice;
		}

		public void setItemPrice(float itemPrice) {
			this.itemPrice = itemPrice;
		}

		public Date getItemDate() {
			return itemDate;
		}

		public void setItemDate(Date itemDate) {
			this.itemDate = itemDate;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

}
