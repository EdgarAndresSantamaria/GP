<?php
$servername = "localhost";
$username = "Xelopez109";
$password = "";
$database = "Xelopez109_serieously";

// La conexión se crea
$conn = mysqli_connect($servername, $username, $password, $database);

// Se comprueba si se ha producido algún error
if (!$conn){
  die(header("location: error-database.php"));
}
?>