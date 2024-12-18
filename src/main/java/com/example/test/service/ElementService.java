package com.example.test.service;

import com.example.test.dto.ElementDTO;
import com.example.test.repository.ElementDeModuleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ElementService {

    @Autowired
    private final ElementDeModuleRepository elementDeModuleRepository;

    public ElementService(ElementDeModuleRepository elementDeModuleRepository){
        this.elementDeModuleRepository = elementDeModuleRepository;
    }

    public ElementDTO getElementById(Long elementId){
        return new ElementDTO(elementDeModuleRepository.findById(elementId)
                .orElseThrow(() -> new EntityNotFoundException("Element Not Found")));
    }
}
