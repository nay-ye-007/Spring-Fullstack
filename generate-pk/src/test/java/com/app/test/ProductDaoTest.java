package com.app.test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.app.product.config.AppConfig;
import com.app.product.model.dao.CategoryDao;
import com.app.product.model.dao.ProductDao;
import com.app.product.model.dto.Product;

@TestMethodOrder(OrderAnnotation.class)
@SpringJUnitConfig(classes = AppConfig.class)
public class ProductDaoTest {
	
	@Autowired
	private CategoryDao categories;
	@Autowired
	private ProductDao products;
	
	@Test
	@DisplayName("1. Create Product")
	@Order(1)
	@Sql(statements = {"insert into category (name) values ('Foods')",
			"insert into category (name) values ('Drinks')"
	})
	void test1() {
		
		var category = categories.findById(1);
		var product = new Product();
		product.setCategory(category);
		product.setName("Pillow Pan");
		product.setPrice(600);
		
		var count = products.create(product);
		Assertions.assertEquals(1, count);
	}
	
	@Test
	@DisplayName("2. Find Product By ID")
	@Order(2)
	void test2() {
		
		var product = products.findById(1);
		Assertions.assertEquals("Pillow Pan", product.getName());
		Assertions.assertEquals("Foods", product.getCategory().getName());
		Assertions.assertEquals(600, product.getPrice());
		
		Assertions.assertNull(products.findById(2));
	}
	
	@Test
	@DisplayName("3. Find Products By Category")
	@Order(3)
	void test3() {
		
		List<Product> list = products.findByCategory(1);
		Assertions.assertEquals(1, list.size());
		Assertions.assertTrue(products.findByCategory(2).isEmpty());
	}
	
	@Test
	@DisplayName("4. Search")
	@Order(4)
	void test4() {
		
		Assertions.assertEquals(1, products.search("Foods").size());
		Assertions.assertEquals(1, products.search("Pillow").size());
		Assertions.assertTrue(products.search("Pillows").isEmpty());
	}
	
	@Test
	@DisplayName("5. Update Product")
	@Order(5)
	void test5() {
		
		var p = products.findById(1);
		p.setName("Pillow Bread");
		p.setPrice(700);
		
		int count = products.update(p);
		Assertions.assertEquals(1, count);
		
		var other = products.findById(1);
		Assertions.assertEquals(p.getName(), other.getName());
		Assertions.assertEquals(p.getPrice(), other.getPrice());
	}
	
	@Test
	@DisplayName("6. Delete Product")
	@Order(6)
	void test6() {
		
		int count = products.delete(1);
		Assertions.assertEquals(1, count);
		Assertions.assertNull(products.findById(1));
	}
}
