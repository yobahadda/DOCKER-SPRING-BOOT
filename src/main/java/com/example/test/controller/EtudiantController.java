package com.example.test.controller;

import com.example.test.dto.EtudiantDTO;
import com.example.test.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    @GetMapping("")
    public List<EtudiantDTO> getAllEtudiants() {
        return etudiantService.getAllEtudiants();
    }

}
