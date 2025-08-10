package hello.shedlockpractice.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AlarmScheduler {

    @Scheduled(cron = "0/30 * * * * ?")
    @SchedulerLock(name = "AlarmScheduler_scheduledTask", lockAtLeastFor = "9s", lockAtMostFor = "9s")
    public void sendAlarm(){
        log.info("------------- : HOO ");
    }

}
