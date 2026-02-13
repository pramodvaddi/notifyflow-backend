package com.pramdvaddiraju.notifyflow_backend.service;

import com.pramdvaddiraju.notifyflow_backend.dto.ReminderRequestDto;
import com.pramdvaddiraju.notifyflow_backend.dto.ReminderResponseDto;
import com.pramdvaddiraju.notifyflow_backend.entity.Reminder;
import com.pramdvaddiraju.notifyflow_backend.exception.ResourceNotFoundException;
import com.pramdvaddiraju.notifyflow_backend.repository.ReminderRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReminderServiceImpl implements ReminderService{

    private ModelMapper modelMapper;
    private ReminderRepository reminderRepository;
    private static final Logger log = LoggerFactory.getLogger(ReminderServiceImpl.class);

    public ReminderServiceImpl(ModelMapper modelMapper, ReminderRepository reminderRepository){
        this.reminderRepository = reminderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReminderResponseDto createReminder(ReminderRequestDto reminderRequestDto) {
        log.debug("Creating reminder for email: {}", reminderRequestDto.getEmail());
        Reminder reminder = modelMapper.map(reminderRequestDto, Reminder.class);
        reminder.setStatus("PENDING");
        Reminder createReminder = reminderRepository.save(reminder);
        log.info("Reminder saved successfully with email: {}", reminderRequestDto.getEmail());
        return modelMapper.map(createReminder, ReminderResponseDto.class);
    }

    @Override
    public List<ReminderResponseDto> getAllReminders() {
        List<Reminder> allReminders = reminderRepository.findAll();
        List<ReminderResponseDto> responseList = new ArrayList<>();

        for(Reminder reminder: allReminders){
            ReminderResponseDto dto = modelMapper.map(reminder, ReminderResponseDto.class);
            responseList.add(dto);
        }
        return responseList;
    }

    @Override
    public ReminderResponseDto getReminderById(Long id) {
        Reminder getById = reminderRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Resource Not found with id: "+ id)
        );

        return modelMapper.map(getById, ReminderResponseDto.class);
    }

    @Override
    public void deleteReminder(Long id) {
        Reminder reminder = reminderRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Not found with id: " + id)
        );
        reminderRepository.delete(reminder);
    }
}
