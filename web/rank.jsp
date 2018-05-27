<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>

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
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lp", "postgres", "root");
        } catch (Exception ex) {
            System.out.println("Erro ao conectar: " + ex);
        }

        try{
            stmt = conn.createStatement();
            result = stmt.executeQuery("SELECT id, nome, vida, dataCriacao from pet;");
        } catch (Exception ex) {
            System.out.println("Erro ao executar o select: " + ex);
        }
        %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- esta linha abaixo é para fazer o conteudo se adaptar a tela, tanto do computador quanto do celular -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />

        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/colecao.css">
        <link href="https://fonts.googleapis.com/css?family=Bangers" rel="stylesheet"> 
        <title>Ranking</title>
    </head>
    <body>
        <!-- tudo deve estar dentro desta classe container-fluid -->
        <div class="container-fluid">

            <h2>Word Ranking</h2>
            <table class="table">
                  <thead class="thead-dark">
                    <tr>
                      <th width="20%" scope="col">Pontuação</th>
                      <th width="40%"  scope="col">Bichinho</th>
                      <th width="40%"  scope="col">Estado</th>
                      
                    </tr>
                  </thead>
                  <tbody>
                      <%
                            while(result.next()){
                                String nome = result.getString(2);
                                boolean vida = result.getBoolean(3);
                                long dataCriacao = result.getLong(4);
                                int id = result.getInt(1);
                                request.setAttribute("dataCriacao", dataCriacao);
                                request.setAttribute("nome", nome);
                                request.setAttribute("vida", vida);
                                request.setAttribute("id", id);
                                %>
                                <tr>
                                    <td>${dataCriacao}</td>
                                    <td>${nome}</td>
                                    <td>${vida}</td>
                                    <td>${id}</td>
                                </tr>
                            <% } %>
                  </tbody>
                </table>
        </div>
    </body>
</html>
