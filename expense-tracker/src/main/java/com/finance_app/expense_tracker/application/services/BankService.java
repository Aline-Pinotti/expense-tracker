package com.finance_app.expense_tracker.application.services;

import com.finance_app.expense_tracker.application.dtos.BankDTO;
import com.finance_app.expense_tracker.application.services.exceptions.DatabaseException;
import com.finance_app.expense_tracker.application.services.exceptions.ResourceNotFoundException;
import com.finance_app.expense_tracker.core.entities.Bank;
import com.finance_app.expense_tracker.core.repositories.BankRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class BankService {

    @Autowired
    private BankRepository repository;


    // TODO: padronizar mensagens

    @Transactional(readOnly = true)
    public BankDTO findById(UUID id) {
        return new BankDTO(repository.findById(id).orElseThrow(() -> new RuntimeException("Bank not found")));
    }

    @Transactional(readOnly = true)
    public Page<BankDTO> findAllPaged(Pageable pageable) {
        //repository retorna número como integer
        //Bank entity = new Bank();
        //converter para string + máscara antes de devolver
        return repository.findAll(pageable).map(BankDTO::new); //como editar?
    }

    @Transactional
    public BankDTO insert(BankDTO dto) {
        Bank entity = new Bank();

        entity.setName(dto.getName());
        entity.setNumber(Integer.valueOf(dto.getNumber()));
        entity.setColorLabel(dto.getColorLabel());
        entity.setCreatedAt(java.time.Instant.now());
        entity.setUpdatedAt(entity.getCreatedAt());
        entity = repository.save(entity);

        return new BankDTO(entity);
    }

    @Transactional
    public BankDTO update(UUID id, BankDTO dto) {
        try {
            Bank entity = repository.getReferenceById(id);
            entity.setName(dto.getName());
            entity.setNumber(Integer.valueOf(dto.getNumber()));
            entity.setColorLabel(dto.getColorLabel());
            entity.setUpdatedAt(java.time.Instant.now());
            entity = repository.save(entity);
            return new BankDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

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
}
