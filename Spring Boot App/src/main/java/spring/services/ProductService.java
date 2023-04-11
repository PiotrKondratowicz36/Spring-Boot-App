package spring.services;

import spring.entities.Product;

import java.util.Optional;

public interface ProductService {

    Iterable<Product> listAllProducts();

    Optional<Product> getProductById(Integer id);

    Product saveProduct(Product product);

    void deleteProduct(Integer id);

    Boolean checkIfExist(Integer id);

    Iterable<Product> listAllProductsPaging(Integer pageNr, Integer howManyOnPage);
}
