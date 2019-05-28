package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.icesi.model.UrgencyAtention;

import java.util.*;


@Repository
public interface AtentionRepository extends CrudRepository<UrgencyAtention, Integer> {
    List<UrgencyAtention> findByDateHour(Date d);
}
