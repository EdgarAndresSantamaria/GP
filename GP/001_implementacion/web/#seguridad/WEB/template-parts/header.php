<!-- Se añade la codificación de la web -->
<?php header('Content-Type: text/html; charset=UTF-8'); ?>

<!-- Se añade el css -->
<link rel="stylesheet" href="style.css">   

<!-- Se añaden las fuentes a utilizar -->
<link href="https://fonts.googleapis.com/css?family=Roboto:400,400i,500,500i,700,700i|Merriweather:400,400i,700,700i|Kalam:400,700|Amatic+SC:400,700" rel="stylesheet" /> 

<!-- Se añade el fichero javascript con las funciones para la comprobación de datos -->
<script type="text/javascript" src="funciones.js"></script> 

   </HEAD>
   <BODY>

      <!-- Se añade el top menú -->
      <div class="topMenuWrapper">
      	<div class="topMenuItems">
      		<a href="../WEB">Inicio</a>
      		<a href="">Sobre Nosotros</a> 
      		<a href="">Contacto</a>
            <!-- Si hay un usuario identificado le saldrán algunas opciones más en el menú -->
            <?php
            if(isset($_SESSION['usuario'])){
               echo "<a href=\"usuario.php\">Mi Cuenta</a>";
               echo "<a href=\"logout.php\">Cerrar Sesión</a>";
            }
            ?>
      	</div>
      </div>
      <!-- Fin Menú -->

<!-- Se añade el logo -->
<div class="logo">
   <a href="../WEB"><img src="images/logo.png" /></a>
</div>
<!-- Fin -->