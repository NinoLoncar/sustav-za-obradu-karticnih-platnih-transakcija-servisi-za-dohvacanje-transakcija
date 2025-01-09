package foi.air.szokpt.transfetch.services;

import foi.air.szokpt.transfetch.entities.Tid;
import foi.air.szokpt.transfetch.entities.Transaction;
import foi.air.szokpt.transfetch.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactionsForYesterday(Tid tid) {
        LocalDateTime startOfYesterday = LocalDate.now().minusDays(1).atStartOfDay();
        LocalDateTime endOfYesterday = startOfYesterday.plusDays(1).minusNanos(1);

        return transactionRepository.findByTidAndTransactionTimestamp(tid, startOfYesterday, endOfYesterday);
    }

    public void saveTransactions(List<Transaction> transactions, Tid tid) {
        transactions.forEach(transaction -> {
            if (!transactionRepository.existsById(transaction.getGuid())) {
                transaction.setTid(tid);
                transactionRepository.save(transaction);
            }
        });
    }
}


