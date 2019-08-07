package bd.edu.seu.coursemanagementserver.controller;

import bd.edu.seu.coursemanagementserver.model.Section;
import bd.edu.seu.coursemanagementserver.repository.SectionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/sections")
public class SectionController {
    private SectionRepository sectionRepository;

    public SectionController(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Section>> findAll() {
        return ResponseEntity.ok(sectionRepository.findAll());
    }

    @PostMapping(value = "")
    public ResponseEntity<Section> save(@RequestBody Section section) {
        Section savedSection = sectionRepository.save(section);
        return ResponseEntity.created(null).body(savedSection);
    }
}

/*
db.section.insert({"code" : "CSE1021","title": "Discrete Math"})
 */