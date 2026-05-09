package com.finance_app.expense_tracker.application.services;

import com.finance_app.expense_tracker.application.dtos.NotificationDTO;
import com.finance_app.expense_tracker.application.services.exceptions.DatabaseException;
import com.finance_app.expense_tracker.application.services.exceptions.ResourceNotFoundException;
import com.finance_app.expense_tracker.core.entities.Notification;
import com.finance_app.expense_tracker.core.repositories.NotificationRepository;
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
public class NotificationService {
    
    @Autowired
    private NotificationRepository repository;

    @Autowired
    private UserRepository userRepository;

    // TODO: padronizar mensagens

    @Transactional(readOnly = true)
    public NotificationDTO findById(UUID id) {
        return new NotificationDTO(repository.findById(id).orElseThrow(() -> new RuntimeException("Notification not found")));
    }

    @Transactional(readOnly = true)
    public Page<NotificationDTO> findAllPaged(Pageable pageable) {
        return repository.findAll(pageable).map(NotificationDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<NotificationDTO> findAllByUser(UUID userId, Pageable pageable) {
        return repository.findByUserId(userId, pageable).map(NotificationDTO::new);
    }

    @Transactional(readOnly = true)
    public Page<NotificationDTO> findUnreadByUser(UUID userId, Pageable pageable) {
        return repository.findByUserIdAndReadAtIsNull(userId, pageable).map(NotificationDTO::new);
    }

    @Transactional
    public NotificationDTO insert(NotificationDTO dto) {
        return new NotificationDTO(saveEntity(null, dto));
    }

    @Transactional
    public NotificationDTO update(UUID id, NotificationDTO dto) {
        try {
            return new NotificationDTO(saveEntity(id, dto));
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

    private Notification saveEntity(UUID id, NotificationDTO dto) {
        Notification entity = new Notification();

        if(id != null){
            entity = repository.getReferenceById(id);
            if (!entity.getUser().getId().equals(dto.getUser().getId())) {
                throw new RuntimeException("Usuário não pode ser alterado! Delete a notificação em vez disso.");
            }
        }

        entity.setSubject(dto.getSubject());
        entity.setMessage(dto.getMessage());
        entity.setDateTime(dto.getDateTime());
        entity.setReadAt(dto.getReadAt());
        entity.setUser(userRepository.getReferenceById(dto.getUser().getId()));

        return repository.save(entity);
    }
}
