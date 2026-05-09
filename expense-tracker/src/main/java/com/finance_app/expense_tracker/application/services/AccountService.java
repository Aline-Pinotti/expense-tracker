package com.finance_app.expense_tracker.application.services;

import com.finance_app.expense_tracker.application.dtos.AccountDTO;
import com.finance_app.expense_tracker.application.services.exceptions.DatabaseException;
import com.finance_app.expense_tracker.application.services.exceptions.ResourceNotFoundException;
import com.finance_app.expense_tracker.core.entities.Account;
import com.finance_app.expense_tracker.core.repositories.AccountRepository;
import com.finance_app.expense_tracker.core.repositories.BankRepository;
import com.finance_app.expense_tracker.core.repositories.UserRepository;
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
public class AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public AccountDTO findById(UUID id) {
        return new AccountDTO(repository.findById(id).orElseThrow(() -> new RuntimeException("Account not found")));
    }

    @Transactional(readOnly = true)
    public Page<AccountDTO> findAllPaged(Pageable pageable) {
        return repository.findAll(pageable).map(AccountDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<AccountDTO> findAllByUser(UUID userId, Pageable pageable) {
        return repository.findByUserId(userId, pageable).map(AccountDTO::new);
    }

    @Transactional
    public AccountDTO insert(AccountDTO dto) {
        return new AccountDTO(saveEntity(null, dto));
    }

    @Transactional
    public AccountDTO update(UUID id, AccountDTO dto) {
        try {
            return new AccountDTO(saveEntity(id, dto));
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

    private Account saveEntity(UUID id, AccountDTO dto) {
        Account entity = new Account();

        if(id != null){
            entity = repository.getReferenceById(id);
            if (!entity.getUser().getId().equals(dto.getUser().getId())) {
                throw new RuntimeException("Usuário não pode ser alterado! Delete a notificação em vez disso.");
            }
        }
        entity.setName(dto.getName());
        entity.setAgencyNo(dto.getAgencyNo());
        entity.setCode(dto.getCode());
        entity.setBalance(dto.getBalance());
        entity.setLimit(dto.getLimit());
        entity.setSpecialLimit(dto.getSpecialLimit());
        entity.setLimitBudget(dto.getLimitBudget());
        entity.setCash(dto.getCash());

        entity.setBank(bankRepository.getReferenceById(dto.getBank().getId()));
        entity.setUser(userRepository.getReferenceById(dto.getUser().getId()));

        return repository.save(entity);
    }
}
