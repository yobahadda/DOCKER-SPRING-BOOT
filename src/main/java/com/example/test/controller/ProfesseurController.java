package com.example.test.controller;

import com.example.test.dto.ProfesseurDTO;
import com.example.test.service.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professeurs")
public class ProfesseurController {

    @Autowired
    private ProfesseurService professeurService;

    @GetMapping("/by-login/{login}")
    public ProfesseurDTO getProfesseurByLogin(@PathVariable String login){
        return professeurService.getProfesseurByLogin(login);
    }
}
