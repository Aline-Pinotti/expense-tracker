package com.finance_app.expense_tracker.presentation.controllers;

import com.finance_app.expense_tracker.application.dtos.NotificationDTO;
import com.finance_app.expense_tracker.application.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<NotificationDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

//    TODO: Find notifications startinf from a seted date
    @GetMapping
    public ResponseEntity<Page<NotificationDTO>> findAll(@RequestParam(required = false) UUID userId,
                                                         @RequestParam(required = false, defaultValue = "false") Boolean isRead,
                                                         Pageable pageable) {
        if (userId != null) {
            if (isRead) {
                return ResponseEntity.ok().body(service.findUnreadByUser(userId, pageable));
            }
            return ResponseEntity.ok().body(service.findAllByUser(userId, pageable));
        }
        return ResponseEntity.ok().body(service.findAllPaged(pageable));
    }


    @PostMapping
    public ResponseEntity<NotificationDTO> insert(@RequestBody NotificationDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<NotificationDTO> update(@PathVariable UUID id, @RequestBody NotificationDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
