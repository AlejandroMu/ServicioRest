package edu.icesi.clienterest.model;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Entity
@Data 
@NoArgsConstructor
@RequiredArgsConstructor
public class Supply {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NonNull
	private Integer amount; 
	@NonNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateHour; 
	@NonNull
	private String observation; 
	@NonNull
	@NotBlank(message = "Este campo es necesario")
	private String pathology; 
	
	@ManyToOne
	@JoinColumn(name = "atencion_id")
	@JsonIgnore
	private Atencion atencion;
	@ManyToOne
	@JoinColumn(name = "pacient_id")
	@JsonIgnore
	private Pacient pacient; 
	@ManyToOne
	@JoinColumn(name = "medicine_id")
	@JsonIgnore
	private Medicine medicine;
	
}
