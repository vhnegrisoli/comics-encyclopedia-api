package br.com.comicsencyclopedia.modules.comics.scheduler;

import br.com.comicsencyclopedia.modules.comics.service.ComicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ComicsScheduler {

    @Autowired
    private ComicsService comicsService;

    @Scheduled(cron = "${app-config.schedulers.delete-data-every-hour}")
    public void deleteDataIfExists() {
        comicsService.deleteDataIfExists();
    }
}
