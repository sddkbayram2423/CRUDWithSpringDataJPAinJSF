package com.bayram.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bayram.entities.Product;
import com.bayram.repositories.ProductRepository;
import com.bayram.services.ProductService;


@Transactional
@Service
public class ProductServiceImpl implements ProductService {
	
	
	private ProductRepository repository;

	public ProductServiceImpl(ProductRepository repository) {
		this.repository =  repository;
	}

	@Override
	public List<Product> findAll() {
		return repository.findAll();
	}

	@Override
	public Product findById(long id) {
		return repository.findById(id);
	}

	@Override
	public void createProduct(Product product) {
			repository.createProduct(product);
	}

	@Override
	public void updateProduct(Product product) {
		repository.updateProduct(product);
	}

	@Override
	public void deleteProduct(Product product) {
		repository.deleteProduct(product);
	}

	
	

}
