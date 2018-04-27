<!-- Se empieza una sesión -->
<?php
session_start();
?>

<!-- Si el usuario está identificado no se puede registrar -->
<?php
if(isset($_SESSION["usuario"])){
  header("location: ../WEB");
}
?>

<HTML>
   <HEAD>
      <TITLE>Registrarse | Serieously</TITLE>

      <!-- Se carga el header -->
  <?php include("template-parts/header.php"); ?>

<!-- Caja principal -->
<div class="formulariocontainerRegistro">
  <!-- Caja texto -->
   <div class="formulariotexto">


  <!--Variables formulario definidas para que no haya pérdida de datos si hay alguno incorrecto-->
<?php
  $nombre = isset($_POST['nombre'])?$_POST['nombre']:'';
  $usuario = isset($_POST['usuario'])?$_POST['usuario']:'';
  $contrasena = isset($_POST['contrasena'])?$_POST['contrasena']:'';
  $dni = isset($_POST['dni'])?$_POST['dni']:'';
  $telefono = isset($_POST['telefono'])?$_POST['telefono']:'';
  $fecha_nacimiento = isset($_POST['fecha_nacimiento'])?$_POST['fecha_nacimiento']:'';
  $email = isset($_POST['email'])?$_POST['email']:'';

?>

<!--Obtener datos formulario y guardarlos en mysql -->
  <?php
   if( isset($_POST["nombre"])) {  
   //se pone isset porque al principio no se le ha enviado nada al formulario y entonces sale error
  
 //Se conecta con la base de datos
include("database/connect-database.php"); 

//Se convierte la fecha del usuario (dd-mm-yyyy) a (yyyy/mm/dd)
$fecha = explode('-', $fecha_nacimiento);
$fechaMysql = $fecha[2] . "/" . $fecha[1] . "/" . $fecha[0];  

//Instrucción
$sql= "INSERT INTO miembros VALUES ('".$usuario."', MD5('".$contrasena."'), '".$nombre."', '".$dni."', '".$telefono."', '".$fechaMysql."', '".$email."')";

if (mysqli_query($conn, $sql)){
  //Si se ha registrado sale un link para identificarse.
   echo "<center>Te has registrado satisfactoriamente como " . $usuario . ". <br /><br /><a href=\"login.php\">Inicia sesión</a></center>";
   exit();
} else {
  //Si el usuario ya existe sale un mensaje por pantalla
   if (mysqli_errno($conn) == 1062){ //Usuario ya en el sistema
      echo "<center><div style=\"color: red; font-size: 25px; background: black;\">-- Usuario ya registrado --</div></center>";
   }
}    
 
 //La conexión se cierra
mysqli_close($conn);

  } 
?>
<!-- Fin -->



	<h1>Nuevo aquí? Crea una cuenta gratuita!</h1>

<!-- Formulario, antes de enviarse los datos se comprueban mediante la función "datosUsuarioCorrectos"-->
      <form onsubmit="return datosUsuarioCorrectos(this)" action="<?php $_PHP_SELF ?>" method = "POST" > 
      	<table style="margin-bottom: 20px;" class="formularioregister" width="100%">
      		<tr>
      			<td>Usuario</td>
      			<td><input type = "text" name = "usuario" value="<?php echo $usuario;?>" required /></td>
            <!-- required - nuevo de HTML5 - para que un usuario tenga que introducir datos de forma obligatoria -->
            <!-- Se pone la variable $usuario como value para que los datos no se pierdan si datosUsuarioCorrectos=false -->
            <!-- placeholder hará que se muestre el estilo del dato aceptado -->
      		</tr>
      		<tr>
      			<td>Contraseña</td>
      			<td><input type = "password" name = "contrasena" value="<?php echo $contrasena;?>" required/></td>
      		</tr>
      		<tr>
      			<td>Nombre Y Apellido</td>
      			<td><input type = "text" name = "nombre" value="<?php echo $nombre;?>" required/></td>
      		</tr>
      		<tr>
      			<td>DNI</td>
      			<td><input type = "text" name = "dni" value="<?php echo $dni;?>" placeholder="11111111-Z" required/></td>
      		</tr>
      		<tr>
      			<td>Teléfono</td>
      			<td><input type = "text" name = "telefono" value="<?php echo $telefono;?>" required/></td>
      		</tr>
      		<tr>
      			<td>Fecha de Nacimiento</td>
      			<td><input type = "text" name = "fecha_nacimiento" value="<?php echo $fecha_nacimiento;?>" placeholder="dd-mm-yyyy" required/></td>
      		</tr>
      		<tr>
      			<td>Email</td>
      			<td><input type = "text" name = "email" value="<?php echo $email;?>" required/></td>
      		</tr>
      	</table>
         <center><input class="aceptar" type="submit" value="↠" /></center>
      </form>

   
</div> <!-- Fin caja principal-->
</div> <!-- Caja texto -->

   Logo Icon hecho por <a href="https://www.flaticon.com/authors/smashicons">Smashicons</a> from www.flaticon.com

  
   </BODY>
</HTML>