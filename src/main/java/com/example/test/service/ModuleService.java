package com.example.test.service;

import com.example.test.dto.ModuleDTO;
import com.example.test.repository.ModuleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleService {

    @Autowired
    private final ModuleRepository moduleRepository;

    public ModuleService(ModuleRepository moduleRepository){
        this.moduleRepository = moduleRepository;
    }

    public ModuleDTO getModuleById(Long id){
        return new ModuleDTO(moduleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Module Not Found")));
    }
}
