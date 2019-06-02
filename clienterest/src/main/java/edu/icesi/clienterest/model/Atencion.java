package edu.icesi.clienterest.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Atencion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NonNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateHour;
	@ManyToOne
	private Pacient pacient;
	@NonNull
	@NotBlank(message = "La descipcion es requerida")
	private String generalDescription;
	@NonNull
	@NotBlank(message = "Es necesario el procedimiento")
	private String procedureDone;
	private boolean transfer;
	
	private String placeTransfer;
	@NonNull
	@NotBlank(message = "Es necesario las observaciones")
	private String observations;
	@NonNull
	@OneToMany(fetch=FetchType.EAGER)
	private List<Supply> supplys;
}
