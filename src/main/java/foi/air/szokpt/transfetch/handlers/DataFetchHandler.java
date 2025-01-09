package foi.air.szokpt.transfetch.handlers;

import foi.air.szokpt.transfetch.clients.SevenPayApiClient;
import foi.air.szokpt.transfetch.entities.Merchant;
import foi.air.szokpt.transfetch.entities.Mid;
import foi.air.szokpt.transfetch.entities.Tid;
import foi.air.szokpt.transfetch.entities.Transaction;
import foi.air.szokpt.transfetch.enums.CardBrand;
import foi.air.szokpt.transfetch.services.MerchantService;
import foi.air.szokpt.transfetch.services.MidService;
import foi.air.szokpt.transfetch.services.TidService;
import foi.air.szokpt.transfetch.services.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataFetchHandler {

    private final SevenPayApiClient apiClient;
    private final MerchantService merchantService;
    private final MidService midService;
    private final TidService tidService;
    private final TransactionService transactionService;

    public DataFetchHandler(
            SevenPayApiClient apiClient,
            MerchantService merchantService,
            MidService midService,
            TidService tidService,
            TransactionService transactionService) {
        this.apiClient = apiClient;
        this.merchantService = merchantService;
        this.midService = midService;
        this.tidService = tidService;
        this.transactionService = transactionService;
    }

    public void fetchData() {
        List<Tid> tids = apiClient.fetchTids();
        for (Tid tid : tids) {
            Mid mid = apiClient.fetchMidByTid(tid.getPosTid());
            if (mid == null) continue;

            Merchant merchant = apiClient.fetchMerchantByMid(mid.getPosMid());
            if (merchant == null) continue;

            Merchant savedMerchant = merchantService.saveMerchant(merchant);
            Mid savedMid = midService.saveMid(mid, savedMerchant);
            Tid savedTid = tidService.saveTid(tid, savedMid);

            List<Transaction> transactions = apiClient.fetchTransactionsByTid(savedTid.getPosTid());
            List<Transaction> validTransactions = transactions.stream()
                    .filter(transaction -> transaction.getCardBrand() != CardBrand.UNKNOWN)
                    .toList();

            transactionService.saveTransactions(validTransactions, savedTid);
        }
    }

}
