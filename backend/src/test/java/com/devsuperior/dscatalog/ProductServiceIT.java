package com.devsuperior.dscatalog;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.ProductService;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

@SpringBootTest
public class ProductServiceIT {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	private Long existingId;
	private Long nonExistingId;
	private Long countTotalProducts;

	@BeforeEach
	void setUp() throws Exception {

		existingId = 1L;
		nonExistingId = 1000L;
		countTotalProducts = 25L;
	}

	@Test
	public void deleteShoulDeleteResourceWhenIdExist() {

		productService.delete(existingId);

		Assertions.assertEquals(countTotalProducts - 1, productRepository.count());

	}

	@Test
	public void deleteShouldThrowResourceNotFoundExceptionIdDoesNotExists() {

		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			productService.delete(nonExistingId);
		});

	}

}
