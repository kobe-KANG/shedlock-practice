package hello.shedlockpractice.scheduler;

import hello.shedlockpractice.service.TaskRunService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockConfiguration;
import net.javacrumbs.shedlock.core.LockingTaskExecutor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskRunScheduler {

    @Value("${env.vmname}")
    private String vmname;

    private final LockingTaskExecutor lockingTaskExecutor;
    private final TaskRunService taskRunService;

    @Scheduled(cron = "0/5 * * * * ?")
    @SchedulerLock(name = "AnnotationScheduleLock", lockAtLeastFor = "2s", lockAtMostFor = "4s")
    public void ScheduledTaskWithAnnotation(){
        taskRunService.runTask(vmname, "ANNOTATION", "Run task using @ScheduledLock annotation");
        log.info("------------- : Run task using annotation");
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void ScheduledTaskWithExecutor(){
        String lockName = "ExecutorScheduleLock";
        LockConfiguration lockConfig = new LockConfiguration(Instant.now(), lockName, Duration.ofSeconds(4), Duration.ofSeconds(2));
        Runnable runnable = () -> taskRunService.runTask(vmname, "EXECUTOR", "Run task using @ScheduledLock executor");
        lockingTaskExecutor.executeWithLock(runnable, lockConfig);
        log.info("------------- : Run task using executor");
    }

}
