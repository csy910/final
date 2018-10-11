package app.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.MailcapCommandMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import app.model.EmployeeRepository;

@Controller
public class EmployeeController extends TextWebSocketHandler{

	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping("/index.do")
	public String indexHandle(WebRequest wr, ModelMap modelMap) {
		if(wr.getAttribute("auth", WebRequest.SCOPE_SESSION) == null) {
			return "index";
		}else {
			System.out.println("a");
			return "home";
		}
	}
	
	
	@PostMapping("/login.do")
	public String loginHandle(WebRequest wr, Map map) {
		String id = (String)wr.getParameter("getId");
		String pass = (String)wr.getParameter("getPass");
		Map mapp = new HashMap<>();
		mapp.put("id", id);
		mapp.put("pass", pass);
		Map mappp = employeeRepository.getAllEmployees(mapp);
		if(mappp != null) {
			wr.setAttribute("id", id, WebRequest.SCOPE_SESSION);
			wr.setAttribute("pass", pass, WebRequest.SCOPE_SESSION);
			return "home";
		}else {
			wr.setAttribute("err", "true", 0);
			return "index";
		}
	}
	
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
 