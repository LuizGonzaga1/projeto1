package model;

public class JavaBeans {

	// criando as variaveis que serão encapsuladas.
	// Ira nomear as variaveis de acordo com os campos da nossa tabela do banco de
	// dados
	// Criando 2 construtores:

	private int id;
	private int codigo;
	private String nome;
	private String categoria;
	private float valor;
	private int quantidade;

	public JavaBeans() {
		super();

	}

	public JavaBeans(int id, int codigo, String nome, String categoria, float valor, int quantidade) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.categoria = categoria;
		this.valor = valor;
		this.quantidade = quantidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

		

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	
}
