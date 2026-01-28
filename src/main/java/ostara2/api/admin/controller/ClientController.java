package ostara2.api.admin.controller;

import ostara2.api.admin.model.Client;
import ostara2.api.admin.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Clients", description = "For managing OSTARA clients")
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Operation(summary = "Get list of all clients")
    @GetMapping
    public ResponseEntity<?> getAllClients() {
        return ResponseEntity.ok(clientRepository.findAll());
    }

    @Operation(summary = "Create a new client")
    @PostMapping
    public ResponseEntity<?> addClient(@RequestBody Client client) {
        Client savedClient = clientRepository.save(client);
        return ResponseEntity.ok(savedClient);
    }
}
