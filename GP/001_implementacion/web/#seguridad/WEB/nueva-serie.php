<!-- Se empieza una sesión -->
<?php
session_start();
?>

<HTML>
   <HEAD>
      <TITLE>Nueva Serie | Serieously</TITLE>

<!-- Se carga el header -->
  <?php include("template-parts/header.php"); ?>

 <!-- Caja principal -->
   <div class="main-wrapper">

    <!-- Caja dentro principal a la izquierda -->
        <div class="left-series">
  
  <!--Variables formulario definidas para que no haya pérdida de datos si hay alguno incorrecto-->
<?php
  $nombre = isset($_POST['nombre'])?$_POST['nombre']:'';
  $cadenatv = isset($_POST['cadenatv'])?$_POST['cadenatv']:'';
  $pais = isset($_POST['pais'])?$_POST['pais']:'';
  $fecha_estreno = isset($_POST['fecha_estreno'])?$_POST['fecha_estreno']:'';
  $estado = isset($_POST['estado'])?$_POST['estado']:'';
  $imagen = isset($_POST['imagen'])?$_POST['imagen']:'';
?>

<!--Obtener datos formulario y guardarlos en mysql -->
  <?php
   if(isset($_POST["nombre"])) { 
    //se pone isset porque al principio no se le ha enviado nada al formulario y entonces sale error

//Se conecta con la base de datos
include("database/connect-database.php"); 

//Se convierte la fecha del usuario (dd-mm-yyyy) a (yyyy/mm/dd)
$fecha = explode('-', $fecha_estreno);
$fechaMysql = $fecha[2] . "/" . $fecha[1] . "/" . $fecha[0];  

//Instrucción
$sql = "INSERT INTO series VALUES ('".$nombre."', '" .$cadenatv."', '" .$pais."', '" .$fechaMysql."', '" .$estado."', '" .$imagen."', curdate())";

if (mysqli_query($conn, $sql)) {
  //Se irá a editar-serie.php?titulo=xxxx&pais=xxxx
    $getstring = "titulo=" .  $nombre . "&pais=" . $pais;
    header("location: editar-serie.php?" . $getstring );
} else {
    if (mysqli_errno($conn) == 1062) { //serie ya en el sistema
       echo "<center><div style=\"color: red; font-size: 25px; background: black;\">-- Serie ya en el sistema --</div></center>";
    }
}

//La conexión se cierra
mysqli_close($conn);

   }
?>
<!-- Fin -->


<h1>Nueva Serie</h1>
         
  <!-- Formulario, antes de enviarse los datos se comprueban con la función "serievalida" -->
  <form onsubmit="return serievalida(this)" action = "<?php $_PHP_SELF ?>" method = "POST" >
     <div class="nuevaserie">
      Imagen: <br /><input type = "text"  name = "imagen" value="<?php echo $imagen;?>" required /><br /><br /> 
      <!-- Se pone la variable $imagen para que los datos se guarden si serievalida = false -->
      <!-- Se pone "required" para que el usuario esté obligado a rellenar el campo -->
      <!-- placeholder hará que se muestre un ejemplo del dato aceptado -->

      Nombre: <br /><input type = "text" name = "nombre" value="<?php echo $nombre;?>"  required/><br /><br />
      Cadena TV: <br /><input type = "text" name = "cadenatv" value="<?php echo $cadenatv;?>"  required /><br /><br />
      Pais: <br /><input type = "text" name = "pais" value="<?php echo $pais;?>" required /><br /><br />
      Fecha Estreno: <br /><input type = "text" name = "fecha_estreno"  value="<?php echo $fecha_estreno;?>" placeholder="dd-mm-yyyy" required /><br /><br />
       Estado: <br /><select name="estado" value="<?php echo $estado;?>"   >
        <option value="finalizada" >Finalizada</option>
        <option value="emision">Emision</option>
        <option value="por estrenar">Por Estrenar</option>
      </select>
      </div>
<br />
    <center><input class="aceptar" type="submit" value="↠" /></center>
  </form> <!-- Cierre formulario -->


</div>  <!-- Cerrar caja dentro principal a la izquierda -->


<!-- Se carga el right sidebar -->
  <?php include("template-parts/right-sidebar.php"); ?>

  </div>     <!-- Cerrar caja principal -->



   </BODY>
</HTML>