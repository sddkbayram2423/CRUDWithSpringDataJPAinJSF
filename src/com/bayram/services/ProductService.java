package com.bayram.services;

import java.util.List;


import com.bayram.entities.Product;


public interface ProductService {
	
	public List<Product> findAll();

	public Product findById(long id);

	public void createProduct(Product product);

	public void updateProduct(Product product);

	public void deleteProduct(Product product);

}
