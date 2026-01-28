package ostara2.api.admin.controller;

import ostara2.api.admin.model.Person;
import ostara2.api.admin.model.Dept;
import ostara2.api.admin.repository.PersonRepository;
import ostara2.api.admin.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Persons", description = "Person management")
@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DeptRepository deptRepository;

    @Operation(summary = "Get all persons")
    @GetMapping
    public ResponseEntity<?> getAllPersons() {
        return ResponseEntity.ok(personRepository.findAll());
    }

    @Operation(summary = "Add a new person to database")
    @PostMapping
    public ResponseEntity<?> addPerson(@RequestBody Person person) {
        // Validate department exists
        Integer deptId = person.getDept().getDeptId();
        Dept dept = deptRepository.findById(deptId)
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + deptId));

        person.setDept(dept);
        Person savedPerson = personRepository.save(person);
        return ResponseEntity.ok(savedPerson);
    }
}
