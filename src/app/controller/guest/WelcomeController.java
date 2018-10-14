package app.controller.guest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import app.model.EmployeeRepository;
import app.service.SocketService;


@Controller
public class WelcomeController {
	
Map<String, HttpSession> sessions;
	
	public WelcomeController() {
		sessions = new HashMap<>();
	}

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	SocketService socketService;

	
	@GetMapping("/index.do")
	public String indexHandle(WebRequest wr, ModelMap modelMap) {
		if(wr.getAttribute("auth", WebRequest.SCOPE_SESSION) == null) {
			return "admin/index";
		}else {
			return "guest.home";
		}
	}
	
	
	@PostMapping("/login.do")
	public String loginHandle(WebRequest wr, Map map, HttpSession session) {
		String id = (String)wr.getParameter("getId");
		String pass = (String)wr.getParameter("getPass");
		Map mapp = new HashMap<>();
		Map maps = employeeRepository.getEmployee(id);
		mapp.put("id", id);
		mapp.put("pass", pass);
		Map mappp = employeeRepository.getAllEmployees(mapp);
		if(mappp != null) {
			wr.setAttribute("auth", true, WebRequest.SCOPE_SESSION);
			wr.setAttribute("user", maps, WebRequest.SCOPE_SESSION);
			wr.setAttribute("userId", id, WebRequest.SCOPE_SESSION);
			// 중복로그인 막기 =======================================
			if(sessions.containsKey(id)) {
				Map msgg = new HashMap<>();
				msgg.put("mode", "sndlogin");
				msgg.put("actor", maps);
				sessions.get(id).invalidate();
				socketService.sendOne(msgg, id);
			}else {				
				Map msg = new HashMap<>();
				msg.put("mode", "login");
				msg.put("actor", maps);
				socketService.sendAll(msg);
			}
			
			sessions.put(id, session);
			//========================================================
			return "redirect:index.do";
		}else {
			wr.setAttribute("err", "true", 0);
			return "admin/index";
		}
	}
	
	@RequestMapping("/logout.do")
	public String logoutHandle(WebRequest wr) {
		wr.removeAttribute("auth", WebRequest.SCOPE_SESSION);
		return "redirect:index.do";
	}
	
	@RequestMapping("/change.do")
	public String changeHandle() {
		return "guest/change";
	}
	
	@PostMapping("/changed.do")
	public String changedHandle(WebRequest wr, Map map) {
		String id = (String)wr.getAttribute("userId", WebRequest.SCOPE_SESSION);
		String oldPass = wr.getParameter("getPass1");
		String newPass1 = wr.getParameter("getPass2");
		String newPass2 = wr.getParameter("getPass3");
		System.out.println(oldPass);
		System.out.println(newPass1);
		System.out.println(newPass2);
		Map maps = employeeRepository.getEmployee(id);
		if(oldPass.equals(maps.get("PASS"))) {
			wr.removeAttribute("err1", 0);
			if(newPass1.equals(newPass2)) {
				maps.put("PASS", newPass1);
				System.out.println(maps);
				employeeRepository.changePassword(maps);
				wr.removeAttribute("err", 0);
				return "guest.home";
			}else {
				wr.setAttribute("err", true, 0);
				return "guest/change";
			}
		}else {
			wr.setAttribute("err1", true, 0);
			return "guest/change";
		}
	}
	
	
}
