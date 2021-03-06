package com.example.demo.model;
import java.util.*;

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
public class MedicineInventory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int amountAvailable;
	@NonNull
	@NotBlank(message = "Es necesario la ubicacion del inventario")
	private String location;
	@NonNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateExpiration;
	
	@ManyToOne
	@JoinColumn(name = "medicine_id")
	@JsonIgnore
	private Medicine medicine;

	@Override
	public String toString() {
		return "Cantidad :"+amountAvailable + "\n ubicacion " + location;
	}
	
}
