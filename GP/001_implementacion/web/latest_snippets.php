<?php
// cabecera de la pagina
include "includes/header.php";
require_once "includes/utilidades.php";

?>

<h1>Latest Snippets</h1>

<?php

if (isset($_SESSION["currentUser"])) {
	$sql = "SELECT f_ult_mod, titulo, id, autor FROM articulo
        ORDER BY f_ult_mod DESC
        LIMIT 30";

CreateSnippetTable($sql);
} else{
	echo 'Acceso no permitido. Necesita estar registrado';
}


// pie de pagina
include "includes/footer.php";
?>
