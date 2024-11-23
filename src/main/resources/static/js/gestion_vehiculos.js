let vehiculosList;

const dialogVehiculo = document.getElementById('vehiculoModal');
const diVehiculoId = document.getElementById('diVehiculoId');
const diColor = document.getElementById('diColor'); // Agregado para el color
const diMarca = document.getElementById('diMarca');
const diPuertas = document.getElementById('diPuertas');
const diAutonomia_km = document.getElementById('diAutonomia_km');
const diPotencia_cv = document.getElementById('diPotencia_cv');

document.querySelectorAll('.closeButtonModal').forEach(button => {
    button.addEventListener('click', (e) => {
        e.preventDefault();
        dialogVehiculo.close();
    });
});

document.querySelectorAll('.editButton').forEach(button => {
    button.addEventListener('click', function () {
        const id = this.closest('tr').getAttribute('data-id');
        const vehiculo = vehiculosList.find(vehiculo => vehiculo.id === parseInt(id, 10));
        if (vehiculo) {
            diVehiculoId.value = vehiculo.id;
            diColor.value = vehiculo.color;
            diMarca.value = vehiculo.marca;
            diPuertas.value = vehiculo.puertas;
            diAutonomia_km.value = vehiculo.autonomia_km;
            diPotencia_cv.value = vehiculo.potencia_cv;
            dialogVehiculo.showModal();
        }
    });
});

document.querySelectorAll('.deleteButton').forEach(button => {
    button.addEventListener('click', function () {
        const id = this.closest('tr').getAttribute('data-id');
        eliminarVehiculo(id);
    });
});

document.querySelector('.createButton').addEventListener('click', function () {
    diVehiculoId.value = "";
    diColor.value = "";
    diMarca.value = "";
    diPuertas.value = "";
    diAutonomia_km.value = "";
    diPotencia_cv.value = "";
    dialogVehiculo.showModal();
});

// Función para guardar o actualizar un vehiculo
document.getElementById('vehiculoForm').addEventListener('submit', function (e) { // Cambié vehciuloForm a vehiculoForm
    e.preventDefault();
    const vehiculoData = {
        id: diVehiculoId.value,
        color: diColor.value,
        marca: diMarca.value,
        puertas: diPuertas.value,
        autonomia_km: diAutonomia_km.value,
        potencia_cv: diPotencia_cv.value
    };
    const method = vehiculoData.id ? 'PUT' : 'POST';
    const url = vehiculoData.id ? `/api/vehiculos/${vehiculoData.id}` : '/api/vehiculos';

    fetch(url, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(vehiculoData)
    })
        .then(response => response.json())
        .then(data => {
            alert('Vehiculo creado/actualizado correctamente');
            dialogVehiculo.close();
            cargarVehiculos();
        })
        .catch(error => console.error('Error al guardar el vehiculo:', error));
});

// Función para eliminar un vehiculo
function eliminarVehiculo(id) {
    if (confirm('¿Estás seguro de que deseas eliminar este vehiculo?')) {
        fetch(`/api/vehiculos/eliminar/${id}`, {
            method: 'DELETE',
        })
            .then(() => {
                alert('Vehiculo eliminado correctamente');
                cargarVehiculos(); // Refresca la lista de vehiculos
            })
            .catch(error => console.error('Error al eliminar el vehiculo:', error));
    }
}