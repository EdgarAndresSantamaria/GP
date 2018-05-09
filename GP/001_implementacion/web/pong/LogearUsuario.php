<?php
if( isset($_POST["username"])) {


  //Se conecta con la base de datos
  include("../database/connect-database.php");

  //Instrucción
  $sql = "SELECT username, password FROM users WHERE username='". $_POST["username"] . "' AND password=MD5('" . $_POST["password"] . "')";
  //Un resultado
  $result = mysqli_query($conn, $sql);

  if (mysqli_num_rows($result) > 0) {
    //El usuario existe
    echo "true";
  } else {
    //el usuario no existe
    echo "false";
  }

  //La conexión se cierra
  mysqli_close($conn);

}
?>
<!-- Fin -->
