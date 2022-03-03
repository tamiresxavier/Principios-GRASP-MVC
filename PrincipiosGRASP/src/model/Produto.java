package model;

public class Produto {

	private String nome;
	
	private float preco;

	public Produto(String nome, float preco) {
		this.nome = nome;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float valorProduto) {
		this.preco = valorProduto;
	}	
	
}
