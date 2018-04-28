<!-- Se añade la codificación de la web -->
<?php header('Content-Type: text/html; charset=UTF-8'); ?>

<div id="menu">
  <div class="topnav" id="myTopnav">
    <a href="index.php" class="active">Home</a>

    <!-- Si el usuario no está identificado -->
    <?php
    if(!isset($_SESSION['usuario'])){
       echo "<a href=\"login.php\">Log In</a>";
       echo "<a href=\"signup.php\">Sign Up</a>";
    }
    ?>

    <!-- Si el usuario está identificado -->
    <?php
    if(isset($_SESSION['usuario'])){
      if($_SESSION['usuario'] == "admin"){ // Si el usuario además es admin
         echo "<a href=\"clientArea.php\">Client Area</a>";
      }
       echo "<a href=\"logout.php\">Log Out</a>";
    }
    ?>

  </div>
</div>
