package com.marketing.app.dto;


import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpDto {

	private long id;

	@NotEmpty
	@Size(min = 3, message = "First Name should have at least 2 characters")
	private String firstName;

	@NotEmpty
	@Size(min = 2, message = "Last Name should have at least 2 characters")
	private String lastName;

	@NotEmpty
	@Size(min = 2, max = 150,message = "Email size should be 10 to 15")
	private String email;
	
	@NotEmpty
	@Size(min = 10, max = 15, message = "Mobile number should be 10 to 15")
	private String mobile;

}
