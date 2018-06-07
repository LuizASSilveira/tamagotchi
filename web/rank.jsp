<%@page import="java.util.Comparator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="Model.DAO"%>
<!DOCTYPE html>

<%!
    public Integer horasVividas(long tempoCriacao, long tempoUltimo){
    
        long milliseconds = tempoUltimo - tempoCriacao;
        int segundos = (int) milliseconds /1000;
        return segundos;
    }
%>

    <%
        System.out.println("Aquiiiiiiiiiiiiiiiiiiiiiii");
        ResultSet result = null;
        LinkedList<Integer> pontosGeral = new LinkedList<>();
        HashMap<Integer, String> pontosMapemaento = new HashMap<>();

        try{
            String user = (String) session.getAttribute("usuario");
            DAO dao = new DAO("lp", "usuario", "pet", "postgres", "root");
            result = dao.getCommand("SELECT nome, dono, dataCriacao, timeMorte from pet;");

            while(result.next()){
                
                String nome = result.getString(1);
                String dono = result.getString(2);
                Timestamp dataCriacao = result.getTimestamp(4);
                Timestamp timeMorte = result.getTimestamp(5);
                Timestamp agora = new Timestamp(System.currentTimeMillis());
                Integer pontos = horasVividas(dataCriacao.getTime(), (timeMorte==null?agora.getTime():timeMorte.getTime()));

                pontosGeral.add(pontos);
                pontosMapemaento.put(pontos, nome + "-" + dono);
            }

            pontosGeral.sort(new Comparator<Integer>(){
            @Override
            public int compare(Integer t, Integer t1) {
                return (t < t1) ? -1 : 1;
            }
        });
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
        <title>Ranking</title>
    </head>
    <body>
        <!-- tudo deve estar dentro desta classe container-fluid -->
        <button style="  margin-left: 80%; margin-top: -10%; margin-bottom: -10%; width: 10%;height: 5%;" id="voltar" type="button" onclick=location.href='http://localhost:8084/tamagotchi/colecao.jsp' class="btn btn-warning">Voltar</button>
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
                        System.out.println("Aqui");
                        for(Integer ponto : pontosGeral){
                            String[] palavra = pontosMapemaento.get(ponto).split("-");
                            System.out.println("Pontos: " + ponto);
                            request.setAttribute("ponto", ponto);
                            request.setAttribute("nome", palavra[0]);
                            request.setAttribute("dono", palavra[1]);

                    %>
                            <tr>
                                <td>${ponto}</td>
                                <td>${nome}</td>
                                <td>${dono}</td>
                            </tr>
                        <% } %>
                  </tbody>
                </table>
        </div>
    </body>
</html>
