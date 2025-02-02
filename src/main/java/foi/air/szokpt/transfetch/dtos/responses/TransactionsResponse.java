package foi.air.szokpt.transfetch.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import foi.air.szokpt.transfetch.entities.Transaction;

import java.util.List;

public class TransactionsResponse {
    @JsonProperty("transactions")
    private List<Transaction> transactions;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
