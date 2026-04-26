package com.finance_app.expense_tracker.core.repositories;

import com.finance_app.expense_tracker.core.entities.Bank;
import com.finance_app.expense_tracker.utils.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

@DataJpaTest
public class BankRepositoryTests {

    @Autowired
    private BankRepository repository;

    private UUID existingId;

    @BeforeEach
    void setup() throws Exception{
        existingId = UUID.randomUUID();
    }

    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull(){
        Bank bank = Factory.createBank();
        bank.setId(null);

        bank = repository.save(bank);
        Optional<Bank> result = repository.findById(bank.getId());

        Assertions.assertNotNull(bank.getId());
		Assertions.assertTrue(result.isPresent());
		Assertions.assertSame(result.get(), bank);

    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists(){
        repository.deleteById(existingId);
        Optional<Bank> result = repository.findById(existingId);
        Assertions.assertFalse(result.isPresent());
    }

//    @Test //JPA Delete is now silent - won't throw exception if id does not exist
//    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist(){
//        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
//			repository.deleteById(nonExistingId);
//		});
//    }

}
