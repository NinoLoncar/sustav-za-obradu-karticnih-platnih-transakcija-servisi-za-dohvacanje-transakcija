package foi.air.szokpt.transfetch.repositories;

import foi.air.szokpt.transfetch.entities.Tid;
import foi.air.szokpt.transfetch.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    @Query("SELECT t FROM Transaction t WHERE t.tid = :tid AND t.transactionTimestamp BETWEEN :startOfDay AND :endOfDay")
    List<Transaction> findByTidAndTransactionTimestamp(
            @Param("tid") Tid tid,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay);
}
