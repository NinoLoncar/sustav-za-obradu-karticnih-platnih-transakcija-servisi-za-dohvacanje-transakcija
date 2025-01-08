package foi.air.szokpt.transfetch.services;

import foi.air.szokpt.transfetch.entities.Mid;
import foi.air.szokpt.transfetch.entities.Tid;
import foi.air.szokpt.transfetch.repositories.TidRepository;
import org.springframework.stereotype.Service;

@Service
public class TidService {

    private final TidRepository tidRepository;

    public TidService(TidRepository tidRepository) {
        this.tidRepository = tidRepository;
    }

    public Tid saveTid(Tid tid, Mid mid) {
        tid.setMid(mid);
        return tidRepository.findById(tid.getPosTid())
                .orElseGet(() -> tidRepository.save(tid));
    }

}
