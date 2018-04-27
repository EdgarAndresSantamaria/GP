<?php
$servername = "galan.ehu.eus";
$username = "Xpdejaime001";
$password = "AIC7nDBJ";
$database = "Xpdejaime001_pong";

// La conexión se crea
$conn = mysqli_connect($servername, $username, $password, $database);

// Se comprueba si se ha producido algún error
if (!$conn){
  die(header("location: error-database.php"));
}
?>
