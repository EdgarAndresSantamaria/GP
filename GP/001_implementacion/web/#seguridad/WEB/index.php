<!-- Se empieza una sesión -->
<?php
session_start();
?>

<HTML>
   <HEAD>
      <TITLE>Serieously</TITLE>

<!-- Se carga el header -->
  <?php include("template-parts/header.php"); ?>


      <!-- Caja principal -->
   <div class="main-wrapper">

    <!-- Caja dentro principal a la izquierda -->
        <div class="left-series">

	<h1>TV Series agregadas recientemente</h1>

<!-- Botón "Añadir Serie" -->
  <a style="background:none; padding: 0;" href="nueva-serie.php"><div class="botonNuevaSerie">Añadir Serie</div></a>
<br /> <!-- linea en blanco -->
<!-- Cuando el contenido de la tabla no quepa por el ancho de la pantalla se utilizará un scroll horizontal -->
<div style="overflow-x:auto;">
  <table width="100%" style="font-size: 14px; ">
    <tr><th>Título</th><th>Cadena TV</th><th>Pais</th><th>Estado</th><th></th></tr>
  

  <!--Se obtienen las series agregadas recientemente-->
<?php
//Se conecta con la base de datos
include("database/connect-database.php"); 

//Instrucción
$sql = "SELECT titulo, cadenatv, pais, estado FROM series ORDER BY fecha_anadida DESC LIMIT 20";
$result = mysqli_query($conn, $sql);

if (mysqli_num_rows($result) > 0) {
    // cada fila
    while($row = mysqli_fetch_assoc($result)) {     
      //Cada titulo tendrá un enlace a editar-serie.php?titulo=xxxx&pais=xxxx y una opción para borrarlo ç
      //con confimación javascript
        $getstring = "titulo=" .  $row["titulo"] . "&pais=" . $row["pais"];
        echo "<tr><td><a href=\"editar-serie.php?" . $getstring . "\">" . $row["titulo"] . "</a></td>";
        echo "<td>" . $row["cadenatv"] . "</td><td>" . $row["pais"] . "</td>";
        echo "<td style='text-transform: Capitalize;'>" . $row["estado"] ."</td>";
        echo "<td><a onClick=\"javascript: return confirm('¿Estas seguro de que deseas borrar " . $row["titulo"] . "?');\"  href=\"borrar-serie.php?" . $getstring . "\" style=\"background: none; text-transform: uppercase; font-size: 11px; color: red; \" >Borrar</a></td></tr>";
    }
} else {
  //Si no hay ninguna serie
    echo "No hay ninguna serie.";
}

//La conexión se cierra
mysqli_close($conn);
?>

</table>
</div>

<br />

<!-- Se crea el formulario de búsqueda (por nombre serie) -->
      <form action = "<?php $_PHP_SELF ?>" method = "POST">
        <input style="float: left;width: 80%; border-top: 1px solid grey;border-bottom: 1px solid grey;border-right: 0px;border-left: 0px;" type = "text" name = "busqueda" /><input type="submit" class="buscar" value="Buscar" /></a>

</form><!-- fin formulario-->


<!--Obtener datos de búsqueda-->
  <?php
   if( isset($_POST["busqueda"])) {  
   //si se ha producido una búsqueda, los resultados obtenidos aparecerán en una tabla. Cuando el contenido de la tabla no quepa por el ancho de la pantalla se utilizará un scroll horizontal
      echo "<div style=\"overflow-x:auto;\"> <table width=\"100%\" style=\"font-size: 14px; \"> <tr><th>Título</th><th>Cadena TV</th><th>Pais</th><th>Estado</th><th></th></tr>";

//Se conecta con la base de datos
include("database/connect-database.php"); 

//Instrucción
$sql = "SELECT titulo, cadenatv, pais, estado FROM series WHERE titulo LIKE \"" . $_POST['busqueda'] . "%\"";
$result = mysqli_query($conn, $sql);

if (mysqli_num_rows($result) > 0) {
    // cada fila
    while($row = mysqli_fetch_assoc($result)) {     
      //Cada titulo tendrá un enlace a editar-serie.php?titulo=xxxx&pais=xxxx y una opción para borrarlo ç
      //con confimación javascript
        $getstring = "titulo=" .  $row["titulo"] . "&pais=" . $row["pais"];
        echo "<tr><td><a href=\"editar-serie.php?" . $getstring . "\">" . $row["titulo"] . "</a></td>";
        echo "<td>" . $row["cadenatv"] . "</td><td>" . $row["pais"] . "</td>";
        echo "<td style='text-transform: Capitalize;'>" . $row["estado"] ."</td>";
        echo "<td><a href=\"borrar-serie.php?" . $getstring . "\" style=\"background: none; text-transform: uppercase; font-size: 11px; color: red; \" >Borrar</a></td></tr>";
    }
    
} else {
  // Si no hay ningún resultado
    echo "No hay ninguna coincidencia.";
}
//Se cierra las tags restantes 
echo "</table></div>";

//Se cierra la conexión
mysqli_close($conn);

   }
?>
<!-- Fin -->


</div> <!-- Fin caja izquierda -->

      	
<!-- Se carga el right sidebar -->
  <?php include("template-parts/right-sidebar.php"); ?>

      </div> <!-- Fin caja principal -->


   </BODY>
</HTML>