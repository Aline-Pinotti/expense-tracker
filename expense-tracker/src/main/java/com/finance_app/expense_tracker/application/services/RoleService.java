package com.finance_app.expense_tracker.application.services;

import com.finance_app.expense_tracker.application.dtos.RoleDTO;
import com.finance_app.expense_tracker.application.services.exceptions.DatabaseException;
import com.finance_app.expense_tracker.application.services.exceptions.ResourceNotFoundException;
import com.finance_app.expense_tracker.core.entities.Role;
import com.finance_app.expense_tracker.core.repositories.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService {
    // TODO: OAuth implementation

    @Autowired
    private RoleRepository repository;

    @Transactional(readOnly = true)
    public RoleDTO findById(UUID id) {
        return new RoleDTO(repository.findById(id).orElseThrow(() -> new RuntimeException("Role not found")));
    }

    @Transactional(readOnly = true)
    public List<RoleDTO> findByAuthority(String authority) {
        return repository.findByAuthorityLike(authority).stream()
                .map(RoleDTO::new)
                .toList();
    } // TODO: Paged?

    @Transactional(readOnly = true)
    public Page<RoleDTO> findAllPaged(Pageable pageable) {
        return repository.findAll(pageable).map(RoleDTO::new);
    }

    @Transactional
    public RoleDTO insert(RoleDTO dto) {
        return new RoleDTO(saveEntity(null, dto));
    }

    @Transactional
    public RoleDTO update(UUID id, RoleDTO dto) {
        try {
            return new RoleDTO(saveEntity(id, dto));
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional
    public void delete(UUID id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private Role saveEntity(UUID id, RoleDTO dto) {
        Role entity = new Role();
        if(id != null) entity = repository.getReferenceById(id);
        entity.setAuthority(dto.getAuthority());
        return repository.save(entity);
    }
}
