package foi.air.szokpt.transfetch.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import foi.air.szokpt.transfetch.dtos.responses.MerchantResponse;
import foi.air.szokpt.transfetch.dtos.responses.MidResponse;
import foi.air.szokpt.transfetch.dtos.responses.TidsResponse;
import foi.air.szokpt.transfetch.entities.Merchant;
import foi.air.szokpt.transfetch.entities.Mid;
import foi.air.szokpt.transfetch.entities.Tid;
import foi.air.szokpt.transfetch.repositories.MerchantRepository;
import foi.air.szokpt.transfetch.repositories.MidRepository;
import foi.air.szokpt.transfetch.repositories.TidRepository;
import foi.air.szokpt.transfetch.repositories.TransactionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataFetchService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private final TidRepository tidRepository;
    private final MidRepository midRepository;
    private final MerchantRepository merchantRepository;
    private final TransactionRepository transactionRepository;

    public DataFetchService(RestTemplate restTemplate,
                            TidRepository tidRepository,
                            MidRepository midRepository,
                            MerchantRepository merchantRepository,
                            TransactionRepository transactionRepository,
                            ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.tidRepository = tidRepository;
        this.midRepository = midRepository;
        this.merchantRepository = merchantRepository;
        this.transactionRepository = transactionRepository;
        this.objectMapper = objectMapper;
    }

    private List<Tid> fetchTids() {
        String url = "https://api-uat.7pay.hr/tss/v1/tids";
        ResponseEntity<TidsResponse> response = restTemplate.getForEntity(url, TidsResponse.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().getTids();
        } else {
            System.err.println("Failed to fetch TIDs");
            return new ArrayList<>();
        }
    }

    private Mid fetchMidByTid(String tid) {
        String url = "https://api-uat.7pay.hr/tss/v1/merchants/midBTid?tid=" + tid;
        ResponseEntity<MidResponse> response = restTemplate.getForEntity(url, MidResponse.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().getMid();
        } else {
            System.err.println("Failed to fetch MID for TID: " + tid);
            return null;
        }
    }

    private Merchant fetchMerchantByMid(String mid) {
        String url = "https://api-uat.7pay.hr/tss/v1/merchants/merchantByMid?mid=" + mid;
        ResponseEntity<MerchantResponse> response = restTemplate.getForEntity(url, MerchantResponse.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody().getMerchant();
        } else {
            System.err.println("Failed to fetch MID for TID: " + mid);
            return null;
        }
    }

    public void fetchData() {
        List<Tid> tids = fetchTids();
        for (Tid tid : tids) {
            System.out.println("Processing TID: " + tid.getPosTid());
            Mid mid = fetchMidByTid(tid.getPosTid());
            if (mid != null) {
                Merchant merchant = fetchMerchantByMid(mid.getPosMid());
                if (merchant != null) {
                    Merchant finalMerchant = merchant;
                    merchant = merchantRepository.findByOib(merchant.getOib())
                            .orElseGet(() -> merchantRepository.save(finalMerchant));
                    mid.setMerchant(merchant);
                    Mid finalMid = mid;
                    mid = midRepository.findById(mid.getPosMid())
                            .orElseGet(() -> midRepository.save(finalMid));
                    tid.setMid(mid);
                    if (!tidRepository.existsById(tid.getPosTid())) {
                        tidRepository.save(tid);
                    }
                }
            }
        }
    }
}
