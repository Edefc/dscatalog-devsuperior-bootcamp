package com.devsuperior.dscatalog.repositories;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.tests.Factory;

@DataJpaTest
public class ProductRepositoryTests {

	@Autowired
	private ProductRepository productRepository;

	private long existingId;
	private long nonExistingId;
	private long countTotalProducts;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 100L;
		countTotalProducts = 25L;
	}

	@Test
	public void saveShouldPersistWhithAutoIncrementWhenIdIsNull() {
		Product product = Factory.createProduct();
		product.setId(null);

		product = productRepository.save(product);

		Assertions.assertNotNull(product.getId());
		Assertions.assertEquals(countTotalProducts + 1, product.getId());

	}

	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {

		productRepository.deleteById(existingId);

		Optional<Product> result = productRepository.findById(existingId);
		Assertions.assertFalse(result.isPresent());

	}

	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIddoesNotExist() {

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			productRepository.deleteById(nonExistingId);
		});
	}

	@Test
	public void findByIdShouldReturnNonEmptyOpyionalWhenIdExists() {

		Optional<Product> result = productRepository.findById(existingId);
		
		Assertions.assertTrue(result.isPresent());

	}

	@Test
	public void findByIdShouldReturnEmptyOpyionalWhenIdDoesNotExists() {
		
			Optional<Product> result = productRepository.findById(nonExistingId);
			Assertions.assertTrue(result.isEmpty());
		
	}

}
