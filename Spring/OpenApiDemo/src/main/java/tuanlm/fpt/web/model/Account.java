package tuanlm.fpt.web.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	@NotBlank
	private String username;
	@NotBlank
	private String password;
}
