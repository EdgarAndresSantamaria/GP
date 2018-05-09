<?php
		if( isset($_POST["username"])) {

		//Se conecta con la base de datos
		include("../database/connect-database.php");

		//Instrucción
		$sql= "INSERT INTO users VALUES ('".$_POST["username"]."','".$_POST["email"]."', MD5('".$_POST["password"]."'))";

		if (mysqli_query($conn, $sql)){
		  //Si se ha registrado
			echo "true";
		} else {
		  //Si el usuario ya existe sale un mensaje por pantalla
		   if (mysqli_errno($conn) == 1062){ //Usuario ya en el sistema

          	echo "false";

		   }
		}

		 //La conexión se cierra
		mysqli_close($conn);
	}
?>