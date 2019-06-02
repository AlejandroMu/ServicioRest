package edu.icesi.model;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
	
	@ManyToOne
	private Medicine medicine;
	@NonNull
	private Integer amount; 
	
	@ManyToOne
	private Pacient pacient; 
	@NonNull
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateHour; 
	@NonNull
	private String observation; 
	@NonNull
	@NotBlank(message = "Este campo es necesario")
	private String pathology; 
	
	@ManyToOne
	private Atencion atencion;
	
}
