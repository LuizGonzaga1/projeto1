package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO; //para o Servlet utilizar o metodo teste conexao, � necess�rio importar essa classe. E tbm precisamos criar um objeto
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete" })
// Main � uma requisi��o que o Servlet ir� trabalhar.
// Inserindo o /insert a camada controller consegue
// receber os dados do formul�rio.
public class Controller extends HttpServlet { // Essa aqui � a classe principal do Servlet
	private static final long serialVersionUID = 1L;

	DAO dao = new DAO(); // usando como modelo a classe DAO, criamos um objeto de nome DAO
	JavaBeans produto = new JavaBeans(); // Esse objeto "produto", consegue acessar os m�todos p�blicos da classe
											// JavaBeans

	public Controller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// esse � o metodo principal do Servlet

		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// a ln acima foi feita s� para testar ds pode apagar

		String action = request.getServletPath();
		System.out.println(action); // Essa ln que esta printando os testes

		// Cirando uma estrutura do tipo if:
		if (action.equals("/main")) {
			produtos(request, response); // metodo "produtos"

			// se o conteudo da vari�vel action for igual a /main.
			// Em Java quando queremos fazer uma compara��o de strings utilizamos .equals ao
			// inves de ==
			// Se o metodo doGet receber a requesi��o /main vc ira redirecionar ao metodo
			// que ir� trabalhar especificamente essa requesi��o.

		} else if (action.equals("/insert")) { // Se o conteudo da variavel action agora for insert, vc ir�
												// redirecionarao ao metodo responsavel por encaminhar essa requesicao a
												// camada model, vamos chamar de novoProduto:
			novoProduto(request, response); // metodo "novoProduto"

		}

		else if (action.equals("/select")) {
			listarProduto(request, response);

		} else if (action.equals("/update")) {
			editarProduto(request, response);

		} else if (action.equals("/delete")) {
			excluirProduto(request, response);

		} else {
			response.sendRedirect("index.html"); // Se o servlet receber alguma requisi��o que ele nao conhece. Vamos
													// pedir para ele redirecionar para o documento index.html
		}

