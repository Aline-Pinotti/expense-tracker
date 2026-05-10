package com.finance_app.expense_tracker.presentation.controllers;

import com.finance_app.expense_tracker.application.dtos.CreditCardBillDTO;
import com.finance_app.expense_tracker.application.services.CreditCardBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

@Controller
@RequestMapping("/credit-card-bills")
public class CreditCardBillController {

    @Autowired
    private CreditCardBillService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CreditCardBillDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    //    TODO: Find CreditCardBills starting from a seted date
    @GetMapping
    public ResponseEntity<Page<CreditCardBillDTO>> findAll(@RequestParam(required = false) LocalDate dueDate,
                                                           @RequestParam(required = false) YearMonth billingMonth,
                                                           @RequestParam(required = false) UUID creditCardId,
                                                           Pageable pageable) {
        if (creditCardId != null) return ResponseEntity.ok().body(service.findByCreditCard(creditCardId, pageable));
        if (billingMonth != null) return ResponseEntity.ok().body(service.findByBilllingMonth(billingMonth, pageable));
        if (dueDate != null) return ResponseEntity.ok().body(service.findByDueDate(dueDate, pageable));
        return ResponseEntity.ok().body(service.findAllPaged(pageable));
    }


    @PostMapping
    public ResponseEntity<CreditCardBillDTO> insert(@RequestBody CreditCardBillDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CreditCardBillDTO> update(@PathVariable UUID id, @RequestBody CreditCardBillDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
