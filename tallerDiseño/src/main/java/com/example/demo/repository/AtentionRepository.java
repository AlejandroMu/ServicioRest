package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.*;
import java.util.*;


@Repository
public interface AtentionRepository extends CrudRepository<Atencion, Integer> {
    List<Atencion> findByDateHour(Date d);
}
