package com.finance_app.expense_tracker.application.services;

import com.finance_app.expense_tracker.application.dtos.InstallmentDTO;
import com.finance_app.expense_tracker.application.services.exceptions.DatabaseException;
import com.finance_app.expense_tracker.application.services.exceptions.ResourceNotFoundException;
import com.finance_app.expense_tracker.core.entities.Installment;
import com.finance_app.expense_tracker.core.repositories.InstallmentRepository;
import com.finance_app.expense_tracker.core.repositories.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Service
@RequestMapping("/installments")
public class InstallmentService {

    @Autowired
    private InstallmentRepository repository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional(readOnly = true)
    public InstallmentDTO findById(UUID id) {
        return new InstallmentDTO(repository.findById(id).orElseThrow(() -> new RuntimeException("Installment not found")));
    }

    @Transactional(readOnly = true)
    public Page<InstallmentDTO> findAllPaged(Pageable pageable) {
        return repository.findAll(pageable).map(InstallmentDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<InstallmentDTO> findByTransaction(UUID userId, Pageable pageable) { // InstallmentUserDTO
        return repository.findByTransactionId(userId, pageable).map(InstallmentDTO::new);
    }

    @Transactional
    public InstallmentDTO insert(InstallmentDTO dto) {
        return new InstallmentDTO(saveEntity(null, dto));
    }

    @Transactional
    public InstallmentDTO update(UUID id, InstallmentDTO dto) {
        try {
            return new InstallmentDTO(saveEntity(id, dto));
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

    private Installment saveEntity(UUID id, InstallmentDTO dto) {
        Installment entity = new Installment();

        if(id != null) entity = repository.getReferenceById(id);

        entity.setAmount(dto.getAmount());
        entity.setDescription(dto.getDescription());
        entity.setPaidDay(dto.getPaidDay());
        entity.setNumber(dto.getNumber());
        entity.setDueDate(dto.getDueDate());
        entity.setTransaction(transactionRepository.getReferenceById(dto.getTransaction().getId()));

        return repository.save(entity);
    }
}
