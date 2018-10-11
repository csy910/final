package app.controller.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.model.EmployeeRepository;

@Controller
@RequestMapping("/admin/employee")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;
	
	
	@GetMapping("/add.do")
	public String addGetHandle(ModelMap map) {
		
		map.put("dep", employeeRepository.getAllDepartments());
		map.put("pos", employeeRepository.getAllPositions());
		
		
		return "admin.employee.add";
	}
	
	
	@PostMapping("/add.do")
	public String addPostHandle(@RequestParam Map param, ModelMap map) {
		
		String nid = employeeRepository.getNewEmployeeId();
		param.put("id", nid);
		try {
			int t = employeeRepository.addEmployee(param);
			map.put("employee", param);
			return "admin.employee.addresult";
		}catch(Exception e) {
			e.printStackTrace();
			map.put("err", "on");
			map.put("dep", employeeRepository.getAllDepartments());
			map.put("pos", employeeRepository.getAllPositions());
			return "admin.employee.add";
		}
		
	}
}
 