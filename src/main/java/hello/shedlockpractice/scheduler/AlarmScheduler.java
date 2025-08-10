package hello.shedlockpractice.scheduler;

import hello.shedlockpractice.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AlarmScheduler {

    @Value("${env.vmname}")
    private String vmname;

    private final AlarmRepository alarmRepository;

    @Scheduled(cron = "0/5 * * * * ?")
    @SchedulerLock(name = "AlarmScheduler_scheduledTask", lockAtLeastFor = "2s", lockAtMostFor = "4s")
    public void sendAlarm(){
        alarmRepository.addAlarm(vmname, "HOOO!!!!");
        log.info("------------- : HOO ");
    }

}
