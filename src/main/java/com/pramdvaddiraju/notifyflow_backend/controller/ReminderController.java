package com.pramdvaddiraju.notifyflow_backend.controller;

import com.pramdvaddiraju.notifyflow_backend.dto.ReminderRequestDto;
import com.pramdvaddiraju.notifyflow_backend.dto.ReminderResponseDto;
import com.pramdvaddiraju.notifyflow_backend.service.ReminderService;
import com.pramdvaddiraju.notifyflow_backend.service.ReminderServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

    private ReminderService reminderService;
    private static final Logger log = LoggerFactory.getLogger(ReminderController.class);

    public ReminderController(ReminderService reminderService){
        this.reminderService = reminderService;
    }

    @PostMapping
    ResponseEntity<ReminderResponseDto> createReminder(@Valid @RequestBody ReminderRequestDto reminderRequestDto){
        log.info("Created reminder successfully with email: {}", reminderRequestDto.getEmail());
        return ResponseEntity.status(201).body(reminderService.createReminder(reminderRequestDto));
    }

    @GetMapping
    ResponseEntity<List<ReminderResponseDto>> getAllReminders(){
        return ResponseEntity.ok().body(reminderService.getAllReminders());
    }

    @GetMapping("/{id}")
    ResponseEntity<ReminderResponseDto> getReminderById(@PathVariable long id){
        return ResponseEntity.ok().body(reminderService.getReminderById(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable long id){
        reminderService.deleteReminder(id);
        return ResponseEntity.noContent().build();
    }



}
