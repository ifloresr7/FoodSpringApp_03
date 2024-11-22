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
        crearAlquilerDialog.classList.remove("hidden");
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
                crearAlquilerDialog.classList.add("hidden");
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
        crearAlquilerDialog.classList.add("hidden");
    });
});