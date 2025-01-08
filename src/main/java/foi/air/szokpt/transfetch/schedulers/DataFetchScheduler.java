package foi.air.szokpt.transfetch.schedulers;

import foi.air.szokpt.transfetch.handlers.DataFetchHandler;
import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataFetchScheduler {
    private final DataFetchHandler dataFetchHandler;

    public DataFetchScheduler(DataFetchHandler fetchService) {
        this.dataFetchHandler = fetchService;
    }

    @PostConstruct
    @Scheduled(cron = "0 0 0,12 * * ?", zone = "Europe/Zagreb")
    public void periodicDataFetch() {
        dataFetchHandler.fetchData();
    }
}
