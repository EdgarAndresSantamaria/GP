<!DOCTYPE html>
<html>
<head>
  <title>Sign Up</title>

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
          <input type="email" id="email" placeholder="Email..." required/>
          <input type="password" id="password" placeholder="Password..." required/>
          <button type="submit">Sign Up</button>
        </br>
      </br>
      <span style="text-align: right">Already signed up? Log In!</span>
    </form>
  </div>


</div>

</body>
</html>
