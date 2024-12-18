package com.example.test.service;

import com.example.test.dto.AffectationDTO;
import com.example.test.model.AffectationProfesseur;
import com.example.test.repository.AffectationProfesseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AffectationService {

    @Autowired
    private final AffectationProfesseurRepository affectationRepository;

    public AffectationService(AffectationProfesseurRepository affectationRepository) {
        this.affectationRepository = affectationRepository;
    }

    public List<AffectationDTO> getProfesseurStudents(Long professeurId){
        // get elements
        List<AffectationDTO> elements = affectationRepository.findAllByProfesseurId(professeurId).stream().map(AffectationDTO::new).toList();
        return elements;
    }

    public AffectationProfesseur getEtudiantsByElement(Long profId, Long eleId){
        return affectationRepository.findByProfesseurIdAndElementDeModuleId(profId, eleId);
    }

}
