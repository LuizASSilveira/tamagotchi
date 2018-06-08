
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="Controller.VPet"%>
<%@page import="Model.DAO"%>

<%
    try{
        Cookie[] cookie = request.getCookies();
        DAO dao = new DAO("lp", "usuario", "pet", "postgres", "root");
        ResultSet result = dao.getCommand("SELECT qtdtoques from pet where id = " + cookie[1].getValue() +";");
        result.next();

        request.setAttribute("qtdtoques", result.getInt("qtdtoques"));
        
    } catch (Exception ex) {
        System.out.println("Erro ao executar o select a pagina tamagotchi: " + ex);
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />

        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/game_1.css">
        <title>OVO</title>
    </head>

    <body>
        <button id="voltar" type="button" onclick=location.href='http://localhost:8084/tamagotchi/tamagotchi.jsp' class="btn btn-warning">Voltar</button>
        
        <h1>Placar: ${ qtdtoques } </h1>
        <div id = "OVOClick" class="zoom">
            <img id="ovo" src="photos/ovoClick.png" class="img-responsive"  alt="" width="390" height="495" />
        </div>
    <script>
        $('body').on('click','img',function(){
            console.log(${ qtdtoques = qtdtoques -  1  });

            <%
                try{
                    Cookie[] cookie = request.getCookies();
                    DAO dao = new DAO("lp", "usuario", "pet", "postgres", "root");
                    ResultSet result = dao.getCommand("SELECT qtdtoques, felicidade, fome from pet where id = " + cookie[1].getValue() +";");
                    result.next();

                    int qtd = result.getInt("qtdtoques");
                    int felicidade = result.getInt("felicidade");
                    int fome = result.getInt("fome");
                    System.out.println("Qtd: " + qtd + " felicidade: " + felicidade);
                    qtd --;
                    felicidade += 3;
                    fome --;

                    dao.getCommand("update pet set fome = " + (fome<0?0:fome) + ", qtdtoques = " + qtd + ", felicidade = " + (felicidade>100?100:felicidade) + " where id = " + cookie[1].getValue() +";");
                } catch (Exception ex) {
                    System.out.println("Erro ao executar o select a pagina tamagotchi: " + ex);
                }
            %>
            location.reload();                  
        })
    </script>    
    </body>
</html>