<%@ page import = "java.util.*" %><?xml version="1.0" encoding="UTF-8"?>

<head>
    <meta http-equiv="Content-Type" content='text/html; charset=UTF-8'/>
     <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <title>Acerte o Numero</title>
</head>
<body>

    <h1>Acerte o Numero</h1>

    <div>
<%

    HttpSession Sess = request.getSession();
    boolean     start = Sess.isNew();
    Integer     Numero;
    ArrayList   Tentativas;

    if (start) {
        Numero = new Integer(new java.util.Random().nextInt(101));
        Tentativas = new ArrayList();

        Sess.setAttribute("no", Numero);
        Sess.setAttribute("hist", Tentativas);

    } else {

        Numero = (Integer) Sess.getAttribute("no");
        Tentativas = (ArrayList) Sess.getAttribute("hist");
    }

    final String            str = request.getParameter("chute");
    String                  error = null;
    int                     chute = -1;

    if (!start) {

        if (str != null && str.length() != 0) {
            try {
                chute = Integer.parseInt(str);
                if (chute < 0 || chute > 100)
                    error = "O valor precisa estar entre 0 e 100";
                else
                    Tentativas.add(new Integer(chute));

            } catch (NumberFormatException e) {
                error = "Digite um numero";
            }

        } else
            error = "Digite um valor";
    }

    if (chute != Numero) {
%>
        <div >
            <p>Chute um numero entre 0 e 100</p>
<%
        if (error != null) {
%>
            <div><%= error %></div>
<%
        }
%>
            <form method='post'>
                <label <%= error != null ? "class='bad-field'" : "" %> >Digite um numero: 
                    <input type='text' size='6' name='chute' 
                    <%= error != null ? "value='" + str + "'" : "" %> />
                </label>
                   <input type='submit' value='chute'/>
            </form>
        </div>
<%
    } else {
        Sess.invalidate();  //  destroi sessao
%>
        <div>
            <p>Acertou<%= Numero %>. 
            You chuteed it in <%= Tentativas.size() %> attempts.</p>

            <form method='post'>
                <input type='submit' value='Jogar de Novo'/>
            </form>
        </div>
<%
    }
    if (Tentativas.size() > 0) {
%>
        <div>
            <table class="table table-dark">
                <thead class="table-dark">
                    <tr>
                        <th>Numero.</th>
                        <th>chute</th> 
                        <th>resultado</th>
                    </tr>
                </thead>
                <tbody>
<%
        for (int Index = Tentativas.size() - 1; Index >= 0; Index--) {
            Integer     Prevchute = (Integer) Tentativas.get(Index);
            int         tentativa = Prevchute.compareTo(Numero);
            String      resultado = "Correto!";

            if (tentativa < 0)
                resultado = "Muito Baixo";
            else if (tentativa > 0)
                resultado = "Muito Alto";
%>
                    <tr>
                        <td><%= Index + 1 %></td> 
                        <td><%= Prevchute %></td> 
                        <td><%= resultado %></td>
                    </tr>
<%
        }
%>
                </tbody>
            </table>
        </div>
<%
    }
%>
    </div>

</body>
</html>
    