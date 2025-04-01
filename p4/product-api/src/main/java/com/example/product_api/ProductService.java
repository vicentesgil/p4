package com.example.product_api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService {

    private final RestTemplate restTemplate;

    @Autowired
    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Obtener todos los productos de la API externa
    public List<Product> getAllProducts() {
        String url = "https://dummyjson.com/products";

        // Deserializamos la respuesta en un objeto ProductResponse
        ProductResponse productResponse = restTemplate.getForObject(url, ProductResponse.class);

        // Si el objeto ProductResponse no es null, devolvemos la lista de productos
        return (productResponse != null) ? productResponse.getProducts() : null;
    }

    // Obtener un producto por su ID
    public Product getProductById(int id) {
        String url = "https://dummyjson.com/products/" + id;
        return restTemplate.getForObject(url, Product.class);
    }

    // Crear un producto (esto es una simulación, ya que la API de DummyJSON no permite crear productos)
    public Product createProduct(Product product, String url) {
        // Simulación de creación
        return restTemplate.postForObject(url, product, Product.class);
    }

    // Actualizar un producto (esto es una simulación, ya que la API de DummyJSON no soporta PUT para actualizaciones)
    public Product updateProduct(int id, Product updatedProduct) {
        String url = "https://dummyjson.com/products/" + id;
        restTemplate.put(url, updatedProduct);
        return updatedProduct;  // Simulando la actualización
    }

    // Eliminar un producto (esto es una simulación, ya que la API de DummyJSON no permite eliminar productos)
    public boolean deleteProduct(int id) {
        String url = "https://dummyjson.com/products/" + id;
        restTemplate.delete(url);
        return true;  // Simulando la eliminación
    }
}
