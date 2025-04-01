document.addEventListener("DOMContentLoaded", async function () {
    const apiUrl = "https://dummyjson.com/products?limit=100";
    const tablaProductos = document.getElementById("tabla-productos");

    async function fetchProductos() {
        try {
            const response = await fetch(apiUrl);
            const data = await response.json();
            mostrarProductos(data.products);
        } catch (error) {
            console.error("Error al obtener los productos:", error);
        }
    }

    function mostrarProductos(productos) {
        productos.forEach(producto => {
            const fila = document.createElement("tr");
            fila.innerHTML = `
                <td>${producto.title}</td>
                <td>$${producto.price}</td>
                <td>${producto.stock > 0 ? "En stock" : "Agotado"}</td>
            `;
            tablaProductos.appendChild(fila);
        });
    }

    fetchProductos();
});
