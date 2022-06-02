package com.devsuperior.dscatalog.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.devsuperior.dscatalog.entities.Product;

@DataJpaTest
public class ProductRepositoryTests {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		
		long existingId = 1L;
		productRepository.deleteById(existingId);

		Optional<Product> result = productRepository.findById(existingId);
		Assertions.assertFalse(result.isPresent());
			
	}

	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIddoesNotExist() {
		
		long nonExistingId = 100L;
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			productRepository.deleteById(nonExistingId);
		});
	}
	
}
