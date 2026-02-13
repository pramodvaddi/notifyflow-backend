package com.pramdvaddiraju.notifyflow_backend.repository;

import com.pramdvaddiraju.notifyflow_backend.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    List<Reminder> findByScheduledTimeLessThanEqualAndStatus(LocalDateTime time, String status);
}
