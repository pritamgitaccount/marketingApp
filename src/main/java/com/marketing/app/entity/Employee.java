package com.marketing.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees", uniqueConstraints = {
	    @UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "mobile") })
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	
	
	@Column(name = "email", nullable = false)
	private String email;
	
	
	@Column(name = "mobile", nullable = false)
	private String mobile;


}
