<!-- Se empieza una sesión -->
<?php
session_start();
?>

<HTML>
   <HEAD>
      <TITLE>Editar Serie | Serieously</TITLE>

<!-- Se carga el header -->
  <?php include("template-parts/header.php"); ?>


<!--Obtener datos formulario y guardarlos en mysql -->
  <?php
   if(isset($_POST["nombre"])) {  
   //lo siguiente se ejecutará solo si se ha mandado el formulario
    
//Se conecta con la base de datos
include("database/connect-database.php"); 

//Se convierte la fecha del usuario (dd-mm-yyyy) a (yyyy/mm/dd)
$fecha = explode('-', $_POST["fecha_estreno"]);
$fechaMysql = $fecha[2] . "/" . $fecha[1] . "/" . $fecha[0];  

//Instrucción
$sql = "UPDATE series SET imagenURL='" . $_POST["imagen"] . "', titulo='" . $_POST["nombre"]  ."', cadenatv='" . $_POST["cadenatv"] . "', pais='" . $_POST["pais"]."', fecha_estreno='".$fechaMysql."', estado='" . $_POST["estado"]."'  WHERE titulo='".$_GET["titulo"]."' AND pais='".$_GET["pais"]."'";


if (mysqli_query($conn,$sql)) {
  //si ha sido correcto no se hace nada y se vuelve a cargar la página
  header("location: editar-serie.php?titulo=" . $_POST["nombre"] . "&pais=".$_POST["pais"]);
  exit();
} else {
  //si ha habido algún error se va a "error-database.php"
    header("location: error-database.php");
}

//La conexión se cierra
$conn->close();

   }
?>
<!-- Fin -->


  <!-- Caja principal -->
 <div class="main-wrapper">

 <!-- Caja dentro principal a la izquierda -->
      	<div class="left-series">
	

<!--Cargar datos de la página-->
<?php
 //Si "titulo" y "pais" no están definidos, significa que el usuario ha accedido directamente a la página.
	if(!$_GET["titulo"] || !$_GET["pais"]){
		echo "Se ha producido un error. ¿No has querido saltarte las reglas verdad?";
		exit();
	}

  //Sino, se cargan los datos
    echo "<h1>Editar: ". $_GET['titulo']. "</h1>";

//Se conecta con la base de datos
include("database/connect-database.php"); 

//Instrucción
$sql = "SELECT * FROM series WHERE titulo='" . $_GET['titulo'] . "' AND pais='" . $_GET['pais'] . "'";
$result = mysqli_query($conn, $sql);

if (mysqli_num_rows($result) > 0) {
  //solo hay una fila
  $row = mysqli_fetch_assoc($result); 
} else {
  //Se puede producir un error si se intentan obtener los datos de una serie cuando esta ha sido borrada
  header("location: error-database.php");
}

?>

<!-- El formulario para editar la serie, antes de enviarse los datos, comprobar estos con la función "serievalida"-->
<form onsubmit="return serievalida(this)" action = "<?php $_PHP_SELF ?>" method = "POST">

<!-- Caja izquierda imagen-->
<div class="editarImagenserie">

<?php
        echo "<img src='" . $row["imagenURL"] . "' /><br /><br />Imagen: <br /><input type = \"text\" name = \"imagen\" value=\"" . $row["imagenURL"] . "\"/>";
        echo "</div>"; //Fin caja izquierda imagen
        echo "<div class=\"editarInfoserie\">"; //Caja derecha Información serie


      //<!-- required - para que un campo sea obligatorio -->
      //<!-- placeholder hará que se muestre un ejemplo del dato aceptado -->

        echo "Nombre: <br /><input type = \"text\" name = \"nombre\" value=\"" . $row["titulo"] . "\" required /><br /><br />";
        echo "Cadena TV: <br /><input type = \"text\" name = \"cadenatv\" value=\"" . $row["cadenatv"] . "\" required/><br /><br />";
        echo "Pais: <br /><input type = \"text\" name = \"pais\" value=\"" . $row["pais"] . "\" required/><br /><br />";

        //Se convierte la fecha obtenida (yyyy/mm/dd) a (dd-mm-yyyy)
        $fecha= Datetime::createFromFormat('Y-m-d', $row["fecha_estreno"])->format('d-m-Y'); 

        echo "Fecha Estreno: <br /><input type = \"text\" name = \"fecha_estreno\" value=\"" . $fecha . "\" placeholder=\"dd-mm-yyyy\" required/><br /><br />";
        echo "Estado: <br /><select name=\"estado\">";

        $estadoOpciones = array("finalizada", "emision", "por estrenar");

        //Se añade el atributo "selected" según el estado guardado
        foreach($estadoOpciones as $valor){
          echo "<option value=\"" . $valor . "\"";
          if ($row["estado"] == $valor) { 
            echo " selected ";
          }
            echo ">" . $valor . "</option>";
        }

        echo "</select><br /><br />";

        echo "</div>"; //Fin caja derecha información serie
        echo "<center><input type=\"submit\" class=\"aceptar\" value=\"↠\" /></center></form></div>";
//Fin formulario

//Se cierra la conexión
mysqli_close($conn);

?>


      	
<!-- Se carga el right sidebar -->
  <?php include("template-parts/right-sidebar.php"); ?>

      </div>   <!-- Fin principal -->


   </BODY>
</HTML>