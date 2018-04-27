
<!-- Se empieza una sesión -->
<?php
session_start();
?>

<HTML>
   <HEAD>
      <TITLE>Borrar Serie | Serieously</TITLE>

      <!-- Se carga el header -->
  <?php include("template-parts/header.php"); ?>


    <!-- Caja principal -->
   <div class="main-wrapper">

    <!-- Caja dentro principal a la izquierda -->
      	<div class="left-series">


<?php
  //Si "titulo" y "pais" no están definidos, significa que el usuario ha accedido directamente a la página.
	if(!$_GET["titulo"] || !$_GET["pais"]){
		echo "Se ha producido un error. ¿No has querido saltarte las reglas verdad?";
		exit();
	}else{

//Se conecta con la base de datos
include("database/connect-database.php"); 

//Instrucción
$sql = "DELETE FROM series WHERE titulo=\"" . $_GET["titulo"] . "\" AND pais=\"" . $_GET["pais"] . "\"";

if (mysqli_query($conn, $sql)) {
    //Si no ha habido ningún error se imprime por pantalla el siguiente mensaje.
    echo "<h2>La serie " . $_GET["titulo"] . " se ha eliminado correctamente.</h2> <br /><a href=\"../WEB\">Volver</a>";
} else {
  //Si se produce un error
    header("location: error-database.php");
}

//La conexión se cierra
mysqli_close($conn);
	}
?>


  </div> <!-- Cierre Caja dentro principal a la izquierda -->


<!-- Se carga el right sidebar -->
  <?php include("template-parts/right-sidebar.php"); ?>


      </div> <!-- Cierre Caja principal -->


   </BODY>
</HTML>