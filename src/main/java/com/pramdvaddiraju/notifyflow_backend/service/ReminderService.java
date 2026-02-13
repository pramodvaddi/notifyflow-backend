package com.pramdvaddiraju.notifyflow_backend.service;

import com.pramdvaddiraju.notifyflow_backend.dto.ReminderRequestDto;
import com.pramdvaddiraju.notifyflow_backend.dto.ReminderResponseDto;

import java.util.List;

public interface ReminderService {

    ReminderResponseDto createReminder(ReminderRequestDto reminderRequestDto);
    List<ReminderResponseDto> getAllReminders();
    ReminderResponseDto getReminderById(Long id);
    void deleteReminder(Long id);

}
