package com.app.product.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import com.app.product.model.dto.Category;

@Service
public class CategoryDao {
	
	@Autowired
	private SimpleJdbcInsert jdbc;
	
	@Value("${dao.category.update}")
	private String update;
	@Value("${dao.category.findById}")
	private String findById;
	@Value("${dao.category.findByNameLike}")
	private String findByNameLike;
	@Value("${dao.category.findCountByNameLike}")
	private String findCountByNameLike;
	@Value("${dao.category.delete}")
	private String deleteById;
	
	private BeanPropertyRowMapper<Category> rowmapper;
	
	public CategoryDao() {
		
		rowmapper = new BeanPropertyRowMapper<Category>(Category.class);
	}
	
	public int create(Category category) {
		
//		PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(insertSql, Types.VARCHAR);
//		factory.setReturnGeneratedKeys(true);
//		var creator = factory.newPreparedStatementCreator(List.of(category.getName()));
//		
//		PreparedStatementCreator creator = con -> {
//			
//			var stmt = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
//			stmt.setString(1, category.getName());
//			return stmt;
//		};
		
//		PreparedStatementCallback<Integer> callback = stmt -> {
//			stmt.executeUpdate();
//			var rs = stmt.getGeneratedKeys();
//			
//			while(rs.next()) {
//				return rs.getInt(1);
//			}
//			
//			return 0;
//		};
		
//		var keyHolder = new GeneratedKeyHolder();
//		
//		//return jdbc.execute(creator, callback);
//		jdbc.update(creator, keyHolder);
//		
//		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbc);
//		insert.setTableName("category");
//		insert.setGeneratedKeyName("id");
		
		Map<String, Object> params = new HashMap<>();
		params.put("name", category.getName());
		
		return jdbc.executeAndReturnKey(params).intValue();
	}

	public int update(Category category) {
		var count = jdbc.getJdbcTemplate().update(update, category.getName(), category.getId());
		
		return count;
	}

	public Category findById(int id) {
		
		return jdbc.getJdbcTemplate().queryForObject(findById, rowmapper, id);
	}

	public List<Category> findByNameLike(String name) {
		
		return jdbc.getJdbcTemplate().query(findByNameLike, rowmapper, name.toLowerCase().concat("%"));
	}

	public int findCountByNameLike(String name) {
		
		return jdbc.getJdbcTemplate().queryForObject(findCountByNameLike, Integer.class, name.toLowerCase().concat("%"));
	}

	public int delete(int id) {
		
		return jdbc.getJdbcTemplate().update(deleteById, id);
	}
}
