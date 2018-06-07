<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.*"%>
<%@page import="Model.DAO"%>
<!DOCTYPE html>

<%!
    public String horasVividas(long tempoCriacao, long tempoUltimo){
    
        long milliseconds = tempoUltimo - tempoCriacao;
        int segundos = (int) milliseconds /1000;
        return Integer.toString(segundos);
    /*
        int horas = segundos / 3600;
        int minutos = (segundos % 3600) / 60;
        segundos = (segundos % 3600) % 60;

        return Integer.toString(horas) + ":" + Integer.toString(minutos) + ":" + Integer.toString(segundos);*/
    }
%>

    <%
        ResultSet result = null;
        try{
            String user = (String) session.getAttribute("usuario");
            DAO dao = new DAO("lp", "usuario", "pet", "postgres", "root");
            result = dao.getCommand("SELECT id, nome, status, dataCriacao, timeMorte from pet where dono = '" + user + "';");
        } catch (Exception ex) {
            System.out.println("Erro ao executar o select da pagina colecao: " + ex);
        }
        %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- esta linha abaixo é para fazer o conteudo se adaptar a tela, tanto do computador quanto do celular -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />

        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/jquery.cookie.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/colecao.css">
        <link href="https://fonts.googleapis.com/css?family=Bangers" rel="stylesheet"> 
        <title>Coleção</title>
    </head>
    <body>
        
        <div class="container-fluid">

            <h2> Coleção</h2>
            <!-- Button trigger modal -->
            
            <button style="display: block; margin-bottom: 1%; margin-top : 3%; float: left;" id="botaoModal" type="button" class="btn btn-dark" data-toggle="modal" data-target="#exampleModal">
                Novo
            </button>
            <button style="display: block; margin-bottom: 1%; float: right; margin-top : 3%; width: 15%;height: 5%;" id="voltar" type="button" onclick=location.href='http://localhost:8084/tamagotchi' class="btn btn-warning">SAIR</button>            
            <button style="display: block; margin-bottom: 1%; margin-right: 1%; margin-left: 1%; float: left; margin-top : 3%; width: 15%;height: 5%;" id="voltar" type="button" onclick=location.href='http://localhost:8084/tamagotchi/rank.jsp' class="btn btn-dark">Ranking</button>            
            
            <table class="table table-dark table-hover">
                <thead class="thead-dark">
                    <tr>
                        <th width="15%"  scope="col">Pontuação</th>
                        <th width="30%"  scope="col">Bichinho</th>
                        <th width="30%"  scope="col">Estado</th>
                        <th width="25%"  scope="col">Ecluir</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        while(result.next()){
                            int id = result.getInt(1);
                            String nome = result.getString(2);
                            String status = result.getString(3);
                            Timestamp dataCriacao = result.getTimestamp(4);
                            Timestamp timeMorte = result.getTimestamp(5);
                            Timestamp agora = new Timestamp(System.currentTimeMillis());

                            request.setAttribute("tempovida", horasVividas(dataCriacao.getTime(), (timeMorte==null?agora.getTime():timeMorte.getTime())));
                            request.setAttribute("nome", nome);
                            request.setAttribute("status", status);
                            request.setAttribute("id", id);
                    %>
                            <tr>
                                <td>${tempovida}</td>
                                <td>${nome}</td>
                                <td>${status}</td>
                            
                            <form action="Requisicao" method="POST">
                                <td>
                                    <input type="submit" name="Excluir" id="Remove" value="x"/>
                                </td>
                                
                            </form>
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
                <form method="POST" action="Requisicao">
                  <div class="modal-body">
                    De um nome para seu novo amigo.
                  <input name="nomePet" placeholder="Nome" type="text" class="form-control" id="basic-url" aria-describedby="basic-addon3">
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Sair</button>
                    <button type="submit" value="submit" class="btn btn-primary">Criar Amigo</button>
                  </div>
                </form>
                </div>
              </div>
            </div>                  

            <script>
                $('tr').dblclick(function(){
                    $.cookie('petId', $(this).children()[5].textContent);
                    window.location.href = "tamagotchi.jsp";
                });

                $('input').click(function () {
                    $.cookie('morte', $(this).parent().parent().children()[5].textContent);
                    console.log($(this).parent().parent().children()[5].textContent);
                });
                
            </script>
    </body>
</html>
