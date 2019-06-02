package edu.icesi.clienterest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JoinColumn(name = "pacient_id")
	@JsonIgnore
	private Pacient pacient;
}
