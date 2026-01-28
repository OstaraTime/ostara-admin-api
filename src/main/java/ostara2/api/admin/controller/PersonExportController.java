package ostara2.api.admin.controller;

import ostara2.api.admin.model.Person;
import ostara2.api.admin.repository.PersonRepository;
import ostara2.api.admin.service.CsvExportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/persons")
public class PersonExportController {

    private final PersonRepository personRepository;
    private final CsvExportService csvExportService;

    public PersonExportController(PersonRepository personRepository,
                                  CsvExportService csvExportService) {
        this.personRepository = personRepository;
        this.csvExportService = csvExportService;
    }

    @Operation(
        summary = "Export persons as CSV with JWT tokens",
        description = "Takes a list of person IDs and returns a CSV file containing ID, name, department and signed JWT for use in the OSTARA QR code generator."
    )
    @PostMapping("/export")
    public ResponseEntity<String> exportPersons(@RequestBody List<Integer> personIds) {

        List<Person> persons = personRepository.findAllByIdInWithDept(personIds);

        String csv = persons.stream()
                .map(p -> {
                    String tokenValue = java.util.UUID.randomUUID()
                            .toString().replace("-", "");
                    String jwt = csvExportService.generateJwt(tokenValue);

                    return String.join(",",
                            csvExportService.escape(p.getPersonId().toString()),
                            csvExportService.escape(p.getName()),
                            csvExportService.escape(p.getDept().getName()),
                            csvExportService.escape(jwt)
                    );
                })
                .collect(Collectors.joining("\n"));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=persons.csv")
                .contentType(MediaType.TEXT_PLAIN)
                .body(csv);
    }
}
