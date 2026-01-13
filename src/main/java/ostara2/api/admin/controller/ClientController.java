package ostara2.api.admin.controller;

import ostara2.api.admin.model.Client;
import ostara2.api.admin.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    // Existing GET endpoint
    @GetMapping
    public ResponseEntity<?> getAllClients() {
        return ResponseEntity.ok(clientRepository.findAll());
    }

    // NEW: Add a new client
    @PostMapping
    public ResponseEntity<?> addClient(@RequestBody Client client) {
        Client savedClient = clientRepository.save(client);
        return ResponseEntity.ok(savedClient);
    }
}


/*package ostara2.api.admin.controller;

import ostara2.api.admin.model.Client;
import ostara2.api.admin.repository.ClientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }
}
*/
