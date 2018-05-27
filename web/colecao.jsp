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
            result = stmt.executeQuery("SELECT id, nome, vida, dataCriacao from pet where dono = 'luiz';");
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
        <title>Coleção</title>
    </head>
    <body>
        <!-- tudo deve estar dentro desta classe container-fluid -->
        <div class="container-fluid">

            <h2> Coleção</h2>
            <!-- Button trigger modal -->
            <button id="botaoModal" type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                Novo
            </button>
            <table class="table">
                  <thead class="thead-dark">
                    <tr>
                      <th width="15%" scope="col">Pontuação</th>
                      <th width="30%"  scope="col">Bichinho</th>
                      <th width="30%"  scope="col">Estado</th>
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
                                    <td style="display:none" >${id}</td>
                                </tr>
                            <% } %>
                  </tbody>
                </table>
        </div>
                  
            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Novo Amigo</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                  <div class="modal-body">
                    De um nome pro seu amigo, Ótario..
                  <input placeholder="Nome" type="text" class="form-control" id="basic-url" aria-describedby="basic-addon3">  
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Sair</button>
                    <button type="button" class="btn btn-primary">Criar Amigo</button>
                  </div>
                </div>
              </div>
            </div>                  

    </body>
</html>
