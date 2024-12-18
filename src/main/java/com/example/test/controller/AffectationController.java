package com.example.test.controller;

import com.example.test.dto.AffectationDTO;
import com.example.test.dto.ElementDTO;
import com.example.test.dto.EtudiantDTO;
import com.example.test.dto.ModuleDTO;
import com.example.test.service.AffectationService;
import com.example.test.service.ElementService;
import com.example.test.service.EtudiantService;
import com.example.test.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/affectation")
public class AffectationController {

    @Autowired
    private AffectationService affectationService;

    @Autowired
    private ElementService elementService;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private EtudiantService etudiantService;

    @GetMapping("/professeur/{professorId}/notes/{elementId}")
    public List<EtudiantDTO> getEtudiantsByElement(@PathVariable Long professorId, @PathVariable Long elementId){
        Long eleId = affectationService.getEtudiantsByElement(professorId,elementId).getId();
        return g
    }

    @GetMapping("/elements/{professorId}")
    public List<ElementDTO> getProfesseurElements(@PathVariable Long professorId){
        List<AffectationDTO> affectations =  affectationService.getProfesseurStudents(professorId);
        List<ElementDTO> elements = new ArrayList<>();
        for(AffectationDTO aff: affectations){
            Long elementId = aff.getElementDeModule_id();
            elements.add(elementService.getElementById(elementId));
        }
        return elements;
    }

    @GetMapping("/modules/{professorId}")
    public List<ModuleDTO> getProfesseurModules(@PathVariable Long professorId){
        List<ElementDTO> elements = getProfesseurElements(professorId);
        List<ModuleDTO> modules = new ArrayList<>();
        for(ElementDTO ele: elements){
            Long moduleId = ele.getModule_id();

            modules.add(moduleService.getModuleById(moduleId));
        }
        return modules;
    }

    @GetMapping("/students/{professeurId}")
    public List<EtudiantDTO> getProfesseurStudents(@PathVariable Long professeurId){
        List<ModuleDTO> modules = getProfesseurModules(professeurId);
        List<EtudiantDTO> etudiants = new ArrayList<>();
        List<Long> filieres = new ArrayList<>();
        for(ModuleDTO mod: modules){
            Long id = mod.getFiliere_id();
            if(!filieres.contains(id)){
                filieres.add(id);
                etudiants.addAll(etudiantService.getAllByFiliereId(id));
            }
        }
        return etudiants;
    }

}
