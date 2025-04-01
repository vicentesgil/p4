document.addEventListener("DOMContentLoaded", async function () {
    const apiUrl = "http://localhost:8080/products";  // Cambié la URL aquí
    const itemsPerPage = 9;
    let productos = [];
    let productosFiltrados = [];
    let currentPage = 1;

    // Obtener productos desde la nueva API
    async function fetchProductos() {
        try {
            const response = await fetch(apiUrl);
            const data = await response.json();
            productos = data;  // Cambié el acceso aquí, ya que ahora obtienes la lista directamente
            productosFiltrados = productos;
            mostrarProductos();
            generarPaginacion();
        } catch (error) {
            console.error("Error al obtener los productos:", error);
        }
    }

    // Filtrar productos en tiempo real mientras se escribe
    document.getElementById("buscador").addEventListener("input", function (e) {
        const termino = e.target.value.toLowerCase();
        productosFiltrados = productos.filter(producto => producto.title.toLowerCase().includes(termino));
        currentPage = 1;
        mostrarProductos();
        generarPaginacion();
    });

    // Botón de búsqueda que lleva a la página del producto
    document.getElementById("boton-buscar").addEventListener("click", function () {
        const termino = document.getElementById("buscador").value.toLowerCase();
        const productoEncontrado = productos.find(producto => producto.title.toLowerCase() === termino);
        
        if (productoEncontrado) {
            window.location.href = `detalle.html?id=${productoEncontrado.id}`;
        } else {
            alert("Producto no encontrado. Intenta con otro nombre.");
        }
    });

    // Mostrar productos en la página actual
    function mostrarProductos() {
        const contenedor = document.getElementById("productos-container");
        contenedor.innerHTML = "";
        
        const inicio = (currentPage - 1) * itemsPerPage;
        const fin = inicio + itemsPerPage;
        const productosPagina = productosFiltrados.slice(inicio, fin);
        
        productosPagina.forEach(producto => {
            const item = document.createElement("div");
            item.classList.add("producto");
            item.innerHTML = `
                <div class="producto-card">
                    <a href="detalle.html?id=${producto.id}" class="producto-link">
                        <img src="${producto.thumbnail}" alt="${producto.title}" class="producto-img" />
                        <h3>${producto.title}</h3>
                    </a>
                </div>
            `;
            contenedor.appendChild(item);
        });
    }

    // Generar botones de paginación
    function generarPaginacion() {
        const paginacion = document.getElementById("paginacion");
        paginacion.innerHTML = "";
        const totalPages = Math.ceil(productosFiltrados.length / itemsPerPage);
        
        for (let i = 1; i <= totalPages; i++) {
            const btn = document.createElement("button");
            btn.textContent = i;
            btn.classList.add("pagina-btn");
            if (i === currentPage) btn.classList.add("activo");
            btn.addEventListener("click", () => {
                currentPage = i;
                mostrarProductos();
                generarPaginacion();
            });
            paginacion.appendChild(btn);
        }
    }

    // Iniciar la carga de productos
    fetchProductos();
});
