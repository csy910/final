package app.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class EmployeeRepository {

	@Autowired
	SqlSessionTemplate template;
	
	public List<Map> getAllDepartments() {
		return template.selectList("employee.getAllDepartments");
	}
	
	public List<Map> getAllPositions() {
		return template.selectList("employee.getAllPositions");
	}
	
	public String getNewEmployeeId() {
		return "em"+ template.selectOne("employee.getEmployeeSeq");
	}
	
	public int addEmployee(Map map) {
		return template.insert("employee.addEmployee", map);
	}
}
