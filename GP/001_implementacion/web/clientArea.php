<!DOCTYPE html>

<!-- Se empieza una sesión -->
<?php
session_start();
?>

<!-- Si el usuario no está identificado y no es admin no puede ver -->
<?php
if(!isset($_SESSION["usuario"]}
header("location: ../WEB");
}else if(isset($_SESSION["usuario"]) && $_SESSION["usuario"] != "admin"){
  header("location: ../WEB");
}
?>

<html>
<head>
  <title>Client Area</title>
  <?php include 'parts/links.html';?>
</head>

<body>

  <header>

    <!-- Se anade el menu -->
    <?php include 'parts/menu.php';?>

  </header>

  <?php
  //Se conecta con la base de datos
  include("database/connect-database.php");
  //Instrucción
  $sql = "SELECT username, email FROM users";
  //Un resultado
  $result = mysqli_query($conn, $sql);

  if (mysqli_num_rows($result) > 0) {
    echo $result
  } else {
    //Si no hay datos
    echo "No data yet :()";
    ?>

  </body>
  </html>
