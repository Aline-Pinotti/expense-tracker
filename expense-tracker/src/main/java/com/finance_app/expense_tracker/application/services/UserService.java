package com.finance_app.expense_tracker.application.services;

import com.finance_app.expense_tracker.application.dtos.RoleDTO;
import com.finance_app.expense_tracker.application.dtos.UserDTO;
import com.finance_app.expense_tracker.application.services.exceptions.DatabaseException;
import com.finance_app.expense_tracker.application.services.exceptions.ResourceNotFoundException;
import com.finance_app.expense_tracker.core.entities.User;
import com.finance_app.expense_tracker.core.repositories.UserRepository;
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
public class UserService {

    // TODO: changing (revoking or adding new) role to user

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public UserDTO findById(UUID id) {
        return new UserDTO(repository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }

    // Unnecessary, findAllPaged will deal with this when passing url parameters
    @Transactional(readOnly = true)
    public List<UserDTO> findByUserName(String username) {
        return repository.findByUsernameLike(username).stream()
                .map(UserDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(Pageable pageable) {
        return repository.findAll(pageable).map(UserDTO::new);
    }

    @Transactional
    public UserDTO insert(UserDTO dto) {
        return new UserDTO(saveEntity(null, dto));
    }

    @Transactional
    public UserDTO update(UUID id, UserDTO dto) {
        try {
            return new UserDTO(saveEntity(id, dto));
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

    private User saveEntity(UUID id, UserDTO dto) {
        User entity = new User();
        if(id != null) entity = repository.getReferenceById(id);
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());

        entity.getRoles().clear();
        //dto.getRoles().forEach(roleDTO -> entity.getRoles().add(new RoleService().findById(roleDTO.getId()).toEntity()));
        entity.getRoles().addAll(dto.getRoles());

        return repository.save(entity);
    }
}
