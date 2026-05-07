package com.finance_app.expense_tracker.presentation.controllers;

import com.finance_app.expense_tracker.application.dtos.RoleDTO;
import com.finance_app.expense_tracker.application.services.RoleService;
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
@RequestMapping("/roles")
public class RoleController {
    
    @Autowired
    private RoleService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<RoleDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<RoleDTO>> findRoles(@RequestParam(required = false) String authority, Pageable pageable) {
        if (authority != null) {return ResponseEntity.ok().body(service.findByAuthority(authority, pageable));}
        return ResponseEntity.ok().body(service.findAllPaged(pageable));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> insert(@RequestBody RoleDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RoleDTO> update(@PathVariable UUID id, @RequestBody RoleDTO dto) {
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
