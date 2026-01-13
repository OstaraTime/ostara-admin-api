package ostara2.api.admin.controller;

import ostara2.api.admin.model.Dept;
import ostara2.api.admin.repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/depts")
public class DeptController {

    private final DeptRepository deptRepository;

    @Autowired
    public DeptController(DeptRepository deptRepository) {
        this.deptRepository = deptRepository;
    }

    // GET all departments
    @GetMapping
    public List<Dept> getAllDepts() {
        return deptRepository.findAll();
    }

    // POST a new department
    @PostMapping
    public ResponseEntity<Dept> createDept(@RequestBody Dept dept) {
        Dept savedDept = deptRepository.save(dept);
        return ResponseEntity.ok(savedDept);
    }

    // GET a single department by ID
    @GetMapping("/{id}")
    public ResponseEntity<Dept> getDeptById(@PathVariable Integer id) {
        return deptRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
