package com.finance_app.expense_tracker.presentation.controllers;

import com.finance_app.expense_tracker.application.dtos.InstallmentDTO;
import com.finance_app.expense_tracker.application.services.InstallmentService;
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
@RequestMapping("/installments")
public class InstallmentController {
    
    @Autowired
    private InstallmentService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<InstallmentDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    //    TODO: Find Installments starting from a seted date
    @GetMapping
    public ResponseEntity<Page<InstallmentDTO>> findAll(@RequestParam(required = false) UUID transactionId,
                                                        Pageable pageable) {
        if (transactionId != null) return ResponseEntity.ok().body(service.findByTransaction(transactionId, pageable));
        return ResponseEntity.ok().body(service.findAllPaged(pageable));
    }


    @PostMapping
    public ResponseEntity<InstallmentDTO> insert(@RequestBody InstallmentDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<InstallmentDTO> update(@PathVariable UUID id, @RequestBody InstallmentDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
