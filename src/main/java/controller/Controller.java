package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO; //para o Servlet utilizar o metodo teste conexao, é necessário importar essa classe. E tbm precisamos criar um objeto
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete" })
// Main é uma requisição que o Servlet irá trabalhar.
// Inserindo o /insert a camada controller consegue
// receber os dados do formulário.
public class Controller extends HttpServlet { // Essa aqui é a classe principal do Servlet
	private static final long serialVersionUID = 1L;

	DAO dao = new DAO(); // usando como modelo a classe DAO, criamos um objeto de nome DAO
	JavaBeans produto = new JavaBeans(); // Esse objeto "produto", consegue acessar os métodos públicos da classe
											// JavaBeans

	public Controller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// esse é o metodo principal do Servlet

		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// a ln acima foi feita só para testar ds pode apagar

		String action = request.getServletPath();
		System.out.println(action); // Essa ln que esta printando os testes

		// Cirando uma estrutura do tipo if:
		if (action.equals("/main")) {
			produtos(request, response); // metodo "produtos"

			// se o conteudo da variável action for igual a /main.
			// Em Java quando queremos fazer uma comparação de strings utilizamos .equals ao
			// inves de ==
			// Se o metodo doGet receber a requesição /main vc ira redirecionar ao metodo
			// que irá trabalhar especificamente essa requesição.

		} else if (action.equals("/insert")) { // Se o conteudo da variavel action agora for insert, vc irá
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
			response.sendRedirect("index.html"); // Se o servlet receber alguma requisição que ele nao conhece. Vamos
													// pedir para ele redirecionar para o documento index.html
		}

		/*
		 * //Fazendo o teste de conexao usando o objeto DAO: dao.testeConexao(); // o
		 * metodo testeConexao irá imprimir a String de conexao se tudo estiver ok. E se
		 * der algum problema ele mostrará qual será o erro.
		 */
	} // Final do metodo doGet

	// Listas Produtos
	protected void produtos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendRedirect("produtos.jsp");
		// Criando um objeto que irá receber os dados JavaBeans e apaganado a linha
		// superior:
		ArrayList<JavaBeans> lista = dao.listarProdutos();
		// Explicando a ln de cima: tendo como referencia a classe modelo "ArrayList",
		// passando como parametro
		// a classe "JavaBeans", criei um objeto "lista", e esse objeto executa o método
		// "listarProdutos()"", dentro da classe DAO. (é o passo 2)
		// lista é um vetor dinamico que esta recebendo todos os dados do banco de
		// dados.

		// teste de recebimento da lista:

		// for (int i = 0; i < lista.size(); i++) {
		// System.out.println(lista.get(i).getId());
		// System.out.println(lista.get(i).getCodigo());
		// System.out.println(lista.get(i).getNome());
		// System.out.println(lista.get(i).getCategoria());
		// System.out.println(lista.get(i).getValor());
		// System.out.println(lista.get(i).getQuantidade());
		// após realizarmos o teste e ver que esta mandando os registros, podemos
		// apaga-las
		// }

		// Encaminhando a lista ao documento produtos.jsp
		request.setAttribute("produtos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("produtos.jsp");// É uma classe modelo que trabalha com
																			// requisições e respostas no Servlet
		rd.forward(request, response); // passo 7 da aula #12
	}

	// novo produto
	protected void novoProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendRedirect("produtos.jsp");

		// Fazendo um teste para verificar se o Servlet esta recebendo os dados do
		// formulário:
		// ESSAS 5 PÁGINAS DE TESTE, PODEMOS APAGAR:
		// System.out.println(request.getParameter("codigo"));
		// System.out.println(request.getParameter("nome"));
		// System.out.println(request.getParameter("categoria"));
		// System.out.println(request.getParameter("valor"));
		// System.out.println(request.getParameter("quantidade"));

		// Setar as variáveis do JavaBeans (passo5):
		// produto.setCodigo(request.getParameter("codigo"));

		produto.setCodigo(Integer.parseInt(request.getParameter("codigo")));

		// O objeto produto, consegue atraves do metodo "setCodigo", armazenar na
		// variávl código o valor que ele está recebendo do formulário de produto. Faz
		// isso para os demais dados

		produto.setNome(request.getParameter("nome"));

		produto.setCategoria(request.getParameter("categoria"));

		// produto.setValor(Integer.parseInt(request.getParameter("valor")));
		produto.setValor(Float.parseFloat(request.getParameter("valor").replaceAll(",", ".")));

		produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));

		// ---- INVOCAR O METODO inserirProduto passando o objeto contato
		dao.inserirProduto(produto);

		// Para finalizar o método novoProduto, iremos fazer o redirecionar para o
		// documento produtos.jsp
		response.sendRedirect("main");

	}

	// Editar produto(1o envolve selecionar o contato que será alterado):
	protected void listarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Verificando se o botao "editar" esta mandando a requisição ao servlet

		// Recebimento do id do contato que será editado:
		int id = (Integer.parseInt(request.getParameter("id")));
		// System.out.println(id); teste para saber se o servlet esta recebendo o id do
		// produto

		// Setar a variável JavaBeans
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

		// setar as variáveis Javabeans
		produto.setId(Integer.parseInt(request.getParameter("id")));
		produto.setCodigo(Integer.parseInt(request.getParameter("codigo")));
		produto.setNome(request.getParameter("nome"));
		produto.setCategoria(request.getParameter("categoria"));
		produto.setValor(Float.parseFloat(request.getParameter("valor").replaceAll(",", ".")));
		produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));

		// executar o método alterarProduto:
		dao.alterarProduto(produto);
		// Redirecionando para o documento produto.jsp (atualizando as alterações).
		response.sendRedirect("main");
	}

	// Excluir um contato:
	protected void excluirProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebendo o id do produto a ser excluído(validador.js)
		int id = (Integer.parseInt(request.getParameter("id")));
		// testando:
		// System.out.println(id);
		produto.setId(id); // Passo 3 da #14

		// Executar o metodo excluirProduto(DAO) passando o objeto produto como
		// parametro:
		dao.excluirProduto(produto);

		// Redirecionando para o documento produto.jsp (atualizando as alterações).
		response.sendRedirect("main");

	}

}
