package org.gestion.logs.service;


import lombok.RequiredArgsConstructor;
import org.gestion.logs.model.EventLog;
import org.gestion.logs.repository.EventLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

    private final EventLogRepository eventLogRepository;

    public EventLog createLog(EventLog eventLog) {
        eventLog.setDate(LocalDateTime.now());
        return eventLogRepository.save(eventLog);
    }

    public List<EventLog> getAllLogs() {
        return eventLogRepository.findAll();
    }

    public List<EventLog> getLogsByModule(String module) {
        return eventLogRepository.findAll().stream().filter(log -> log.getModule().equals(module)).toList();
    }
}
