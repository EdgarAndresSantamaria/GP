<!DOCTYPE html>
<html>
<head>
  <title>Log In</title>

  <?php include 'parts/links.html';?>

</head>
<body>

  <header>

    <!-- Se anade el menu -->
    <?php include 'parts/menu.php';?>

  </header>

  <div id="main-container">
    <div id="left-panel">
      imagen
    </div>

    <div id="right-panel">

      <div class="formulario">
        <form action="/my-handling-form-page" method="post">
          <input type="text" id="username" placeholder="Username..." required/>
          <input type="password" id="password" placeholder="Password..." required/>
          <button type="submit">Log In</button>
        </br>
      </br>
      <span style="text-align: right">Don't have an account yet? Sign Up!</span>
    </form>
  </div>


</div>

</body>
</html>
