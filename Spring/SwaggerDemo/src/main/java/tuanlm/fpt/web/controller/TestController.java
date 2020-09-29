package tuanlm.fpt.web.controller;

import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

/**
 * The Class TestController.
 */
@RestController
@RequestMapping("/api")
@Api(value = "Test API")
public class TestController {
	
	/**
	 * Gets the string test.
	 *
	 * @return the string test
	 */
	@GetMapping("/get-string")
	public ResponseEntity<String> getStringTest() {
		return new ResponseEntity<String>("Test API OK", HttpStatus.OK);
	}
	
	/**
	 * Gets the string param.
	 *
	 * @param text the text
	 * @return the string param
	 */
	@GetMapping("/get-string-with-param")
	public ResponseEntity<String> getStringParam(@NotBlank String text) {
		return new ResponseEntity<String>("Test API OK" + text	, HttpStatus.OK);
	}
	
	@PostMapping("/get-string-post")
	public ResponseEntity<String> getStringParamByPost(@RequestBody String text) {
		return new ResponseEntity<String>("Test API OK" + text	, HttpStatus.OK);
	}
}
