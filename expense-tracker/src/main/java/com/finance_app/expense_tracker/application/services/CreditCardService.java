package com.finance_app.expense_tracker.application.services;

import com.finance_app.expense_tracker.application.dtos.CreditCardDTO;
import com.finance_app.expense_tracker.application.services.exceptions.DatabaseException;
import com.finance_app.expense_tracker.application.services.exceptions.ResourceNotFoundException;
import com.finance_app.expense_tracker.core.entities.CreditCard;
import com.finance_app.expense_tracker.core.repositories.AccountRepository;
import com.finance_app.expense_tracker.core.repositories.CreditCardRepository;
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
public class CreditCardService {

    @Autowired
    private CreditCardRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public CreditCardDTO findById(UUID id) {
        return new CreditCardDTO(repository.findById(id).orElseThrow(() -> new RuntimeException("CreditCard not found")));
    }

    @Transactional(readOnly = true)
    public Page<CreditCardDTO> findAllPaged(Pageable pageable) {
        return repository.findAll(pageable).map(CreditCardDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<CreditCardDTO> findByAccountId(UUID accountId, Pageable pageable) {
        return repository.findByAccountId(accountId, pageable).map(CreditCardDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<CreditCardDTO> findAllByUser(UUID userId, Pageable pageable) {
        return repository.findByAccountUserId(userId, pageable).map(CreditCardDTO::new);
    }

    @Transactional
    public CreditCardDTO insert(CreditCardDTO dto) {
        return new CreditCardDTO(saveEntity(null, dto));
    }

    @Transactional
    public CreditCardDTO update(UUID id, CreditCardDTO dto) {
        try {
            return new CreditCardDTO(saveEntity(id, dto));
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional
    public void delete(UUID id) { // should it be allowed to delete? Better only change a status to canceled (or better yet only the log?)/ let it available, deciding in service layer
        try { // TODO: if delete is allowed, should it be a soft delete? (status canceled or something like that) or just log the deletion and keep the record available for historical purposes?
            repository.deleteById(id); // TODO: property STATUS
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private CreditCard saveEntity(UUID id, CreditCardDTO dto) {
        CreditCard entity = new CreditCard();

        if(id != null) entity = repository.getReferenceById(id);

        entity.setName(dto.getName());
        entity.setNumber(dto.getNumber());
        entity.setLimit(dto.getLimit());
        entity.setClosingDay(dto.getClosingDay());
        entity.setDueDay(dto.getDueDay());
        entity.setExpirationDate(dto.getExpirationDate());
        entity.setInternational(dto.isInternational());
        entity.setAccount(accountRepository.getReferenceById(dto.getAccount().getId()));

        return repository.save(entity);
    }
}
