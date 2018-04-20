<div class="nav_body">
    <header class="nav_header bg-dark">
        <div class="nav_container">
            <!-- <a href="index.php">
                <img src="img/logorandom.png" alt="logo" class="logo">
            </a> -->
            <nav>
                <ul class="nav_ul">
                    <a href="index.php"><li class="no-padding logo"><i class="material-icons logo-icon">receipt</i>Snippedia</li></a>
                    <?php
                        session_start();

                        $param = session_get_cookie_params();
                        setcookie("PHPSESSID", session_id(), 0, $param["path"], $param["domain"], true, true);

                        if(isset($_SESSION["time"])){
                            if (time() > $_SESSION["time"]+60)
                            {
                                session_destroy();
                                header("location: index.php");
                            }
                        }

                        if (isset($_SESSION["currentUser"])) {
                            // si hay sesión iniciada mostramos enlace al perfil
                            $name = $_SESSION["currentUserName"];
                            echo("<a href='profile.php'><li>$name</li></a>");
                        } else {
                            // si no, mostramos enlace al login y al signup
                            echo("<a href='formulario_login.php'><li>Login</li></a>");
                            echo("<a href='formulario_registro.php'><li>Sign up</li></a>");
                        }
                     ?>
                    <a href="latest_snippets.php"><li>Latest Snippets</li></a>
                    <a href="formulario_post.php"><li>Submit Snippet!</li></a>
                </ul>
                <form id="nav_search" action="search_snippet.php" method="get" target"_blank" class="form-inline">
                    <input class="form-control" type="search" placeholder="Search" name="search_term" id="search_term" aria-label="Search">
                    <a class="btn btn-info" onclick="document.getElementById('nav_search').submit()"><i class="material-icons">search</i></a>
                </form>
            </nav> 
        </div>
    </header>
</div>
