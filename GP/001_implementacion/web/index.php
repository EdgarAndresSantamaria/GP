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
    <div id="left">
      <div class="containerHome">
        <!-- Si el usuario no está identificado -->
        <?php
        if(!isset($_SESSION['usuario'])){
          ?>
          <h2>SIGN UP AND PLAY!</h2>
          <img src="images/pong.png"/>
          <br />
          <br />
          <button onclick="window.location.href='signup.php'">Sign up </button>
          <button onclick="window.location.href='login.php'">Log In </button>

          <?php
        }else{
          ?>
          <h2>PLAY NOW!</h2></br/>
          <img src="images/pong.png"/>
          <br />
          <br />
          <button id="botonDescarga" type="window.location.href='linkDescargaDirecta'" >Download </button>

          <div id="anuncio1" class="espacioPublicitario mediano" style="display:none;">advertisement 262x168</div>
          <div id="anuncio2" class="espacioPublicitario mediano" style="display:none;">advertisement 262x168</div>
          <?php
        }
        ?>
      </div>
    </div>

    <div id="right">

    </div>
    <?php include 'parts/creditos.html';?>
  </div>

  <script type="text/javascript" src="js/main.js"></script>
</body>
</html>
