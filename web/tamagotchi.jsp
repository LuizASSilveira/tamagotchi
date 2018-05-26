<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- esta linha abaixo é para fazer o conteudo se adaptar a tela, tanto do computador quanto do celular -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />

        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/game.css">
        <title>Tamagotchi</title>
    </head>
    <body>

        <%
            Connection conn = null;
            ResultSet result = null;
            Statement stmt = null;
            ResultSetMetaData rsmd = null;

            try{
                Class c = Class.forName("org.postgresql.Driver");
            } catch (Exception ex) {
                System.out.println("Error na conexao: " + ex);
            }

            try{
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lp", "postgres", "postgresAdmin");
            } catch (Exception ex) {
                System.out.println("Erro ao conectar: " + ex);
            }

            try{
                stmt = conn.createStatement();
                result = stmt.executeQuery("SELECT nome from pet where id = 1;");
                result.next();
                System.out.println(result.getString("nome"));
                String name = result.getString("nome");
                request.setAttribute("name", name);
            } catch (Exception ex) {
                System.out.println("Erro ao executar o select: " + ex);
            } finally {
                stmt.close();
                conn.close();
            }
        %>
        <!-- tudo deve estar dentro desta classe container-fluid -->
        <div class="container-fluid" id="telaTama">
            
            <div id="menu">
                <div class = "status">${name}
                    <div class="progress">
                    <div id="felicidade" class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 75%"></div>
                    </div>

                </div>

                <div class = "status"> Vida
                    <div class="progress">
                    <div id="vida" class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 75%"></div>
                    </div>

                </div>

                <div class = "status">Fome
                    <div class="progress">
                    <div id="fome" class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 75%"></div>
                    </div>
                </div>
            </div>
        
        <div id = "Personagem">
            




        </div>

            <div id="menuActions">
                <div id="actions">
                  
                    <button type="button" class="btn btn-outline-dark">Alimentar</button>
                    <button type="button" class="btn btn-outline-dark">Banheiro</button>
                    <button type="button" class="btn btn-outline-dark">Jogar</button>
                    <button type="button" class="btn btn-outline-dark">Curar</button>
                    <button type="button" class="btn btn-outline-dark">Luzes</button>

                </div>
            </div>
        </div>
    </body>
</html>