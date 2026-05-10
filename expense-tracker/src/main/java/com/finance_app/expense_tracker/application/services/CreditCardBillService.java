package com.finance_app.expense_tracker.application.services;

import com.finance_app.expense_tracker.application.dtos.CreditCardBillDTO;
import com.finance_app.expense_tracker.application.services.exceptions.DatabaseException;
import com.finance_app.expense_tracker.application.services.exceptions.ResourceNotFoundException;
import com.finance_app.expense_tracker.core.entities.CreditCardBill;
import com.finance_app.expense_tracker.core.repositories.CreditCardBillRepository;
import com.finance_app.expense_tracker.core.repositories.CreditCardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.UUID;

@Service
public class CreditCardBillService {

    @Autowired
    private CreditCardBillRepository repository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    // Page<Transaction> findTransactionsByBillingMonth(@Param("year") int year, @Param("month") int month, Pageable pageable);
    // TODO: padronizar mensagens

    @Transactional(readOnly = true)
    public CreditCardBillDTO findById(UUID id) {
        return new CreditCardBillDTO(repository.findById(id).orElseThrow(() -> new RuntimeException("CreditCardBill not found")));
    }

    @Transactional(readOnly = true)
    public Page<CreditCardBillDTO> findAllPaged(Pageable pageable) {
        return repository.findAll(pageable).map(CreditCardBillDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<CreditCardBillDTO> findByBilllingMonth(YearMonth billingMonth, Pageable pageable) {
       return repository.findByDueDateBetween(billingMonth.atDay(1), billingMonth.atEndOfMonth(), pageable).map(CreditCardBillDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<CreditCardBillDTO> findByDueDate(LocalDate dueDate, Pageable pageable) {
        return repository.findByDueDate(dueDate, pageable).map(CreditCardBillDTO::new);
    }
    @Transactional(readOnly = true)
    public Page<CreditCardBillDTO> findByCreditCard(UUID creditCardId, Pageable pageable) {
        return repository.findByCardId(creditCardId, pageable).map(CreditCardBillDTO::new);
    }

    @Transactional
    public CreditCardBillDTO insert(CreditCardBillDTO dto) {
        return new CreditCardBillDTO(saveEntity(null, dto));
    }

    @Transactional
    public CreditCardBillDTO update(UUID id, CreditCardBillDTO dto) {
        try {
            return new CreditCardBillDTO(saveEntity(id, dto));
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

    private CreditCardBill saveEntity(UUID id, CreditCardBillDTO dto) {
        CreditCardBill entity = new CreditCardBill();

        if(id != null) entity = repository.getReferenceById(id);
        entity.setDueDate(dto.getDueDate());
        entity.setClosingDate(dto.getClosingDate());
        entity.setAmount(dto.getAmount());
        entity.setPaidDate(dto.getPaidDate());
        entity.setAmountPayed(dto.getAmountPayed());
        entity.setStatus(dto.getStatus());
        entity.setCard(creditCardRepository.getReferenceById(dto.getCard().getId()));

        return repository.save(entity);
    }

}
