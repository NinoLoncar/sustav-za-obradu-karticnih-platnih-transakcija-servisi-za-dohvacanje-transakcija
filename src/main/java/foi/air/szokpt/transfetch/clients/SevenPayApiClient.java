package foi.air.szokpt.transfetch.clients;

import foi.air.szokpt.transfetch.dtos.responses.MerchantResponse;
import foi.air.szokpt.transfetch.dtos.responses.MidResponse;
import foi.air.szokpt.transfetch.dtos.responses.TidsResponse;
import foi.air.szokpt.transfetch.dtos.responses.TransactionsResponse;
import foi.air.szokpt.transfetch.entities.Merchant;
import foi.air.szokpt.transfetch.entities.Mid;
import foi.air.szokpt.transfetch.entities.Tid;
import foi.air.szokpt.transfetch.entities.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SevenPayApiClient {
    private final RestTemplate restTemplate;

    @Value("${7pay.api.base.url}")
    private String baseUrl;

    public SevenPayApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Tid> fetchTids() {
        String url = baseUrl + "/tids";
        try {
            ResponseEntity<TidsResponse> response = restTemplate.getForEntity(url, TidsResponse.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody().getTids();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Mid fetchMidByTid(String tid) {
        String url = baseUrl + "/merchants/midBTid?tid=" + tid;
        try {
            ResponseEntity<MidResponse> response = restTemplate.getForEntity(url, MidResponse.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody().getMid();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Merchant fetchMerchantByMid(String mid) {
        String url = baseUrl + "/merchants/merchantByMid?mid=" + mid;
        try {
            ResponseEntity<MerchantResponse> response = restTemplate.getForEntity(url, MerchantResponse.class);
            return Optional.ofNullable(response.getBody())
                    .map(MerchantResponse::getMerchant)
                    .orElse(null);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Transaction> fetchTransactionsByTid(String tid) {
        String date = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String url = baseUrl + "/transactions/" + tid + "?date=" + date;
        try {
            ResponseEntity<TransactionsResponse> response = restTemplate.getForEntity(url, TransactionsResponse.class);
            return Optional.ofNullable(response.getBody())
                    .map(TransactionsResponse::getTransactions)
                    .orElse(new ArrayList<>());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return new ArrayList<>();
    }
}
