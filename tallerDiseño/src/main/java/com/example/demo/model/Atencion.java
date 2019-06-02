package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	

	@OneToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "atencion")
	private List<Supply> supplys;
	@ManyToOne
	@JoinColumn(name = "pacient_id")
	@JsonIgnore
	private Pacient pacient;
}
