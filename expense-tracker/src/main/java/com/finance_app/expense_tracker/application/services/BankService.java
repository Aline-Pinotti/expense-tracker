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
        return repository.findAll(pageable).map(BankDTO::new);
    }

    @Transactional
    public BankDTO insert(BankDTO dto) {
        return new BankDTO(saveEntity(null, dto));
    }

    @Transactional
    public BankDTO update(UUID id, BankDTO dto) {
        try {
            return new BankDTO(saveEntity(id, dto));
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

    private Bank saveEntity(UUID id, BankDTO dto) {
        Bank entity = new Bank();
        if(id != null) entity = repository.getReferenceById(id);
        entity.setName(dto.getName());
        entity.setNumber(Integer.valueOf(dto.getNumber()));
        entity.setColorLabel(dto.getColorLabel());
        return repository.save(entity);
    }
}
