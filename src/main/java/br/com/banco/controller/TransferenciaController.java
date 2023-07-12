package br.com.banco.controller;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.services.ContaService;
import br.com.banco.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    @Autowired
    private final TransferenciaService transferenciaService;

    @Autowired
    public TransferenciaController(TransferenciaService service) {
        this.transferenciaService = service;
    }

    @GetMapping
    public ResponseEntity<List<Transferencia>> listarTransferencias() {
        List<Transferencia> transferencias = transferenciaService.listarTransferencias();
        return ResponseEntity.ok(transferencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transferencia> obterTransferenciaPorId(@PathVariable Long id) {
        Optional<Transferencia> transferencia = transferenciaService.obterTransferenciaPorId(id);
        return transferencia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Transferencia> criarTransferencia(@RequestBody Transferencia transferencia) {
        Transferencia novaTransferencia = transferenciaService.criarTransferencia(transferencia);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTransferencia);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTransferencia(@PathVariable Long id) {
        boolean deletado = transferenciaService.deletarTransferencia(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
