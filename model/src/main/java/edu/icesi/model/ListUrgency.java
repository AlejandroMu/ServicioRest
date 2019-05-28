package edu.icesi.model;

import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ListUrgency{
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private List<UrgencyAtention> list;

}