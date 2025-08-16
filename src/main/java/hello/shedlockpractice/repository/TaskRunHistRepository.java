package hello.shedlockpractice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class TaskRunHistRepository {

    private final JdbcTemplate jdbcTemplate;

    public int addHist(String name, String type, String description) {
        String sql = "insert into TASK_RUN_HIST (run_tmstp, name, type, description) values (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, LocalDateTime.now(), name, type, description);
    }

}
