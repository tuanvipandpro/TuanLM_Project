package tuanlm.fpt.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InitController {
	
	@RequestMapping("/")
	public void initPage(HttpServletResponse response) {
		response.setHeader("Location", "http://localhost:8080/swagger-ui.html");
		response.setStatus(302);
	}
}
