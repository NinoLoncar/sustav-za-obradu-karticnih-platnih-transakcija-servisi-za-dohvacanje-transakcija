package foi.air.szokpt.transfetch.utils;

import foi.air.szokpt.transfetch.services.DataFetchService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataSyncScheduler {
    private final DataFetchService dataFetchService;

    public DataSyncScheduler(DataFetchService fetchService) {
        this.dataFetchService = fetchService;
    }

    @Scheduled(cron = "0 0 0,12 * * ?", zone = "Europe/Zagreb")
    public void periodicDataFetch() {
        dataFetchService.synchronizeData();
    }
}
