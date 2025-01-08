package foi.air.szokpt.transfetch.services;

import foi.air.szokpt.transfetch.dtos.responses.MerchantResponse;
import foi.air.szokpt.transfetch.dtos.responses.MidResponse;
import foi.air.szokpt.transfetch.dtos.responses.TidsResponse;
import foi.air.szokpt.transfetch.dtos.responses.TransactionsResponse;
import foi.air.szokpt.transfetch.entities.Merchant;
import foi.air.szokpt.transfetch.entities.Mid;
import foi.air.szokpt.transfetch.entities.Tid;
import foi.air.szokpt.transfetch.entities.Transaction;
import foi.air.szokpt.transfetch.repositories.MerchantRepository;
import foi.air.szokpt.transfetch.repositories.MidRepository;
import foi.air.szokpt.transfetch.repositories.TidRepository;
import foi.air.szokpt.transfetch.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DataFetchService {
    private final RestTemplate restTemplate;

    private final TidRepository tidRepository;
    private final MidRepository midRepository;
    private final MerchantRepository merchantRepository;
    private final TransactionRepository transactionRepository;

    @Value("${7pay.api.base.url}")
    private String baseUrl;

    public DataFetchService(RestTemplate restTemplate,
                            TidRepository tidRepository,
                            MidRepository midRepository,
                            MerchantRepository merchantRepository,
                            TransactionRepository transactionRepository) {
        this.restTemplate = restTemplate;
        this.tidRepository = tidRepository;
        this.midRepository = midRepository;
        this.merchantRepository = merchantRepository;
        this.transactionRepository = transactionRepository;
    }

    public void synchronizeData() {
        List<Tid> tids = fetchTids();
        tids.forEach(tid -> {
            Mid mid = fetchMidByTid(tid.getPosTid());
            if (mid != null) {
                Merchant merchant = fetchMerchantByMid(mid.getPosMid());
                if (merchant != null) {
                    Merchant savedMerchant = saveMerchant(merchant);
                    Mid savedMid = saveMid(mid, savedMerchant);
                    Tid savedTid = saveTid(tid, savedMid);

                    List<Transaction> transactions = fetchTransactionsByTid(savedTid.getPosTid());
                    transactions.forEach(transaction -> saveTransaction(transaction, savedTid));
                }
            }
        });
    }

    private List<Tid> fetchTids() {
        String url = baseUrl + "/tids";
        ResponseEntity<TidsResponse> response = restTemplate.getForEntity(url, TidsResponse.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().getTids();
        } else {
            return new ArrayList<>();
        }
    }

    private Mid fetchMidByTid(String tid) {
        String url = baseUrl + "/merchants/midBTid?tid=" + tid;
        ResponseEntity<MidResponse> response = restTemplate.getForEntity(url, MidResponse.class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().getMid();
        } else {
            return null;
        }
    }

    private Merchant fetchMerchantByMid(String mid) {
        String url = baseUrl + "/merchants/merchantByMid?mid=" + mid;
        ResponseEntity<MerchantResponse> response = restTemplate.getForEntity(url, MerchantResponse.class);
        return Optional.ofNullable(response.getBody())
                .map(MerchantResponse::getMerchant)
                .orElse(null);
    }

    private List<Transaction> fetchTransactionsByTid(String tid) {
        String date = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String url = baseUrl + "/transactions/" + tid + "?date=" + date;
        ResponseEntity<TransactionsResponse> response = restTemplate.getForEntity(url, TransactionsResponse.class);

        return Optional.ofNullable(response.getBody())
                .map(TransactionsResponse::getTransactions)
                .orElse(new ArrayList<>());
    }

    private Merchant saveMerchant(Merchant merchant) {
        return merchantRepository.findByOib(merchant.getOib())
                .orElseGet(() -> merchantRepository.save(merchant));
    }

    private Mid saveMid(Mid mid, Merchant merchant) {
        mid.setMerchant(merchant);
        return midRepository.findById(mid.getPosMid())
                .orElseGet(() -> midRepository.save(mid));
    }

    private Tid saveTid(Tid tid, Mid mid) {
        tid.setMid(mid);
        return tidRepository.findById(tid.getPosTid())
                .orElseGet(() -> tidRepository.save(tid));
    }

    private void saveTransaction(Transaction transaction, Tid tid) {
        if (!transactionRepository.existsById(transaction.getGuid())) {
            transaction.setTid(tid);
            transactionRepository.save(transaction);
        }
    }
}
