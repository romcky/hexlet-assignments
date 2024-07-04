package exercise.controller;

import exercise.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

import exercise.repository.ProductRepository;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @GetMapping(path = "")
    public List<ProductDTO> index() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(p -> productMapper.map(p)).toList();
    }

    @GetMapping(path = "/{id}")
    public ProductDTO show(@PathVariable long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product with id " + id + " not found")
        );
        return productMapper.map(product);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductCreateDTO dto) {
        Product product = productMapper.map(dto);
        return productMapper.map(productRepository.save(product));
    }

    @PutMapping(path = "/{id}")
    public ProductDTO update(@PathVariable long id, @RequestBody ProductUpdateDTO dto) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product with id " + id + " not found")
        );
        productMapper.update(dto, product);
        return productMapper.map(productRepository.save(product));
    }
    // END
}
