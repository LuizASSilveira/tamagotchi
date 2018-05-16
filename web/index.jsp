<%-- 
    Document   : index
    Created on : 16/05/2018, 12:12:33
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- esta linha abaixo é para fazer o conteudo se adaptar a tela, tanto do computador quanto do celular -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />

        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styles.css">
        <title>JSP Page</title>
    </head>
    <body>
        <!-- tudo deve estar dentro desta classe container-fluid -->
        <div class="container-fluid">
            <div>
                <p class="titleTamagotchi">Tamagotchi LP</p>
            </div>

            <div class="login">
                <p>Login</p>
                <form method="POST" action="Login">
                    Digite o login:<br>
                    <input type="text" class="login" name="login">
                        <br>
                    Digite a senha:<br>
                    <input type="password" class="login" name="password">
                    <br>
                    <input type="submit" value="Login" class="buttomLogin">
                </form>
            </div>
        </div>
    </body>
</html>
