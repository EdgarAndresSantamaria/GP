<!DOCTYPE html>

<!-- Se empieza una sesión -->
<?php
session_start();
?>

<!-- Si el usuario no está identificado y no es admin no puede ver -->
<?php
if(!isset($_SESSION["usuario"])){
  header("location: ../WEB");
}else if(isset($_SESSION["usuario"]) && $_SESSION["usuario"] != "admin"){
  header("location: ../WEB");
}
?>

<html>
<head>
  <title>Client Area</title>
  <?php include 'parts/links.html';?>
</head>

<body>

  <header>

    <!-- Se anade el menu -->
    <?php include 'parts/menu.php';?>

  </header>

  <div class="main-container">

    <div class="table-container">
      <table width="100%">
        <tr>
          <th>Username</th>
          <th>Email</th>
        </tr>

        <?php

        //Se conecta con la base de datos
        include("database/connect-database.php");
        //Instrucción
        $sql = "SELECT username, email FROM users";
        //Un resultado
        $result = mysqli_query($conn, $sql);

        if (mysqli_num_rows($result) > 0) {
          while ($row = mysqli_fetch_assoc($result)) {
            echo "<tr>";
            echo "<td>" . $row["username"] . "</td>";
            echo "<td>" . $row["email"] . "</td>";
            echo "</tr>";
          }
        } else {
          //Si no hay datos
          echo "No data yet :()";
        }

        //La conexión se cierra
        mysqli_close($conn);
        ?>

      </table>

    </div>
  </div>

</body>
</html>
