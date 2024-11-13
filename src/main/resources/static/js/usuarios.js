let usuariosList;
const dialogCliente = document.getElementById('usuarioModal');
const diClienteId = document.getElementById('diUsuarioId');
const diNombre = document.getElementById('diNombre');
const diApellidos = document.getElementById('diApellidos');
const diEmail = document.getElementById('diEmail');
const diTelefono = document.getElementById('diTelefono');
const diDireccion = document.getElementById('diDireccion');

// Función para cargar clientes
function cargarUsuarios() {
    fetch('/api/usuarios')
        .then(response => response.json())
        .then(usuarios => {
            const tableBody = document.getElementById('usuariosTable');
            tableBody.innerHTML = '';
            usuariosList = usuarios;
            usuarios.forEach(usuario => {
                const row = `<tr data-id="${usuario.id}">
                    <td>${usuario.id}</td>
                    <td>${usuario.nombre} ${usuario.apellidos}</td>
                    <td>${usuario.email}</td>
                    <td>${usuario.telefono}</td>
                    <td>${usuario.direccion}</td>
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
            const usuario = usuariosList.find(usuario => usuario.id === parseInt(id, 10));
            if (usuario) {
                diUsuarioId.value = usuario.id;
                diNombre.value = usuario.nombre;
                diApellidos.value = usuario.apellidos;
                diEmail.value = usuario.email;
                diTelefono.value = usuario.telefono;
                diDireccion.value = usuario.direccion;
                dialogCliente.showModal();
            }
        });
    });

    document.querySelectorAll('.deleteButton').forEach(button => {
        button.addEventListener('click', function () {
            const id = this.closest('tr').getAttribute('data-id');
            eliminarUsuario(id);
        });
    });

    document.querySelector('.createButton').addEventListener('click', function () {
        diUsuarioId.value = "";
        diNombre.value = "";
        diApellidos.value = "";
        diEmail.value = "";
        diTelefono.value = "";
        diDireccion.value = "";
        dialogCliente.showModal();
    });
}

// Función para guardar o actualizar un cliente
document.getElementById('usuarioForm').addEventListener('submit', function(e) {
    e.preventDefault();
    const usuarioData = {
        id: diUsuarioId.value,
        nombre: diNombre.value,
        apellidos: diApellidos.value,
        email: diEmail.value,
        telefono: diTelefono.value,
        direccion: diDireccion.value
    };
    const method = usuarioData.id ? 'PUT' : 'POST';
    const url = usuarioData.emailid ? `/api/usuarios/${clienteData.id}` : '/api/usuarios';

    fetch(url, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(clienteData)
    })
    .then(response => response.json())
    .then(data => {
        alert('Usuario creado/actualizado correctamente');
        dialogCliente.close();
        cargarUsuarios();
    })
    .catch(error => console.error('Error al guardar el cliente:', error));
});

// Función para eliminar un cliente
function eliminarUsuario(id) {
    if (confirm('¿Estás seguro de que deseas eliminar este usuario?')) {
        fetch(`/api/usuarios/eliminar/${id}`, {
            method: 'DELETE',
        })
        .then(() => {
            alert('Usuario eliminado correctamente');
            cargarUsuarios(); // Refresca la lista de clientes
        })
        .catch(error => console.error('Error al eliminar el usuario:', error));
    }
}


// Cargar clientes al iniciar
document.addEventListener('DOMContentLoaded', cargarClientes);