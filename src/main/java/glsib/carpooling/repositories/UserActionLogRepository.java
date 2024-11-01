package glsib.carpooling.repositories;

import glsib.carpooling.entities.UserActionLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserActionLogRepository extends JpaRepository<UserActionLog, Long> {
    List<UserActionLog> findAllByOrderByTimestampDesc();
}
