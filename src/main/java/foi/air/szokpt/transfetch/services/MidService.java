package foi.air.szokpt.transfetch.services;

import foi.air.szokpt.transfetch.entities.Merchant;
import foi.air.szokpt.transfetch.entities.Mid;
import foi.air.szokpt.transfetch.repositories.MidRepository;
import org.springframework.stereotype.Service;

@Service
public class MidService {

    private final MidRepository midRepository;

    public MidService(MidRepository midRepository) {
        this.midRepository = midRepository;
    }

    public Mid saveMid(Mid mid, Merchant merchant) {
        mid.setMerchant(merchant);
        return midRepository.findById(mid.getPosMid())
                .orElseGet(() -> midRepository.save(mid));
    }
}

