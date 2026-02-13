package com.pramdvaddiraju.notifyflow_backend.service;

import com.pramdvaddiraju.notifyflow_backend.entity.Reminder;
import com.pramdvaddiraju.notifyflow_backend.repository.ReminderRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReminderScheduler {
    private ReminderRepository reminderRepository;
    private EmailService emailService;


    public ReminderScheduler(ReminderRepository reminderRepository, EmailService emailService){
        this.emailService = emailService;
        this.reminderRepository = reminderRepository;
    }

    @Scheduled(fixedRate = 30000)
    public void processReminders(){



        List<Reminder> reminders =
                reminderRepository.findByScheduledTimeLessThanEqualAndStatus(
                        LocalDateTime.now(), "PENDING");

        for(Reminder reminder: reminders){
            emailService.sendEmail(
                    reminder.getEmail(),
                    "Scheduled Reminder",
                    reminder.getMessage()
            );
            reminder.setStatus("SENT");
            reminderRepository.save(reminder);
        }
    }



}
