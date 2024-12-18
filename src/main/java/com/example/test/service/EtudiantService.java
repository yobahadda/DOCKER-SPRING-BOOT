    package com.example.test.service;

    import com.example.test.dto.EtudiantDTO;
    import com.example.test.repository.EtudiantRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    public class EtudiantService {

        @Autowired
        private final EtudiantRepository etudiantRepository;

        public EtudiantService(EtudiantRepository etudiantRepository) {
            this.etudiantRepository = etudiantRepository;
        }

        public List<EtudiantDTO> getAllEtudiants(){
            return etudiantRepository.findAll().stream().map(EtudiantDTO::new).toList();
        }

        public List<EtudiantDTO> getAllByFiliereId(Long id){
            return etudiantRepository.findAllByFiliere_Id(id).stream().map(EtudiantDTO::new).toList();
        }
    }
