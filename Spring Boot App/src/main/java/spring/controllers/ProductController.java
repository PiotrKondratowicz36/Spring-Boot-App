package spring.controllers;

import spring.entities.Product;
import spring.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

/**
 * Product controller.
 */
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * List all products.
     *
     */
    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Product> list(Model model) {
        return productService.listAllProducts();
    }

    // Only for redirect!
    @ApiIgnore
    @RequestMapping(value = "/products", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Product> redirect(Model model) {
        return productService.listAllProducts();
    }

    /**
     * View a specific product by its id.
     *
     * @return
     */
    @GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getByPublicId(@PathVariable("id") Integer publicId) {
        return productService.getProductById(publicId).orElseGet(null);
    }

    /**
     * View a specific product by its id.
     *
     * @return
     */
    @GetMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getByParamPublicId(@RequestParam("id") Integer publicId) {
        return productService.getProductById(publicId).orElseGet(null);
    }

    /**
     * Save product to database.
     *
     */
    @PostMapping(value = "/product")
    public ResponseEntity<Product> create(@RequestBody @NonNull @Valid
                                                      Product product) {
        product.setProductId(UUID.randomUUID().toString());
        productService.saveProduct(product);
        return ResponseEntity.ok().body(product);
    }


    /**
     * Edit product in database.
     *
     */
    @PutMapping(value = "/product")
    public ResponseEntity<Void> edit(@RequestBody Product product) {
        if(!productService.checkIfExist(product.getId()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            productService.saveProduct(product);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    /**
     * Delete product by its id.
     *
     */
    @DeleteMapping(value = "/product/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity deleteBadRequest(@PathVariable Integer id) {
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }


    @GetMapping(value = "/products/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Product> list(@PathVariable("page") Integer pageNr,@RequestParam("size") Optional<Integer> howManyOnPage) {
        return productService.listAllProductsPaging(pageNr, howManyOnPage.orElse(2));
    }
}
