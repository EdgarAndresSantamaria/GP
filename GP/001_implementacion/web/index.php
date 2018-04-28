<!DOCTYPE html>
<!-- Se empieza una sesión -->
<?php
session_start();
?>

<html>
<head>
<title>Home</title>
    <?php include 'parts/links.html';?>
</head>

<body>

  <header>

    <!-- Se anade el menu -->
    <?php include 'parts/menu.php';?>

  </header>

<div id="main-container">
  <div id="left"
    <div class="formularioHome">
      <form action=" " method="post">
        SIGN UP AND PLAY!</br/>
        PLAY NOW!</br/>
        Escribir algo</br/>
        <button type="submit" >Sign up </button> </br/> </br/>
        <button type="submit" >Download </button>

      </form>
    </div>

    <div id="right">

  </div>
</div>
  <?php include 'parts/creditos.html';?>
</body>
</html>
