package co.edu.umb.productsorm.business.service;

import co.edu.umb.productsorm.domain.entity.Product;
import co.edu.umb.productsorm.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record ProductService(ProductRepository productRepository) {

  public void create(Product product) {
    if (product.getPrice() <= 0) {
      throw new IllegalStateException("The price value must be upper that zero");
    }
    product.setEnable(Boolean.TRUE);
    productRepository.save(product);
  }

  public void edit(Product product) {
    if (product.getPrice() <= 0) {
      throw new IllegalStateException("The price value must be upper that zero");
    }
    if (product.getId() == null){
      throw new IllegalStateException("The product must be an id");
    }
    productRepository.save(product);
  }

  public List<Product> getProducts(){
    return productRepository.findAll();
  }

  public Product findById(Integer id){
    return productRepository.findById(id)
      .orElseThrow(() -> new IllegalStateException("Products not found"));
  }

  public void delete(Product product){
    product.setEnable(Boolean.FALSE);
    productRepository.save(product);
  }

}
