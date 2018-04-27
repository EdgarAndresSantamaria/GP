function datosUsuarioCorrectos(d) {
	var datosCorrectos = true;
	var msg = "Se han producido los siguientes fallos: \n";

  	//comprobar que  el nombre y apellido es solo texto
  	if (/^[A-Za-z\_\-\.\s\xF1\xD1]+$/.test(d.nombre.value)==false){
  		msg +="+ Nombre y apellido: Solo se admite texto. \n";
		datosCorrectos = false;
  	}
	
  	// comprobar formato dni
	if (/^[0-9]{8}-[A-Z]$/.test(d.dni.value)==false){
		msg += "+Dni: El formato del dni no es válido. \n";
		datosCorrectos= false;
	}else{ // comprobar  si la letra es la correcta
		var dni = d.dni.value;
			var numero = dni.substr(0,dni.length-2);
			var letra = dni.substr(dni.length-1,1);
			numero = numero % 23;
			var letraValida='TRWAGMYFPDXBNJZSQVHLCKET';
			letraValida=letraValida.substring(numero,numero+1);
			if (letraValida!=letra) { 
			msg += "+Dni: La letra del dni no se corresponde con el número. \n";
			datosCorrectos= false;
		}
		}
	
	//comprobar telefono
	if (/^[0-9]{9}$/.test(d.telefono.value)== false){
		msg += "+Telefono: El número de teléfono no contiene nueve dígitos. \n";
		datosCorrectos= false;
	}
	
	//comprobar fecha formato dd-mm-aaaa
	if (!formatoFechaCorrecto(d.fecha_nacimiento.value)){
		msg += "+Fecha Nacimiento: El formato debe ser dd-mm-aaaa. \n";
		datosCorrectos= false;
	}

    // comprobar email
 	if (/^\w+@\w+\.[a-z]*$/.test(d.email.value)==false){
		msg += "+Email: El formato del email no es válido";
		datosCorrectos= false;
	}
	
	if (datosCorrectos==false){
		alert(msg);
	}
  	return datosCorrectos;

}



function serievalida(d) {
	var valida = true;
	var msg = "Se han producido los siguientes fallos:\n";

	if (!(/.(jpeg|jpg|png)$/i.test(d.imagen.value)))
	{
		msg += "+ Imagen: Tipo de archivo no válido. Solo se admite .png, .jpg y .jpeg.\n";
		valida = false;
	}

	//Alfabetico con espacios
	var filtro=/^[A-Za-z\_\-\.\s\xF1\xD1]+$/;

	if(!filtro.test(d.cadenatv.value))
	{
		msg += "+ CadenaTv: El nombre de la cadena de televisión no es válido. Solo se admite texto.\n";
		valida = false;
	}

	if(!filtro.test(d.pais.value))
	{
		msg += "+ Pais: El nombre del país no es válido. Solo se admite texto.\n";
		valida = false;
	}


	//Fecha tiene que ser dd/mm/aaaa
	if(!formatoFechaCorrecto(d.fecha_estreno.value))
	{
		msg += "+ Fecha Estreno: El formato debe ser dd-mm-aaaa. \n";
		valida = false;
	}

	//La fecha tiene que existir
	if(!fechaExiste(d.fecha_estreno.value))
	{
		msg += "+ Fecha Estreno: La fecha no existe. \n";
		valida = false;
	}

	if (fechaMayorQueHoy(d.fecha_estreno.value) && d.estado.value!= "por estrenar") {
    	msg += "+ Estado: La fecha de estreno es posterior al momento actual, se ha producido un error en Estado \n";
		valida = false;
	}
	if(fechaMayorQueHoy(d.fecha_estreno.value)==false && d.estado.value=="por estrenar"){
		msg += "+ Estado: La fecha de estreno es anterior al momento actual, se ha producido un error en Estado \n";
		valida = false;
	}
	
	if(valida == false)
	{
		alert(msg);
	}

	return valida;

}


//Devuelve true si la fecha está en el formato dd-mm-aaaa
function formatoFechaCorrecto(fecha){
	var filtrofecha=/^\d{2}\-\d{2}\-\d{4}$/;
	if(filtrofecha.test(fecha)==false)
	{
		return false;
	}
	return true;
}


//Devuelve true si la fecha existe
function fechaExiste(fecha){
	var digitos = fecha.split("-");
	var dia = digitos[0];
	var mes = digitos[1];
	var ano = digitos[2];
	return (mes > 0 && mes < 13 && ano > 1500 && ano < 2527 && dia > 0 && dia <= (new Date(ano, mes, 0)).getDate());
}


//Devuelve true si d1 es igual o posterior a la fecha de hoy
function fechaMayorQueHoy(d1){ 
var nueva=new Date();
var fecha = d1.split("-");
nueva.setFullYear(fecha[2],fecha[1]-1,fecha[0]);
var hoy = new Date();
 
if (nueva >= hoy){
    return(true);
}else{
    return(false);
}

}

