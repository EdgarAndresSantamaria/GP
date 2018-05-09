  <?php
  //Se conecta con la base de datos
          include("../database/connect-database.php");

if( isset($_POST["jugador1"])) {

 //Instrucción
    $sql= "INSERT INTO puntuaciones(jugador1,jugador2,puntuacion1,puntuacion2) VALUES ('".$_POST["jugador1"]."','".$_POST["jugador2"]."', '".$_POST["puntuacion1"]."','".$_POST["puntuacion2"]."')";

    if (mysqli_query($conn, $sql)){
      echo "true";
    } else {
      //Si el usuario ya existe sale un mensaje por pantalla
       if (mysqli_errno($conn) == 1062){ //Usuario ya en el sistema

            echo "false";

       }
    }

     //La conexión se cierra
    mysqli_close($conn);

}else{
          
          //Instrucción
          $sql = "SELECT jugador1, jugador2, puntuacion1, puntuacion2 FROM puntuaciones";
         
         //Un resultado
          $result = mysqli_query($conn, $sql);

          $list = array();
          if (mysqli_num_rows($result) > 0) {
            while ($row = mysqli_fetch_assoc($result)) {
                
                
                    $list[] = array('jugador1' => $row["jugador1"], 'jugador2' => $row["jugador2"], 'puntuacion1' => $row["puntuacion1"], 'puntuacion2' => $row["puntuacion2"]);
                }
              
            echo json_encode($list);
            
          } else {
            //Si no hay datos
            echo "false";
          }

          //La conexión se cierra
          mysqli_close($conn);
}
    
?>
