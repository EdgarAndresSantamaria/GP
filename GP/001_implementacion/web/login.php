<!DOCTYPE html>

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

<html>
<head>
  <title>Log In</title>

  <?php include 'parts/links.html';?>

</head>
<body>

  <header>

    <!-- Se anade el menu -->
    <?php include 'parts/menu.php';?>

  </header>

  <div id="main-container">
    <div id="left-panel">

    </div>

    <div id="right-panel">

      <div class="formulario">


        <!--Obtener datos formulario y guardarlos en mysql -->
        <?php
        if( isset($_POST["username"])) {
          //se pone isset porque al principio no se le ha enviado nada al formulario y entonces sale error


          //Se conecta con la base de datos
          include("database/connect-database.php");

          //Instrucción
          $sql = "SELECT username, password FROM users WHERE username='". $_POST["username"] . "' AND password=MD5('" . $_POST["password"] . "')";
          //Un resultado
          $result = mysqli_query($conn, $sql);

          if (mysqli_num_rows($result) > 0) {
            //Se establece la variable de la sesión y se va a index.php
            $_SESSION['usuario'] = $_POST["username"];
            header("location: ../WEB");
          } else {
            //Si los datos son incorrectos
            echo "<center><div style=\"color: #060606;
            font-size: 15px;
            margin-bottom: 14px;
            border-bottom: 3px solid #454545;
            border-top: 3px solid #454545;
            padding: 5px;\">-- Your username and password don't match --</div></center>";
          }

          //La conexión se cierra
          mysqli_close($conn);

        }
        ?>
        <!-- Fin -->


        <form action="<?php $_PHP_SELF ?>" method="post">
          <input type="text" id="username" name="username" placeholder="Username..." required/>
          <input type="password" id="password" name="password" placeholder="Password..." required/>
          <button type="submit">Log In</button>
        </br>
      </br>
      <span style="text-align: right">Don't have an account yet? <a href="signup.php">Sign Up!</a></span>
    </form>
  </div>


</div>
  <?php include 'parts/creditos.html';?>
</body>
</html>
