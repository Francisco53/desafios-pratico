package br.com.banco.repositories;

import br.com.banco.entities.Transferencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    List<Transferencia> findByNomeOperadorTransacaoAndDataTransferenciaBetween(String nomeOperadorTransacao, LocalDateTime dataInicial, LocalDateTime dataFinal);
    List<Transferencia> findByDataTransferenciaBetween(LocalDate dataInicial, LocalDate dataFinal);
    List<Transferencia> findByNomeOperadorTransacao(String nomeOperadorTransacao);

    @Query("SELECT obj FROM Transferencia obj WHERE (:minDate IS NULL OR obj.dataTransferencia >= :minDate) " +
            "AND (:maxDate IS NULL OR obj.dataTransferencia <= :maxDate) AND (:nomeOperadorTransacao " +
            "IS NULL OR obj.nomeOperadorTransacao = :nomeOperadorTransacao)")
    List<Transferencia> findTransferencias(@Param("minDate") LocalDate minDate, @Param("maxDate") LocalDate maxDate,
                                           @Param("nomeOperadorTransacao") String nomeOperadorTransacao);
}
