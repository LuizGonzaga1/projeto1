<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Editar Produto</title>
<link rel="icon" href="imagens/produtos.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<img src="imagens/store.png">
	<h1>Editar Produto</h1>
	<form name="frmProduto" action="update">
		<!-- Enviando essa requesição a classe controller -->
		<table>
			<tr>
				<td><input type="text" name="id" id="caixa3" readonly
					value="<%out.print(request.getAttribute("id"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="codigo" class="Caixa1"
					value="<%out.print(request.getAttribute("codigo"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="Caixa1"
					value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>

			<tr>
				<td><input type="text" name="categoria" class="Caixa1"
					value="<%out.print(request.getAttribute("categoria"));%>"></td>
			</tr>

			<tr>
				<td><input type="text" name="valor" class="Caixa2"
					value="<%out.print(request.getAttribute("valor"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="quantidade" class="Caixa1"
					value="<%out.print(request.getAttribute("quantidade"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="Botao1" onclick="validar()">

	</form>

	<script src="scripts/validador.js"></script>

</body>
</html>
