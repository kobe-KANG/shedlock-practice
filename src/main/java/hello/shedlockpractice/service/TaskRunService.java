package hello.shedlockpractice.service;

import hello.shedlockpractice.repository.TaskRunHistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskRunService {

    private final TaskRunHistRepository taskRunHistRepository;

    public void runTask(String name, String type, String description) {
        taskRunHistRepository.addHist(name, type, description);
    }

}
