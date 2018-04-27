<!-- Se empieza una sesión -->
<?php
session_start();
?>

<HTML>
   <HEAD>
      <TITLE>Mi Cuenta | Serieously</TITLE>


 <!-- Se carga el header -->
  <?php include("template-parts/header.php"); 
  

//Se cargan los datos del usuario


//Se conecta con la base de datos
include("database/connect-database.php"); 

//Instrucción
$sql = "SELECT * FROM miembros WHERE usuario='" . $_SESSION['usuario'] . "'";
$result = mysqli_query($conn, $sql);

if (mysqli_num_rows($result) > 0) {
    // solo hay una fila
    $row = mysqli_fetch_assoc($result); 

  $nombre = $row["nombre_apellido"];
  $dni = $row["dni"];
  $telefono = $row["telefono"];
   //Se convierte la fecha obtenida (yyyy/mm/dd) a (dd-mm-yyyy)
  $fecha_nacimiento= Datetime::createFromFormat('Y-m-d', $row["fecha_nacimiento"])->format('d-m-Y'); 
  $email = $row["email"];
}else{
  //Si se produce un error
  header("location: error-database.php");
}

//La conexión se cierra
mysqli_close($conn);

?>

<!-- Caja principal -->
 <div class="main-wrapper">

<!-- Caja a la izquierda -->
        <div class="left-series">


<!-- Modificar los datos -->
<?php
   if( isset($_POST["contrasena"])) {
    //Si la contraseña se ha introducido
   
  //Se conecta con la base de datos
include("database/connect-database.php"); 

//Se convierte la fecha del usuario (dd-mm-yyyy) a (yyyy/mm/dd)
$fecha = explode('-', $_POST["fecha_nacimiento"]);
$fechaMysql = $fecha[2] . "/" . $fecha[1] . "/" . $fecha[0];  

//Instrucción
$sql = "UPDATE miembros SET contrasena= MD5('".$_POST["contrasena"] . "'), nombre_apellido='".$_POST["nombre"] ."', dni='".$_POST["dni"] ."', telefono='".$_POST["telefono"] ."', fecha_nacimiento='".$fechaMysql."', email='".$_POST["email"] ."' WHERE usuario='".$_SESSION["usuario"] ."'";

if (mysqli_query($conn,$sql)) {
  //Si no hay errores se vuelve a cargar la página
 header("location: usuario.php" );  
} else {
  //Se va a "error-database.php"
    header("location: error-database.php");
}

//La conexión se cierra
mysqli_close($conn);

}
?>

	<h1>Modificar datos</h1>

<!-- Formulario con los datos del usuario identificado, se comprueban con "datosUsuarioCorrectos" -->
      <form action = "<?php $_PHP_SELF ?>" method = "POST" onsubmit="return datosUsuarioCorrectos(this)">
        <div class="nuevaserie"> <!-- Caja datos -->
      Contraseña: <br /><input type = "password"  name = "contrasena" required /><br /><br /> 
      
      <!-- required - para que un campo sea obligatorio -->
      <!-- placeholder hará que se muestre el estilo del dato aceptado -->
      <!-- Se pone la variable $nombre como value para que los datos no se pierdan si datosUsuarioCorrectos=false -->

      Nombre Y Apellido: <br /><input type = "text" name = "nombre"  value="<?php echo $nombre;?>" required/><br /><br />
      DNI: <br /><input type = "text" name = "dni"  value="<?php echo $dni;?>" placeholder="11111111-Z" required/><br /><br />
      Teléfono: <br /><input type = "text" name = "telefono"  value="<?php echo $telefono;?>" required/><br /><br />
      Fecha de Nacimiento: <br /><input type = "text" name = "fecha_nacimiento"  value="<?php echo $fecha_nacimiento;?>" placeholder="dd-mm-yyyy"  required/><br /><br />
      Email: <br /><input type = "text" name = "email"  value="<?php echo $email;?>" required /><br /><br />
      </div>
         <center><input class="aceptar" type="submit" value="↠" /></center>
      </form>

</div> <!-- Fin caja datos -->

<!-- Se carga el right sidebar -->
  <?php include("template-parts/right-sidebar.php"); ?>

  
    </div> <!-- Fin caja principal -->


  
   </BODY>
</HTML>
