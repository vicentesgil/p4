document.addEventListener("DOMContentLoaded", async function () {
    const params = new URLSearchParams(window.location.search);
    const productId = params.get("id");

    if (!productId) {
        document.getElementById("detalle-producto").innerHTML = "<p>Producto no encontrado</p>";
        return;
    }

    try {
        // Cambié la URL aquí para apuntar a la nueva API
        const response = await fetch(`http://localhost:8080/products/${productId}`);
        const producto = await response.json();

        document.getElementById("detalle-producto").innerHTML = `
            <div class="producto-detalles">
                <img src="${producto.thumbnail}" alt="${producto.title}" class="producto-img">
                <div class="producto-info">
                    <h2>${producto.title}</h2>
                    <p>${producto.description}</p>
                    <p><strong>Precio:</strong> ${producto.price}€</p>
                    <p><strong>Descuento:</strong> ${producto.discountPercentage}%</p>
                    <p><strong>Valoración:</strong> ${producto.rating}</p>
                    <p><strong>Stock:</strong> ${producto.stock}</p>
                    <p><strong>Marca:</strong> ${producto.brand}</p>
                    <p><strong>Categoría:</strong> ${producto.category}</p>
                </div>
            </div>
            <div class="boton-volver-container">
                <button onclick="window.location.href='pagina2.html'" class="boton-volver">⬅ Volver</button>
            </div>
        `;
    } catch (error) {
        console.error("Error al obtener el producto:", error);
    }
});
