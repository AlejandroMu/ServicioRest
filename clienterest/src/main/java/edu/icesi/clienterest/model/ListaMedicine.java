package edu.icesi.clienterest.model;

import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class ListaMedicine{
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private List<Medicine> list;
}