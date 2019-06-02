package edu.icesi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Medicine {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NonNull
	@NotBlank(message = "El nombre es necesario")
	private String name;
	@NonNull
	@NotBlank(message = "El nombre generico es necesario")
	private String genericName;
	@NonNull
	@NotBlank(message = "El nombre del laboratorio es necesario")
	private String laboratory;
	@NonNull
	@NotBlank(message = "El tipo de administracion es necesario")
	private String administationType;
	@NonNull
	@NotBlank(message = "las indicaciones son necesarias")
	private String indications;
	@NonNull
	private String contraIndications;
	
	
	
}
