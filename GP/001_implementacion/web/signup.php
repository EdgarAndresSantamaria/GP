<!DOCTYPE html>
<!-- Se empieza una sesión -->
<?php
session_start();
?>

<!-- Si el usuario está identificado no se puede registrar -->
<?php
if(isset($_SESSION["username"])){
  header("location: ../WEB");
}
?>

<html>
<head>
  <title>Sign Up</title>

  <?php include 'parts/links.html';?>

</head>
<body>

  <header>

    <!-- Se anade el menu -->
    <?php include 'parts/menu.php';?>

  </header>
  <!--Variables formulario definidas para que no haya pérdida de datos si hay alguno incorrecto-->

<?php
    $username = isset($_POST['username'])?$_POST['username']:'';
    $password = isset($_POST['password'])?$_POST['password']:'';
    $email = isset($_POST['email'])?$_POST['email']:'';
?>

<!--Obtener datos formulario y guardarlos en mysql -->
  <?php
   if( isset($_POST["username"])) {
   //se pone isset porque al principio no se le ha enviado nada al formulario y entonces sale error

 //Se conecta con la base de datos
include("database/connect-database.php");

//Instrucción
$sql= "INSERT INTO users VALUES ('".$username."','".$email."', MD5('".$password."'))";

if (mysqli_query($conn, $sql)){
  //Si se ha registrado sale un link para identificarse.
   echo "<center>Te has registrado satisfactoriamente como " . $username . ". <br /><br /><a href=\"login.php\">Inicia sesión</a></center>";
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


<div id="main-container">
  <div id="left-panel">
    imagen
  </div>

<div id="right-panel">

  <div class="formulario">
        <?php
            $username = isset($_POST['username'])?$_POST['username']:'';
            $password = isset($_POST['password'])?$_POST['password']:'';
            $email = isset($_POST['email'])?$_POST['email']:'';
        ?>

        <!--Obtener datos formulario y guardarlos en mysql -->
          <?php
           if( isset($_POST["nombre"])) {
           //se pone isset porque al principio no se le ha enviado nada al formulario y entonces sale error

         //Se conecta con la base de datos
        include("database/connect-database.php");

        //Instrucción
        $sql= "INSERT INTO users VALUES ('".$username."','".$email."', MD5('".$password."'))";

        if (mysqli_query($conn, $sql)){
          //Si se ha registrado sale un link para identificarse.
           echo "<center>Te has registrado satisfactoriamente como " . $username . ". <br /><br /><a href=\"login.php\">Inicia sesión</a></center>";
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

        <form onsubmit="return datosUsuarioCorrectos(this)" action="<?php $_PHP_SELF ?>" method = "POST" >
          <input type="text" name="username" id="username" value="<?php echo $username;?>" placeholder="Username..." required/>
          <input type="email" name="email" id="email" value="<?php echo $email;?>" placeholder="Email..." required/>
          <input type="password" name="password" id="password" value="<?php echo $password;?>" placeholder="Password..." required/>
          <button type="submit">Sign Up</button>
        </br>
      </br>
      <span style="text-align: right">Already signed up? Log In!</span>
    </form>
  </div>


</div>

</body>
</html>
