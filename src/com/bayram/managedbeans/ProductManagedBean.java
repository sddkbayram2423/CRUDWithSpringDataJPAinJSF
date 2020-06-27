package com.bayram.managedbeans;

import java.util.List;


import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bayram.entities.Product;
import com.bayram.services.ProductService;

import java.io.Serializable;

@Component
@Named
@RequestScoped
public class ProductManagedBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private ProductService productService;
	
	private List<Product> products;
	
	private Product product;
	
	public ProductManagedBean() {
	}
	
	
	@PostConstruct
	public void init() {
		this.product=new Product();
		this.products=this.productService.findAll();
	}

	public String add() {
		this.product=new Product();
		return "add?faces-redirect=true";
	}
	public String save() {
		this.productService.createProduct(this.product);
		this.products=this.productService.findAll();
		return "index?faces-redirect=true";
	}
	public String edit(long id) {
		
		this.product=productService.findById(id);
		
		return "add?faces-redirect=true";
		
	}
	
	public String update(Product product) {
		
		productService.updateProduct(product);
		this.products=this.productService.findAll();
		return "index?faces-redirect=true";
		
	}
	public String delete(long id) {
		Product deleteProduct=productService.findById(id);
		productService.deleteProduct(deleteProduct);
		this.products=this.productService.findAll();
		return "index?faces-redirect=true";
		
	}
	
	
	public String returnMainPage() {
		return "index";
	}
	
	
	
	
	
	
	// get-set
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	
	
	
	

}
