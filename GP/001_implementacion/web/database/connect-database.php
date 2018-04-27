<?php
$servername = "localhost";
$user = "Xpdejaime001";
$pass = "AIC7nDBJ";
$database = "Xpdejaime001_pong";

// La conexión se crea
$conn = mysqli_connect($servername, $user, $pass, $database);

// Se comprueba si se ha producido algún error
if (!$conn){
  die(header("location: error-database.php"));
}
?>
