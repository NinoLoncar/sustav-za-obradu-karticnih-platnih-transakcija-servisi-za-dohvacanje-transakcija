package foi.air.szokpt.transfetch.services;

import foi.air.szokpt.transfetch.entities.Tid;
import foi.air.szokpt.transfetch.entities.Transaction;
import foi.air.szokpt.transfetch.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
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


