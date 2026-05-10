package com.finance_app.expense_tracker.presentation.controllers;

import com.finance_app.expense_tracker.application.dtos.CreditCardDTO;
import com.finance_app.expense_tracker.application.services.CreditCardService;
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
@RequestMapping("/credit-cards")
public class CreditCardController {
    @Autowired
    private CreditCardService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CreditCardDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<CreditCardDTO>> findAll(@RequestParam(required = false) UUID userId,
                                                       @RequestParam(required = false) UUID accountId,
                                                       Pageable pageable) {
        if (accountId != null) {
            return ResponseEntity.ok().body(service.findByAccountId(accountId, pageable));
        }
        if (userId != null) {
            return ResponseEntity.ok().body(service.findAllByUser(userId, pageable));
        }

        return ResponseEntity.ok().body(service.findAllPaged(pageable));
    }


    @PostMapping
    public ResponseEntity<CreditCardDTO> insert(@RequestBody CreditCardDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CreditCardDTO> update(@PathVariable UUID id, @RequestBody CreditCardDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
