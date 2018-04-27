<!-- Se empieza una sesión -->
<?php
session_start();
?>
<!DOCTYPE html>
<html>
 <HEAD>
      <TITLE>Cerrar Sesión| Serieously</TITLE>
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


</body>
</html>