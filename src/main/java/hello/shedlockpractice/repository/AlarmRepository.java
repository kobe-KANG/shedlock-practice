package hello.shedlockpractice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class AlarmRepository {

    private final JdbcTemplate jdbcTemplate;

    public int addAlarm(String name, String description) {
        String sql = "insert into shedlock_alarm (alrm_tmstp, name, description) values (?, ?, ?)";
        return jdbcTemplate.update(sql, LocalDateTime.now(), name, description);
    }

}
