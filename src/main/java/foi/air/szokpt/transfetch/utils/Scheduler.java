package foi.air.szokpt.transfetch.utils;

import foi.air.szokpt.transfetch.services.DataFetchService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private final DataFetchService dataFetchService;

    public Scheduler(DataFetchService fetchService) {
        this.dataFetchService = fetchService;
    }

    //@Scheduled(cron = "0 0 1,13 * * ?", zone = "Europe/Zagreb")
    @Scheduled(fixedRate = 30000)
    public void periodicDataFetch() {
        dataFetchService.synchronizeData();
    }
}
