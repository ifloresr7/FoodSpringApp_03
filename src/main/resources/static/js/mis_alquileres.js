document.addEventListener("DOMContentLoaded", async function () {
    const createButton = document.querySelector(".createButton");
    const closeButton = document.querySelector(".closeButtonModal");
    const crearAlquilerDialog = document.getElementById("crearAlquilerDialog");
    const formCrearAlquiler = document.getElementById("formCrearAlquiler");
    const tablaAlquileres = document.getElementById("tablaAlquileres").querySelector("tbody");

    // Cargar alquileres automáticamente al cargar la página
    try {
        const response = await fetch('/api/alquileres/mis-alquileres'); // Endpoint para obtener alquileres
        if (response.ok) {
            const alquileres = await response.json();
            llenarTablaAlquileres(alquileres);
        } else {
            alert("Error al obtener los alquileres.");
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Hubo un problema al cargar los alquileres.");
    }

    // Mostrar modal para crear alquiler al hacer clic en el botón
    createButton.addEventListener("click", () => {
        crearAlquilerDialog.showModal();
    });

    // Crear alquiler
    formCrearAlquiler.addEventListener("submit", async (event) => {
        event.preventDefault();

        const alquilerData = {
            clienteId: document.getElementById("clienteId").value,
            vehiculoId: document.getElementById("vehiculoId").value,
            fechaInicio: document.getElementById("fechaInicio").value,
            fechaFin: document.getElementById("fechaFin").value,
            precio: document.getElementById("precio").value
        };

        try {
            const response = await fetch('/api/alquileres/crear-alquiler', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(alquilerData)
            });

            if (response.ok) {
                alert("Alquiler creado exitosamente");
                crearAlquilerDialog.close();
                formCrearAlquiler.reset();

                // Recargar alquileres
                const nuevoAlquiler = await response.json();
                llenarTablaAlquileres([nuevoAlquiler]);
            } else {
                alert("Error al crear el alquiler.");
            }
        } catch (error) {
            console.error("Error:", error);
            alert("Hubo un problema al comunicarse con el servidor.");
        }
    });

    // Función para llenar la tabla con los alquileres
    function llenarTablaAlquileres(alquileres) {
        tablaAlquileres.innerHTML = ""; // Limpiar tabla
        alquileres.forEach(alquiler => {
            const fila = document.createElement("tr");
            fila.innerHTML = `
                <td>${alquiler.id}</td>
                <td>${alquiler.clienteId}</td>
                <td>${alquiler.vehiculoId}</td>
                <td>${alquiler.fechaInicio}</td>
                <td>${alquiler.fechaFin}</td>
                <td>${alquiler.precio}</td>
            `;
            tablaAlquileres.appendChild(fila);
        });
    }

    closeButton.addEventListener("click", () => {
        crearAlquilerDialog.close();
    });

    document.getElementById('vehiculoId').addEventListener('change', function () {
        const selectedOption = this.options[this.selectedIndex]; // Obtener opción seleccionada
        const precio = selectedOption.getAttribute('data-price'); // Leer atributo data-price
        document.getElementById('precio').textContent = precio ? `${precio} €` : 'Precio no disponible'; // Actualizar precio
    });

    document.getElementById("formCrearAlquiler").addEventListener("submit", function (event) {
        // Obtén las fechas seleccionadas
        const fechaInicio = new Date(document.getElementById("fechaInicio").value);
        const fechaFin = new Date(document.getElementById("fechaFin").value);
    
        // Referencia al mensaje de error
        const errorFechas = document.getElementById("errorFechas");
    
        // Validar que la fecha de inicio no sea mayor que la fecha de fin
        if (fechaInicio > fechaFin) {
            // Mostrar mensaje de error
            errorFechas.style.display = "block";
    
            // Evitar que el formulario se envíe
            event.preventDefault();
        } else {
            // Ocultar mensaje de error
            errorFechas.style.display = "none";
        }
    });
    
    document.addEventListener("DOMContentLoaded", function () {
        const vehiculoSelect = document.getElementById("vehiculoId");
        const fechaInicioInput = document.getElementById("fechaInicio");
        const fechaFinInput = document.getElementById("fechaFin");
        const precioTotalElement = document.getElementById("precioTotal");
    
        // Función para actualizar el precio total
        function actualizarPrecioTotal() {
            const selectedOption = vehiculoSelect.options[vehiculoSelect.selectedIndex];
            const precioDia = parseFloat(selectedOption?.getAttribute("data-price") || 0);
            const fechaInicio = new Date(fechaInicioInput.value);
            const fechaFin = new Date(fechaFinInput.value);
    
            // Validación de fechas y cálculo
            if (!fechaInicioInput.value || !fechaFinInput.value) {
                precioTotalElement.textContent = "Selecciona fechas válidas";
                return;
            }
    
            if (!isNaN(fechaInicio.getTime()) && !isNaN(fechaFin.getTime()) && precioDia > 0) {
                const diferenciaDias = Math.floor((fechaFin - fechaInicio) / (1000 * 60 * 60 * 24)) + 1;
    
                if (diferenciaDias > 0) {
                    const precioTotal = diferenciaDias * precioDia;
                    precioTotalElement.textContent = `${precioTotal.toFixed(2)} €`;
                } else {
                    precioTotalElement.textContent = "Fechas inválidas";
                }
            } else {
                precioTotalElement.textContent = "Selecciona vehículo y fechas";
            }
        }
    
        // Eventos para recalcular el precio total
        vehiculoSelect.addEventListener("change", actualizarPrecioTotal);
        fechaInicioInput.addEventListener("change", actualizarPrecioTotal);
        fechaFinInput.addEventListener("change", actualizarPrecioTotal);
    });
    
});