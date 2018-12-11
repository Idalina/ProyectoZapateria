
function validacion() {
    var nombre = document.formu.nombre.value;
    var edad = document.formu.edad.value;


    if (nombre.length > 30) {
        alert("Esto supera el máximo permitido");
        document.formu.nombre.value = " ";
        document.formu.nombre.focus();

        return false;
    }

    if (nombre == null || nombre.length == 0 || /^\s+$/.test(nombre)) {
        alert("Este campo no puede estar vacio")

        document.formu.nombre.value = " ";
        document.formu.nombre.focus();

        return false;
    }

    if (/^[a-z][a-z]*/.test(formu.nombre.value) == false) {
        alert("Este campo solo puede tener letras")
        document.formu.nombre.value = " ";
        document.formu.nombre.focus();
        return false;
    }

    if (edad >= 20 && edad <= 40) {
        alert("Con esta edad no puedes usar este programa")
        document.formu.nombre.value = " ";
        document.formu.nombre.focus();
        return false;
    }

    if (edad == null || edad.length == 0 || /^\s+$/.test(edad)) {
        alert("Este campo no puede estar vacio")
         document.formu.nombre.value = " ";
        document.formu.nombre.focus();
        return false;
    }
    if (isNaN(edad)) {
        alert("Este campo solo puede tener números")
        document.formu.nombre.value = " ";
        document.formu.nombre.focus();
        return false;
    }
    return true;
}