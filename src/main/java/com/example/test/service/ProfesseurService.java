package com.example.test.service;

import com.example.test.dto.ProfesseurDTO;
import com.example.test.repository.ProfesseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesseurService {

    @Autowired
    private final ProfesseurRepository professeurRepository;

    public ProfesseurService(ProfesseurRepository professeurRepository) {
        this.professeurRepository = professeurRepository;
    }

    public ProfesseurDTO getProfesseurByLogin(String login){
        return new ProfesseurDTO(professeurRepository.findByLogin(login));
    }
}
