let clientesList;
const dialogCliente = document.getElementById('clienteModal');
const diClienteId = document.getElementById('diClienteId');
const diNombre = document.getElementById('diNombre');
const diApellidos = document.getElementById('diApellidos');
const diEmail = document.getElementById('diEmail');
const diTelefono = document.getElementById('diTelefono');
const diDireccion = document.getElementById('diDireccion');

// Función para cargar clientes
function cargarClientes() {
    fetch('/api/clientes')
        .then(response => response.json())
        .then(clientes => {
            const tableBody = document.getElementById('clientesTable');
            tableBody.innerHTML = '';
            clientesList = clientes;
            clientes.forEach(cliente => {
                const row = `<tr data-id="${cliente.id}">
                    <td>${cliente.id}</td>
                    <td>${cliente.nombre} ${cliente.apellidos}</td>
                    <td>${cliente.email}</td>
                    <td>${cliente.telefono}</td>
                    <td>${cliente.direccion}</td>
                    <td>
                        <button class="editButton">Editar</button>
                        <button class="deleteButton">Eliminar</button>
                    </td>
                </tr>`;
                tableBody.insertAdjacentHTML('beforeend', row);
            });
            createFunctionalityButton();
        });
}

// Configura los botones de edición y eliminación
function createFunctionalityButton() {
    document.querySelectorAll('.closeButtonModal').forEach(button => {
        button.addEventListener('click', (e) => {
            e.preventDefault();
            dialogCliente.close();
        });
    });

    document.querySelectorAll('.editButton').forEach(button => {
        button.addEventListener('click', function () {
            const id = this.closest('tr').getAttribute('data-id');
            const cliente = clientesList.find(cliente => cliente.id === parseInt(id, 10));
            if (cliente) {
                diClienteId.value = cliente.id;
                diNombre.value = cliente.nombre;
                diApellidos.value = cliente.apellidos;
                diEmail.value = cliente.email;
                diTelefono.value = cliente.telefono;
                diDireccion.value = cliente.direccion;
                dialogCliente.showModal();
            }
        });
    });

    document.querySelectorAll('.deleteButton').forEach(button => {
        button.addEventListener('click', function () {
            const id = this.closest('tr').getAttribute('data-id');
            eliminarCliente(id);
        });
    });

    document.querySelector('.createButton').addEventListener('click', function () {
        diClienteId.value = "";
        diNombre.value = "";
        diApellidos.value = "";
        diEmail.value = "";
        diTelefono.value = "";
        diDireccion.value = "";
        dialogCliente.showModal();
    });
}

// Función para guardar o actualizar un cliente
document.getElementById('clienteForm').addEventListener('submit', function(e) {
    e.preventDefault();
    const clienteData = {
        id: diClienteId.value,
        nombre: diNombre.value,
        apellidos: diApellidos.value,
        email: diEmail.value,
        telefono: diTelefono.value,
        direccion: diDireccion.value
    };
    const method = clienteData.id ? 'PUT' : 'POST';
    const url = clienteData.id ? `/api/clientes/${clienteData.id}` : '/api/clientes';

    fetch(url, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(clienteData)
    })
    .then(response => response.json())
    .then(data => {
        alert('Cliente creado/actualizado correctamente');
        dialogCliente.close();
        cargarClientes();
    })
    .catch(error => console.error('Error al guardar el cliente:', error));
});

// Función para eliminar un cliente
function eliminarCliente(id) {
    if (confirm('¿Estás seguro de que deseas eliminar este cliente?')) {
        fetch(`/api/clientes/eliminar/${id}`, {
            method: 'DELETE',
        })
        .then(() => {
            alert('Cliente eliminado correctamente');
            cargarClientes(); // Refresca la lista de clientes
        })
        .catch(error => console.error('Error al eliminar el cliente:', error));
    }
}


// Cargar clientes al iniciar
document.addEventListener('DOMContentLoaded', cargarClientes);