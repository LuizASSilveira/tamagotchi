<%-- 
    Document   : login
    Created on : 16/05/2018, 17:16:40
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
        <title>Tamagotchi</title>
    </head>

    <body>
        <!-- tudo deve estar dentro desta classe container-fluid -->
        <div class="container-fluid">
            <div class="row">
                <p class="titleTamagotchi">Tamagotchi LP</p>
            </div>

            <div class="row">
                <div class="col-xs-6">
                    <a href="login.jsp">
                       <img src="photos/login.png" title="Egg para login"/>
                    </a>
                </div>
                <div class="col-xs-6">
                    <a href="register.jsp">
                       <img src="photos/register.png" title="Egg para criar conta"/>
                    </a>
                </div>
            </div>
        </div>
    </body>
</html>