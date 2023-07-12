package br.com.banco.services;

import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransferenciaService {

    @Autowired
    private final TransferenciaRepository transferenciaRepository;

    @Autowired
    public TransferenciaService(TransferenciaRepository repository) {
        this.transferenciaRepository = repository;
    }

    public List<Transferencia> listarTransferencias() {
        return transferenciaRepository.findAll();
    }

    public Optional<Transferencia> obterTransferenciaPorId(Long id) {
        return transferenciaRepository.findById(id);
    }

    public Transferencia criarTransferencia(Transferencia transferencia) {
        return transferenciaRepository.save(transferencia);
    }


    public boolean deletarTransferencia(Long id) {
        Optional<Transferencia> optionalTransferencia = transferenciaRepository.findById(id);
        if (optionalTransferencia.isPresent()) {
            transferenciaRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
