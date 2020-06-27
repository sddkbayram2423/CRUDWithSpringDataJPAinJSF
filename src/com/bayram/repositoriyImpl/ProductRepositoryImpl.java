package com.bayram.repositoriyImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bayram.entities.Product;
import com.bayram.repositories.ProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Product> findAll() {
		
		try {
			@SuppressWarnings("unchecked")
			List<Product> products=entityManager.createQuery("from Product").getResultList();
			
			return products;
		} catch (Exception e) {
			return null;
		}
		
		
	}

	@Override
	public Product findById(long id) {
		

		try {
			return entityManager.find(Product.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void createProduct(Product product) {
		entityManager.persist(product);
		

	}

	@Override
	public void updateProduct(Product product) {
		entityManager.merge(product);

	}

	@Override
	public void deleteProduct(Product product) {
		entityManager.remove(entityManager.merge(product));

	}
	
//	@Autowired
//	@Qualifier("sessionFactory")
//	private SessionFactory sessionFactory;
//	
//	@Override
//	public List<Product> findAll() {
//		
//		try {
//			@SuppressWarnings("unchecked")
//			List<Product> products=sessionFactory.getCurrentSession().createQuery("from Product").getResultList();
//			
//			return products;
//		} catch (Exception e) {
//			return null;
//		}
//		
//		
//	}
//
//	@Override
//	public Product findById(long id) {
//		
//
//		try {
//			return sessionFactory.getCurrentSession().find(Product.class, id);
//		} catch (Exception e) {
//			return null;
//		}
//	}
//
//	@Override
//	public void createProduct(Product product) {
//		sessionFactory.getCurrentSession().persist(product);
//		
//
//	}
//
//	@Override
//	public void updateProduct(Product product) {
//		sessionFactory.getCurrentSession().merge(product);
//
//	}
//
//	@Override
//	public void deleteProduct(Product product) {
//	sessionFactory.getCurrentSession().remove(sessionFactory.getCurrentSession().merge(product));
//
//	}
//	
	
	
	

}
