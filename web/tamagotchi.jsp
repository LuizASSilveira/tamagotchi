<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="Controller.VPet"%>
<%@page import="Model.DAO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- esta linha abaixo é para fazer o conteudo se adaptar a tela, tanto do computador quanto do celular -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />

        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <link   rel="stylesheet" href="css/bootstrap.min.css">
        <link  rel="stylesheet" href="css/game.css">

    <audio style="display:none"  controls autoplay loop  >
        <source  onplaying="true " src="photos/music.mp3" type="audio/mpeg">
    </audio>

    <title>Tamagotchi</title>
</head>
<body>

    <%
        try {
            Cookie[] cookie = request.getCookies();
            DAO dao = new DAO("lp", "usuario", "pet", "postgres", "root");
            ResultSet result = dao.getCommand("SELECT * from pet where id = " + cookie[1].getValue() + ";");
            result.next();
            VPet pet = new VPet(result);
            System.out.println("Passou daqui");

            result = dao.getCommand("SELECT * from pet where id = " + cookie[1].getValue() + ";");
            result.next();
            
            
            switch(result.getString("status")){
                case "NORMAL":
                    request.setAttribute("personagem", "photos/novoP1.gif");
                    break;

                case "DORMINDO":
                    request.setAttribute("personagem", "photos/novoP3.gif");
                break;

                 case "MORTO":
                    request.setAttribute("personagem", "photos/novoP4.gif");
                break;

                 case "TRISTE":
                    request.setAttribute("personagem", "photos/novoP5.gif");
                break;

                 case "CANSADO":
                    request.setAttribute("personagem", "photos/novoP6.gif");
                break;
            }

            request.setAttribute("disabled", (result.getString("status").equals("MORTO") ? "disabled" : ""));
            request.setAttribute("liveForever", (result.getString("status").equals("MORTO") ? "" : "none"));
            request.setAttribute("pet", (result.getBoolean("lampada") ? "" : "black"));
            request.setAttribute("cor", (result.getBoolean("lampada") ? "" : "rgba(15, 15, 10, 0.9)"));
            request.setAttribute("felicidade", result.getInt("felicidade"));
            request.setAttribute("status", result.getString("status"));
            request.setAttribute("saude", result.getInt("saude"));
            request.setAttribute("fome", result.getInt("fome"));

        } catch (Exception ex) {
            System.out.println("Erro a pagina tamagotchi: " + ex);
        }
    %>



    <!-- tudo deve estar dentro desta classe container-fluid -->
    <div class="container-fluid" id="telaTama">
        <button style="float:right; margin-top: 4.5%; width: 10%;height: 5%;" id="voltar" type="button" onclick=location.href="http://localhost:8084/tamagotchi/colecao.jsp" class="btn btn-warning">Voltar</button>
        <div id="menu">
            <div class = "status"> Felicidade
                <div class="progress">
                    <div id="felicidade" class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width: ${felicidade}%"></div>
                </div>
            </div>

            <div class = "status"> Saude
                <div class="progress">
                    <div id="vida" class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width: ${saude}%"></div>
                </div>
            </div>

            <div class = "status">Fome
                <div class="progress">
                    <div id="fome" class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width: ${fome}%"></div>
                </div>
            </div>            
        </div>
        <div> 
            <h2 style="margin-left:45%; margin-bottom: -3%; color: goldenrod ; font-weight: bold;">${status}</h2><br><br>
            <form action="Requisicao" method="POST">
                <input style="margin-left: 42%; display: ${liveForever};" type="submit" name="Reviver" class="btn btn-info" value="REVIVER"/>
            </form>
        </div>

        <div id = "Personagem" style=" background-color: ${cor};">
            
            <img id="imgTama" src="${personagem}" with="400" height="300"/>
            
        </div>
        <div id="menuActions">
            <div id="actions">
                <form action="Requisicao" method="POST">
                    <input type="submit" name="Alimentar"   class="btn btn-dark" value="Alimentar"  ${disabled} />
                    <input type="submit" name="Banheiro"    class="btn btn-dark" value="Banheiro"   ${disabled}/>
                    <input type="submit" name="Jogar"       class="btn btn-dark" value="Jogar"      ${disabled}/>
                    <input type="submit" name="Curar"       class="btn btn-dark" value="Curar"      ${disabled}/>
                    <input type="submit" name="Luzes"       class="btn btn-dark" value="Luzes"      ${disabled}/>
                </form>
            </div>
        </div>
    </div>
    <script>
        setTimeout(function () {
            location.reload();
        }, 15000);
    </script>
</body>
</html>