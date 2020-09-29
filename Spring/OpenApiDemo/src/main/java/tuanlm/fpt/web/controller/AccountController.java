package tuanlm.fpt.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tuanlm.fpt.web.model.Account;

@RestController
@RequestMapping("api/v1")
public class AccountController {
	
//	@Operation(description = "View All List User", responses = {
//			@ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = Account.class))), 
//					responseCode = "200")
//	})
//	
//	@ApiResponses(value = {
//			@ApiResponse(responseCode = "200", description = "Success!"),
//			@ApiResponse(responseCode = "403", description = "Forbidden"),
//			@ApiResponse(responseCode = "404", description = "Not Found!"),
//			@ApiResponse(responseCode = "500", description = "Server Error!")
//	})
	
	@GetMapping("/get-all")
	public ResponseEntity<List<Account>> getListAccount() {
		return new ResponseEntity<List<Account>>(
				Arrays.asList(new Account("leminhtuan", "1"), new Account("tuanlm", "2")), 
				HttpStatus.OK);
	}
	
	@PostMapping("post-all")
	public ResponseEntity<List<Account>> getListAccountPost() {
		return new ResponseEntity<List<Account>>(
				Arrays.asList(new Account("leminhtuan", "1"), new Account("tuanlm", "2")), 
				HttpStatus.OK);
	}	
}
