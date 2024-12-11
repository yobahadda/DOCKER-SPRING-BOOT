package com.example.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import java.util.List;
import com.example.test.model.Professeur;

@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {}
