package com.app.test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.app.product.config.AppConfig;
import com.app.product.model.dao.CategoryDao;
import com.app.product.model.dto.Category;

@TestMethodOrder(OrderAnnotation.class)
@SpringJUnitConfig(classes = AppConfig.class)
public class CategoryDaoTest {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Test
	@DisplayName("1. Create Category")
	@Order(1)
	void test1() {
		
		var c = new Category();
		c.setName("Foods");
		
		var id = categoryDao.create(c);
		Assertions.assertEquals(1, id);
	}
	
	@Test
	@DisplayName("2. Update Category")
	@Order(2)
	void test2() {
		Category c = new Category();
		c.setId(1);
		c.setName("Drinks");
		
		int count = categoryDao.update(c);
		Assertions.assertEquals(1, count);
	}
	
	@Test
	@DisplayName("3. Find Category By ID")
	@Order(3)
	void test3() {
		
		Category c = categoryDao.findById(1);
		Assertions.assertEquals("Drinks", c.getName());
	}
	
	@Test
	@DisplayName("4. Find Category By Name")
	@Order(4)
	void test4() {
		
		List<Category> list = categoryDao.findByNameLike("Drin");
		Assertions.assertEquals(1, list.size());
	}
	
	@Test
	@DisplayName("5. Find Category Count By Name")
	@Order(5)
	void test5() {
		
		int count = categoryDao.findCountByNameLike("Drin");
		Assertions.assertEquals(1, count);
	}
	
	@Test
	@DisplayName("6. Delete Category By Id")
	@Order(6)
	void test6() {
		
		int count = categoryDao.delete(1);
		Assertions.assertEquals(1, count);
	}
}
