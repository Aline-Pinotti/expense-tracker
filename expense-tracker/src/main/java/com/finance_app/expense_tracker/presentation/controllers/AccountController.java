package com.finance_app.expense_tracker.presentation.controllers;

import com.finance_app.expense_tracker.application.dtos.AccountDTO;
import com.finance_app.expense_tracker.application.services.AccountService;
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
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AccountDTO>> findAll(@RequestParam(required = false) UUID userId, Pageable pageable) {
        if (userId != null) {
            return ResponseEntity.ok().body(service.findAllByUser(userId, pageable));
        }
        return ResponseEntity.ok().body(service.findAllPaged(pageable));
    }


    @PostMapping
    public ResponseEntity<AccountDTO> insert(@RequestBody AccountDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AccountDTO> update(@PathVariable UUID id, @RequestBody AccountDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
