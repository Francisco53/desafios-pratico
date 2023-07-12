package br.com.banco.services;

import br.com.banco.entities.Conta;
import br.com.banco.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private final ContaRepository contaRepository;

    @Autowired
    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    public Optional<Conta> obterContaPorId(Long id) {
        return contaRepository.findById(id);
    }

    public Conta criarConta(Conta conta) {
        return contaRepository.save(conta);
    }

    public Conta atualizarConta(Long id, Conta contaAtualizada) {
        Optional<Conta> optionalConta = contaRepository.findById(id);
        if (optionalConta.isPresent()) {
            Conta conta = optionalConta.get();
            conta.setNomeResponsavel(contaAtualizada.getNomeResponsavel());
            return contaRepository.save(conta);
        }
        return null;
    }

    public boolean deletarConta(Long id) {
        Optional<Conta> optionalConta = contaRepository.findById(id);
        if (optionalConta.isPresent()) {
            contaRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
