<!-- Se empieza una sesión -->
<?php
session_start();
?>
<!DOCTYPE html>
<html>
 <HEAD>
      <TITLE>Logout</TITLE>
</HEAD>
<body>

<?php
// borrar variables de la sesión
session_unset();

// cerrar la sesión
session_destroy();

//se carga la página principal
header("location: ../WEB");
?>

  <?php include 'parts/creditos.html';?>
</body>
</html>
