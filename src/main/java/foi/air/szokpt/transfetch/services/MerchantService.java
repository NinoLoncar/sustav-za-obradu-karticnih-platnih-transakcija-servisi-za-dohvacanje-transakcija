package foi.air.szokpt.transfetch.services;

import foi.air.szokpt.transfetch.entities.Merchant;
import foi.air.szokpt.transfetch.repositories.MerchantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public MerchantService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public List<Merchant> getMerchants() {
        return merchantRepository.findAll();
    }

    public Merchant saveMerchant(Merchant merchant) {
        return merchantRepository.findByOib(merchant.getOib())
                .orElseGet(() -> merchantRepository.save(merchant));
    }
}

