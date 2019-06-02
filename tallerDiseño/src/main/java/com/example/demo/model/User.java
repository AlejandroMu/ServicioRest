package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.*;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NonNull
	private String username;
	@NonNull
	private String name;
	@NonNull
	private String lastName;
	@NonNull
	@NotBlank
	private String password;

	private boolean state;
	@NonNull
	@OneToOne
	private Pacient pacient;
}
