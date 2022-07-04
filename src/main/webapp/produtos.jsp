<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>

<%
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("produtos");
  
	/* TESTANTO SE A CAMADA CONTROLER ESTA ENVIANDO PARA PRODUTOS.JSP
	for (int i=0; i<lista.size(); i++){
		out.println(lista.get(i).getId());
		out.println(lista.get(i).getCodigo());
		out.println(lista.get(i).getNome());
		out.println(lista.get(i).getCategoria());
		out.println(lista.get(i).getValor());
		out.println(lista.get(i).getQuantidade());
		
	}*/
%>

<!DOCTYPE html>
<html lang="pr-br">

<head>
<meta charset="utf-8">
<title>Lista de Produtos</title>
<link rel="icon" href="imagens/produtos.png">
<link rel="stylesheet" href="style.css">
</head>


<body>
	<h1>Lista de Produtos</h1>
	<a href="novo.html" class="Botao1">Novo Produto</a>
	
	<table id="tabela">
		<thead>
				<tr>
						<th>Id</th>
						<th>Codigo</th>
						<th>Nome</th>
						<th>Categoria</th>
						<th>Valor</th>
						<th>Quantidade</th>
						<th>Opções</th>
				</tr>
		</thead>
		<tbody>
				<%for (int i = 0; i <lista.size(); i++) { %>
					<tr>
						<td><%=lista.get(i).getId() %></td>
						<td><%=lista.get(i).getCodigo() %></td>
						<td><%=lista.get(i).getNome() %></td>
						<td><%=lista.get(i).getCategoria() %></td>
						<td><%=lista.get(i).getValor() %></td>
						<td><%=lista.get(i).getQuantidade() %></td>
						<td>
							<a href="select?id=<%=lista.get(i).getId()%>" class="Botao1">Editar</a>
							<a href="javascript: confirmar(<%=lista.get(i).getId()%>)" class="Botao2">Excluir</a>
						</td>
					</tr>
					
				<% } %>
		</tbody>
			
	</table>
	<script src="scripts/confirmador.js"></script>
			
	
</body>

</html>