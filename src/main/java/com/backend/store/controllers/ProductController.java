import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.store.models.Product;
import com.backend.store.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product dto) {
        Product product = productService.createProduct(dto, null);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String productId, @RequestBody Product dto) {
        Product product = productService.updateProduct(productId, dto, null);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") String productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/newest")
    public ResponseEntity<List<Product>> getNewestProducts() {
        List<Product> products = productService.getNewestProduct();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProductsPagination(@RequestParam("limit") int limit, @RequestParam("page") int page) {
        List<Product> products = productService.getProductsPagination(limit, page);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/count")
    public ResponseEntity<Integer> countProductsInCategory(@PathVariable("categoryId") String categoryId) {
        int count = productService.countProductsInCategory(categoryId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/max-price")
    public ResponseEntity<Double> findMaxPrice(@PathVariable("categoryId") String categoryId) {
        double maxPrice = productService.findMaxPrice(categoryId);
        return new ResponseEntity<>(maxPrice, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterProducts(
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "categoryId", required = false) String categoryId,
            @RequestParam(value = "searchText", required = false) String searchText,
            @RequestParam(value = "priceFrom", defaultValue = "0") double priceFrom,
            @RequestParam(value = "priceTo", defaultValue = "0") double priceTo,
            @RequestParam(value = "sort", defaultValue = "asc") String sort
    ) {
        List<Product> filteredProducts = productService.filterProduct(limit, page, categoryId, searchText, priceFrom, priceTo, sort);
        return new ResponseEntity<>(filteredProducts, HttpStatus.OK);
    }

    @GetMapping("/cart/{productId}")
    public ResponseEntity<Product> getProductForCart(@PathVariable("productId") String productId) {
        Product product = productService.getProductForCart(productId);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
   
