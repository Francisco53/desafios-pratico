package br.com.banco.services;

import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public List<Transferencia> obterTransferencias(String nomeOperadorTransacao, LocalDate dataInicial, LocalDate dataFinal) {
        List<Transferencia> transferencias;

        if (nomeOperadorTransacao != null && dataInicial != null && dataFinal != null) {
            transferencias = transferenciaRepository.findByNomeOperadorTransacao(nomeOperadorTransacao);
            transferencias.retainAll(transferenciaRepository.findByDataTransferenciaBetween(dataInicial, dataFinal));
        } else if (nomeOperadorTransacao != null) {
            transferencias = transferenciaRepository.findByNomeOperadorTransacao(nomeOperadorTransacao);
        } else if (dataInicial != null && dataFinal != null) {
            transferencias = transferenciaRepository.findByDataTransferenciaBetween(dataInicial, dataFinal);
        } else {
            transferencias = transferenciaRepository.findAll();
        }

        return transferencias;
    }

    public List<Transferencia> findTransferencias(String minDate, String maxDate, String nomeOperadorTransacao) {
        LocalDate min = null;
        LocalDate max = null;

        if (!minDate.isEmpty()) {
            min = LocalDate.parse(minDate);
        }
        if (!maxDate.isEmpty()) {
            max = LocalDate.parse(maxDate);
        }

        return transferenciaRepository.findTransferencias(min, max, nomeOperadorTransacao);
    }

}
