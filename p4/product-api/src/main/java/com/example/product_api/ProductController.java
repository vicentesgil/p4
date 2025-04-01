package com.example.product_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")  // Esto debería mapear todas las rutas que empiezan con /products
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 1. Crear un nuevo producto
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        // Aquí creamos el producto utilizando la API externa (esto debe ser adaptado)
        String url = "https://dummyjson.com/products/add";  // Simulación de la API de creación
        Product createdProduct = productService.createProduct(product, url);

        // Si el producto se crea correctamente, respondemos con status 201 y el objeto producto creado
        if (createdProduct != null) {
            return ResponseEntity.status(201).body(createdProduct);  // 201 Created
        } else {
            // Si no se pudo crear el producto, respondemos con un error 400 Bad Request
            return ResponseEntity.status(400).build();  // 400 Bad Request
        }
    }

    // 2. Leer todos los productos
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();

        if (products != null && !products.isEmpty()) {
            return ResponseEntity.ok(products);  // 200 OK y los productos en el cuerpo
        } else {
            return ResponseEntity.noContent().build();  // 204 No Content si no hay productos
        }
    }

    // 3. Leer un producto por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);

        if (product != null) {
            return ResponseEntity.ok(product);  // 200 OK con el producto en el cuerpo
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found si no se encuentra el producto
        }
    }

    // 4. Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        Product updated = productService.updateProduct(id, updatedProduct);

        if (updated != null) {
            return ResponseEntity.ok(updated);  // 200 OK con el producto actualizado
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found si no se encuentra el producto para actualizar
        }
    }

    // 5. Borrar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        boolean deleted = productService.deleteProduct(id);

        if (deleted) {
            return ResponseEntity.noContent().build();  // 204 No Content si el producto se eliminó correctamente
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found si no se encuentra el producto para borrar
        }
    }
}
