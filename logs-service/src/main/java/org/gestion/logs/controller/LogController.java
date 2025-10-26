package org.gestion.logs.controller;


import lombok.RequiredArgsConstructor;
import org.gestion.logs.model.EventLog;
import org.gestion.logs.service.LogService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @PostMapping
    @PreAuthorize("hasAuthority('access.logs')")
    public ResponseEntity<EventLog> createLog(@RequestBody EventLog eventLog) {
        return ResponseEntity.ok(logService.createLog(eventLog));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('access.logs')")
    public ResponseEntity<List<EventLog>> getAllLogs(@RequestParam(required = false) String module) {
        if (module != null) {
            return ResponseEntity.ok(logService.getLogsByModule(module));
        }
        return ResponseEntity.ok(logService.getAllLogs());
    }
}
