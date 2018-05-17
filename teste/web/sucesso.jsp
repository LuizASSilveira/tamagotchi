<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="pt">
<head>
<title>Página de Sucesso</title>
</head>
<body>
<H1>Mensagem JSTL:	<c:out value="${mensagem}" /></H1>	
<jsp:include page="alunoLista.jsp"/>	
</body>
</html>