<!-- Empieza sidebar -->
<div class="right-sidebar">
      	
<!-- Si un usuario no ha iniciado sesión se muestran los enlaces "Iniciar Sesión" y "Registrarse" -->
<?php
  if(!isset($_SESSION["usuario"])){

        	echo "<div class=\"loginlink\"><a href=\"login.php\">Iniciar Sesión</a></div>";
      		echo "<div class=\"registerlink\"><a href=\"register.php\">Registrarse</a></div>";
      		echo "<br />";
}
?>
      		
<!-- Un widget -->
<h1>Próximamente...</h1>


<?php 

//Se conecta con la base de datos
include("database/connect-database.php"); 

//Sql query limitado a seis resultados
$sql = "SELECT titulo, imagenURL, fecha_estreno FROM series WHERE estado = 'por estrenar' LIMIT 6";
$result = mysqli_query($conn, $sql);

if (mysqli_num_rows($result) > 0) {
    // cada fila
    while($row = mysqli_fetch_assoc($result)) {
      $titulo = $row["titulo"];
      $imagen = $row["imagenURL"];
      //Se convierte la fecha obtenida (yyyy/mm/dd) a (dd-mm--yyyy)
      $fecha_estreno= Datetime::createFromFormat('Y-m-d', $row["fecha_estreno"])->format('d-m-Y'); 

      //Cada resultado se imprime por pantalla
      echo "<div class=\"serie\"><img src='" . $imagen . "'/><br /><b>" . $titulo . "</b><br /><i>" . $fecha_estreno . "</i></div>";
    }
} else {
  //Si no ha habido ningún resultado
    echo "Ninguna de momento :(";
}

//La conexión se cierra
mysqli_close($conn);

?>

<!-- Un widget -->
		<div class="credits">
			<h1>Créditos</h1>
			<li>Logo Icon hecho por <a href="https://www.flaticon.com/authors/smashicons">Smashicons</a> from www.flaticon.com </li>
			
		</div>
</div>
<!-- Fin -->