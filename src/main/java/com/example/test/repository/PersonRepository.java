package com.example.test.repository;

import com.example.test.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PersonRepository extends JpaRepository<Person,Long> {
}
