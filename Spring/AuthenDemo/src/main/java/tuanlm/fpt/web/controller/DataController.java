package tuanlm.fpt.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo-service")
public class DataController {
	
	@GetMapping("/get-demo-list")
	public ResponseEntity<List<Integer>> getDemoList() {
		return new ResponseEntity<List<Integer>>(Arrays.asList(5, 6, 7, 8) , HttpStatus.OK);
	}
}
