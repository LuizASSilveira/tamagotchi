<%--
    Document   : register
    Created on : 17/05/2018, 01:51:35
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/styles.css">
        <title>Tamagotchi criar</title>
    </head>

    <body>
        <div class="login">
            <p class="Cadastro">Cadastro</p>

            <form method="POST" action="Requisicao">
                Nome de usuario<br>
                <input type="text" class="Nome" name="username"><br>
                Senha<br>
                <input type="password" class="login" name="password"><br>
                <br>
                <input type="submit" value="Criar" class="buttomCadastro">
            </form>
        </div>
    </body>
</html>
