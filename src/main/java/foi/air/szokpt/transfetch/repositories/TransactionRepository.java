package foi.air.szokpt.transfetch.repositories;

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
    @Query("SELECT t FROM Transaction t WHERE t.transactionTimestamp >= :startOfDay AND" +
            " t.transactionTimestamp < :endOfDay AND t.tid.posTid = :posTid")
    List<Transaction> findByTidAndTimestamp(@Param("posTid") String posTid,
                                            @Param("startOfDay") LocalDateTime startOfDay,
                                            @Param("endOfDay") LocalDateTime endOfDay);
}
