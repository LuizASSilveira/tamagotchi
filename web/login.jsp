
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
        <title>Login Tamagotchi</title>
    </head>
    <body>
        <!-- tudo deve estar dentro desta classe container-fluid -->
        <div class="container-fluid">
            
            <div class="login">
                <p class="titleTamagotchi">Login</p>
                <form method="POST" action="Requisicao">
                    <br>
                    Nome<br>
                    <input type="text" class="login" name="login">
                    <br>    
                    Senha<br>
                    <input type="password" class="login" name="password">
                    <br>
                    <input type="submit" value="Entrar" name="botaoLogin" class="buttomLogin">
                </form>    
                <a href="register.jsp">
                    <button class="buttomGoCadastro">Cadastrar</button>
                </a>
            </div>
        </div>
    </body>
</html>
