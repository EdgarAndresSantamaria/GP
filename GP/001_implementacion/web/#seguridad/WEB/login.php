<!-- Se empieza una sesión -->
<?php
session_start();
?>

<!-- Si el usuario está identificado no se puede logear -->
<?php
if(isset($_SESSION["usuario"])){
  header("location: ../WEB");
}
?>

<HTML>
   <HEAD>
      <TITLE>Iniciar Sesión | Serieously</TITLE>

      <!-- Se carga el header -->
  <?php include("template-parts/header.php"); ?>

   <!-- Caja principal -->
<div class="formulariocontainerlogin">
     <!-- Caja texto -->
   <div class="formulariotexto">

<!--Obtener datos formulario y guardarlos en mysql -->
  <?php
   if( isset($_POST["usuario"])) {  
   //se pone isset porque al principio no se le ha enviado nada al formulario y entonces sale error


//Se conecta con la base de datos
include("database/connect-database.php"); 

//Instrucción
$sql = "SELECT usuario, contrasena FROM miembros WHERE usuario='". $_POST["usuario"] . "' AND contrasena= MD5('" . $_POST["contrasena"] . "')";
//Un resultado
$result = mysqli_query($conn, $sql);

if (mysqli_num_rows($result) > 0) {
  //Se establece la variable de la sesión y se va a index.php
   $_SESSION['usuario'] = $_POST["usuario"];
   header("location: ../WEB");
} else {
  //Si los datos son incorrectos 
   echo "<center><div style=\"color: red; font-size: 25px; background: black;\">-- Datos incorrectos --</div></center>";

}


//La conexión se cierra
mysqli_close($conn);

   }
?>
<!-- Fin -->


	<center><h1>Iniciar Sesión</h1></center>

<!-- El formulario -->
      <form action = "<?php $_PHP_SELF ?>" method = "POST">
      	<table style="margin-bottom: 20px; margin: 0 auto;" class="formulariologin" width="100%">
      		<tr>
      			<td>Usuario</td>
      			<td><input type = "text" name = "usuario" required/></td> <!-- HTML5 permite poner required -->
      		</tr>
      		<tr>
      			<td>Contraseña</td>
      			<td><input type = "password" name = "contrasena" required/></td>
      		</tr>
      	</table>
         <br /><br />
         <center><input class="aceptar" type="submit" value="↠" /></center>
      </form>


 </div> <!-- Cerrar Caja texto -->
</div>   <!-- Cerrar Caja principal -->

   Logo Icon hecho por <a href="https://www.flaticon.com/authors/smashicons">Smashicons</a> from www.flaticon.com


   </BODY>
</HTML>