package com.jdc.project.model.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import com.jdc.project.model.dto.Project;
import com.jdc.project.model.service.utils.ProjectHelper;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectHelper projectHelper;
	@Autowired
	private SimpleJdbcInsert projectInsert;
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private RowMapper<Project> rowMapper;
	
	public ProjectService() {
		rowMapper = new BeanPropertyRowMapper<>(Project.class);
	}
	
	public int create(Project project) {
		projectHelper.validate(project);
		var params = projectHelper.insertParams(project);
		
		return projectInsert.executeAndReturnKey(params).intValue();
	}

	public Project findById(int id) {
		var sql = """
				select p.id, p.name, p.description, p.manager managerId, p.start startDate, p.months, m.name managerName, m.login_id 
				from project p inner join member m on p.manager = m.id where p.id = :id
				""";
		
		return template.queryForObject(sql, Map.of("id", id), rowMapper);
	}

	public List<Project> search(String project, String manager, LocalDate dateFrom, LocalDate dateTo) {
		var sql = new StringBuffer("select p.name, m.name managerName from project p inner join member m on p.manager = m.id ");
		var params = new HashMap<String, Object>();
		
		if(null != project) {
			sql.append("where p.name like :project");
			params.put("project", project.toLowerCase().concat("%"));
		}
		if(null != manager) {
			sql.append("where m.name like :manager");
			params.put("manager", manager.toLowerCase().concat("%"));
		}
		if(null != dateFrom || null != dateTo) {
			sql.append("where p.start between :dateFrom and last_day(:dateFrom) or p.start between '' and :dateTo");
			params.put("dateFrom", dateFrom);
			params.put("dateTo", dateTo);
		}
		
		return template.queryForStream(sql.toString(), params, rowMapper).toList();
	}

	public int update(int id, String name, String description, LocalDate startDate, int month) {
		var sql = "update project set name = :name, description = :description, start = :startDate, months = :month where id = :id";
		var params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("name", name);
		params.put("description", description);
		params.put("startDate", startDate);
		params.put("month", month);
		
		return template.update(sql, params);
	}

	public int deleteById(int id) {
		
		return template.update("delete from project where id = :id", Map.of("id", id));
	}

}
