package com.app.product.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan("com.app.product.model.dao")
@PropertySource("/sql.properties")
public class AppConfig {
	
	@Bean
	public DataSource dataSource() {
		
		var hsql = new EmbeddedDatabaseBuilder();
		hsql.setType(EmbeddedDatabaseType.HSQL);
		hsql.setName("dataSource");
		hsql.addScript("classpath:/database.sql");
		
		return hsql.build();
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		
		return new JdbcTemplate(dataSource);
	}
	
	@Bean
	public SimpleJdbcInsert categoryInsert(JdbcTemplate template) {
		var jdbcInsert = new SimpleJdbcInsert(template);
		jdbcInsert.setTableName("category");
		jdbcInsert.setGeneratedKeyName("id");
		
		return jdbcInsert;
	}
	
	@Bean
	public NamedParameterJdbcTemplate namedTemplate(DataSource dataSource) {
		
		return new NamedParameterJdbcTemplate(dataSource);
	}
}
