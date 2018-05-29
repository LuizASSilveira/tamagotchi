<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.*"%>
<%@page import="Model.DAO"%>
<!DOCTYPE html>

    <%!
        public void novoPet(){
            System.out.println("Entrou aqui");
            //String nomePet = request.getParameter("nomePet");
            //System.out.println("Nome do pet: " + nomePet);
        }
        public long tempoVida(long criacao){
            long agora = System.currentTimeMillis();
            long segundosAtual = agora / 1000;
            long minutosAtual = segundosAtual / 60;
            segundosAtual = segundosAtual % 60;
            long horasAtual = minutosAtual / 60;
            minutosAtual = minutosAtual % 60;

            long segundosCriacao = criacao / 1000;
            long minutosCriacao = segundosCriacao / 60;
            segundosCriacao = segundosCriacao % 60;
            long horasCriacao = minutosCriacao / 60;
            minutosCriacao = minutosCriacao % 60;

            try{
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                Date horaIni = sdf.parse(String.format ("%02d:%02d:%02d", horasCriacao, minutosCriacao, segundosCriacao));
                Date horaFim = sdf.parse(String.format ("%02d:%02d:%02d", horasAtual, minutosAtual, segundosAtual));

                double horaI = horaIni.getTime();
                double horaF = horaFim.getTime();

                System.out.println("fazendo isso: " + ((long) horaF - (long) horaI));
                return (long) horaF - (long) horaI;
            } catch (Exception ex) {
                System.out.println("Erro ao calcular pontuacao: " + ex);
                return -1L;
            }
        }
    
    %>

    <%
        ResultSet result = null;
        try{
            DAO dao = new DAO("lp", "usuario", "pet", "postgres", "root");
            result = dao.getCommand("SELECT id, nome, vida, dataCriacao from pet where dono = 'luiz';");
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
                        <th width="15%" scope="col">Tempo de Vida</th>
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
                            long agora = System.currentTimeMillis();
                            int id = result.getInt(1);

                            request.setAttribute("tempovida", tempoVida(dataCriacao));
                            request.setAttribute("nome", nome);
                            request.setAttribute("vida", vida);
                            request.setAttribute("id", id);
                    %>
                            <tr>
                                <td>${tempovida}</td>
                                <td>${nome}</td>
                                <td>${vida?"Vivo":"Morto"}</td>
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
                    De um nome pro seu amigo, Ótario..
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

    </body>
</html>
