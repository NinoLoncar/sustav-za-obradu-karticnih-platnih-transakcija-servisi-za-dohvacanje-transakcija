package foi.air.szokpt.transfetch.services;

import foi.air.szokpt.transfetch.entities.Merchant;
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

    public void assignYesterdayTransactions(List<Merchant> merchants) {
        merchants.forEach(merchant ->
                merchant.getMids().forEach(mid ->
                        mid.getTids().forEach(tid -> {
                            List<Transaction> filteredTransactions = getYesterdayTransactions(tid);
                            tid.setTransactions(filteredTransactions);
                        })
                )
        );
    }

    private List<Transaction> getYesterdayTransactions(Tid tid) {
        LocalDateTime startOfDay = LocalDate.now().minusDays(1).atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().atStartOfDay();
        
        return transactionRepository.findByTidAndTimestamp(tid.getPosTid(), startOfDay, endOfDay);
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


