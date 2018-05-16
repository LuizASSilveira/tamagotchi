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
        <link rel="stylesheet" href="styles.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <p class="titleTamagotchi">Game Tamagotchi Linguagem de Programação</p>
        </div>

        <div class="login">
            <p>Login</p>
                <hr>
            <form>
                Digite o login:<br>
                <input type="text" class="login" name="login">
                    <br>
                Digite a senha:<br>
                <input type="password" name="password">
                <br>
                <input type="submit" value="Login" class="buttomLogin">
            </form>
        </div>
    </body>
</html>
