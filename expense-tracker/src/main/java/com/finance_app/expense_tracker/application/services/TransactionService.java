package com.finance_app.expense_tracker.application.services;

import com.finance_app.expense_tracker.application.dtos.TransactionDTO;
import com.finance_app.expense_tracker.application.services.exceptions.DatabaseException;
import com.finance_app.expense_tracker.application.services.exceptions.ResourceNotFoundException;
import com.finance_app.expense_tracker.core.entities.Transaction;
import com.finance_app.expense_tracker.core.repositories.AccountRepository;
import com.finance_app.expense_tracker.core.repositories.CreditCardBillRepository;
import com.finance_app.expense_tracker.core.repositories.TransactionRepository;
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
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CreditCardBillRepository creditCardBillRepository;

    @Transactional(readOnly = true)
    public TransactionDTO findById(UUID id) {
        return new TransactionDTO(repository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found")));
    }

    @Transactional(readOnly = true)
    public Page<TransactionDTO> findAllPaged(Pageable pageable) {
        return repository.findAll(pageable).map(TransactionDTO::new);
    }

//    @Transactional(readOnly = true)
//    public Page<TransactionUserDTO> findByUser(UUID userId, Pageable pageable) {
//        return repository.findByCardId(userId, pageable).map(TransactionDTO::new);
//    }

//    @Transactional(readOnly = true)
//    public Page<TransactionDTO> findByBilllingMonth(YearMonth billingMonth, Pageable pageable) {
//        return repository.findByDueDateBetween(billingMonth.atDay(1), billingMonth.atEndOfMonth(), pageable).map(TransactionDTO::new);
//    }
//
//    @Transactional(readOnly = true)
//    public Page<TransactionDTO> findByDueDate(LocalDate dueDate, Pageable pageable) { //paidDate,
//        return repository.findByDueDate(dueDate, pageable).map(TransactionDTO::new);
//    }
//    @Transactional(readOnly = true)
//    public Page<TransactionBillDTO> findByCreditCard(UUID creditCardId, Pageable pageable) {
//        // TODO: Credit Card Bill transactions //to find the bill of the credit card, give creditcardId and dueDate
//        return repository.findByCardId(creditCardId, pageable).map(TransactionDTO::new);
//    }
//    @Transactional(readOnly = true)
//    public Page<TransactionAccountDTO> findByAccount(UUID accountId, Pageable pageable) {
//        return repository.findByCardId(creditCardId, pageable).map(TransactionDTO::new);
//    }

    @Transactional
    public TransactionDTO insert(TransactionDTO dto) {
        return new TransactionDTO(saveEntity(null, dto));
    }

    @Transactional
    public TransactionDTO update(UUID id, TransactionDTO dto) {
        try {
            return new TransactionDTO(saveEntity(id, dto));
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

    private Transaction saveEntity(UUID id, TransactionDTO dto) {
        Transaction entity = new Transaction();

        if(id != null) entity = repository.getReferenceById(id);

        entity.setDescription(dto.getDescription());
        entity.setAmount(dto.getAmount());
        entity.setDate(dto.getDate());
        entity.setFixed(dto.isFixed());
        entity.setDueDate(dto.getDueDate());
        entity.setPaidDate(dto.getPaidDate());
        entity.setInOut(dto.getInOut());
        entity.setNumberOfInstallments(dto.getNumberOfInstallments());
        entity.setType(dto.getType());
        entity.setMainCategory(dto.getMainCategory());
        entity.setPaymentMethod(dto.getPaymentMethod());

        entity.setUser(userRepository.getReferenceById(dto.getUser().getId()));
        if (dto.getAccountId() != null) entity.setAccount(accountRepository.getReferenceById(dto.getAccountId()));
        if (dto.getCreditCardBillId() != null) entity.setCreditCardBill(creditCardBillRepository.getReferenceById(dto.getCreditCardBillId()));

        return repository.save(entity);
    }
}