		/*
		 * //Fazendo o teste de conexao usando o objeto DAO: dao.testeConexao(); // o
		 * metodo testeConexao ir� imprimir a String de conexao se tudo estiver ok. E se
		 * der algum problema ele mostrar� qual ser� o erro.
		 */
	} // Final do metodo doGet

	// Listas Produtos
	protected void produtos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendRedirect("produtos.jsp");
		// Criando um objeto que ir� receber os dados JavaBeans e apaganado a linha
		// superior:
		ArrayList<JavaBeans> lista = dao.listarProdutos();
		// Explicando a ln de cima: tendo como referencia a classe modelo "ArrayList",
		// passando como parametro
		// a classe "JavaBeans", criei um objeto "lista", e esse objeto executa o m�todo
		// "listarProdutos()"", dentro da classe DAO. (� o passo 2)
		// lista � um vetor dinamico que esta recebendo todos os dados do banco de
		// dados.

		// teste de recebimento da lista:

		// for (int i = 0; i < lista.size(); i++) {
		// System.out.println(lista.get(i).getId());
		// System.out.println(lista.get(i).getCodigo());
		// System.out.println(lista.get(i).getNome());
		// System.out.println(lista.get(i).getCategoria());
		// System.out.println(lista.get(i).getValor());
		// System.out.println(lista.get(i).getQuantidade());
		// ap�s realizarmos o teste e ver que esta mandando os registros, podemos
		// apaga-las
		// }

		// Encaminhando a lista ao documento produtos.jsp
		request.setAttribute("produtos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("produtos.jsp");// � uma classe modelo que trabalha com
																			// requisi��es e respostas no Servlet
		rd.forward(request, response); // passo 7 da aula #12
	}

	// novo produto
	protected void novoProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendRedirect("produtos.jsp");

		// Fazendo um teste para verificar se o Servlet esta recebendo os dados do
		// formul�rio:
		// ESSAS 5 P�GINAS DE TESTE, PODEMOS APAGAR:
		// System.out.println(request.getParameter("codigo"));
		// System.out.println(request.getParameter("nome"));
		// System.out.println(request.getParameter("categoria"));
		// System.out.println(request.getParameter("valor"));
		// System.out.println(request.getParameter("quantidade"));

		// Setar as vari�veis do JavaBeans (passo5):
		// produto.setCodigo(request.getParameter("codigo"));

		produto.setCodigo(Integer.parseInt(request.getParameter("codigo")));

		// O objeto produto, consegue atraves do metodo "setCodigo", armazenar na
		// vari�vl c�digo o valor que ele est� recebendo do formul�rio de produto. Faz
		// isso para os demais dados

		produto.setNome(request.getParameter("nome"));

		produto.setCategoria(request.getParameter("categoria"));

		// produto.setValor(Integer.parseInt(request.getParameter("valor")));
		produto.setValor(Float.parseFloat(request.getParameter("valor").replaceAll(",", ".")));

		produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));

		// ---- INVOCAR O METODO inserirProduto passando o objeto contato
		dao.inserirProduto(produto);

		// Para finalizar o m�todo novoProduto, iremos fazer o redirecionar para o
		// documento produtos.jsp
		response.sendRedirect("main");

	}

	// Editar produto(1o envolve selecionar o contato que ser� alterado):
	protected void listarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Verificando se o botao "editar" esta mandando a requisi��o ao servlet

		// Recebimento do id do contato que ser� editado:
		int id = (Integer.parseInt(request.getParameter("id")));
		// System.out.println(id); teste para saber se o servlet esta recebendo o id do
		// produto

		// Setar a vari�vel JavaBeans
		produto.setId(id);

		// Executar o metodo selecionarProduto(DAO):
		dao.selecionarProduto(produto);

		// teste de recebimento
		// System.out.println(produto.getId());
		// System.out.println(produto.getCodigo());
		// System.out.println(produto.getNome());
		// System.out.println(produto.getCategoria());
		// System.out.println(produto.getValor());
		// System.out.println(produto.getQuantidade());

		// Setar os atributos do formulario com o conteudo JavaBeans - Passo 10 #13
		request.setAttribute("id", produto.getId()); // - Passo 9 #13 - "produto.getId()
		request.setAttribute("codigo", produto.getCodigo());
		request.setAttribute("nome", produto.getNome());
		request.setAttribute("categoria", produto.getCategoria());
		request.setAttribute("valor", produto.getValor());
		request.setAttribute("quantidade", produto.getQuantidade());

		// Encaminhando ao documento editar.jsp: - Passo 10 #13
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);

	}

	// Editar produto:
	protected void editarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// testando:
		// System.out.println(request.getParameter("id"));
		// System.out.println(request.getParameter("codigo"));
		// System.out.println(request.getParameter("nome"));
		// System.out.println(request.getParameter("categoria"));
		// System.out.println(request.getParameter("valor"));
		// System.out.println(request.getParameter("quantidade"));

		// setar as vari�veis Javabeans
		produto.setId(Integer.parseInt(request.getParameter("id")));
		produto.setCodigo(Integer.parseInt(request.getParameter("codigo")));
		produto.setNome(request.getParameter("nome"));
		produto.setCategoria(request.getParameter("categoria"));
		produto.setValor(Float.parseFloat(request.getParameter("valor").replaceAll(",", ".")));
		produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));

		// executar o m�todo alterarProduto:
		dao.alterarProduto(produto);
		// Redirecionando para o documento produto.jsp (atualizando as altera��es).
		response.sendRedirect("main");
	}

	// Excluir um contato:
	protected void excluirProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebendo o id do produto a ser exclu�do(validador.js)
		int id = (Integer.parseInt(request.getParameter("id")));
		// testando:
		// System.out.println(id);
		produto.setId(id); // Passo 3 da #14

		// Executar o metodo excluirProduto(DAO) passando o objeto produto como
		// parametro:
		dao.excluirProduto(produto);

		// Redirecionando para o documento produto.jsp (atualizando as altera��es).
		response.sendRedirect("main");

	}

}
