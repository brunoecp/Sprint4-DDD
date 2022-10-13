package br.com.fiap.model;

public class Instituicao {

	// Atributos
	private int id;
	private Long cnpj;
	private String nome;
	private String endereco;

	// Construtor com atributos obrigatórios
	public Instituicao(int id, Long cnpj, String nome, String endereco) {
		this.id = id;
		this.cnpj = cnpj;
		this.nome = nome;
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return  id + "- " + nome + "\nCNPJ: " + cnpj + "\nEndereço: " + endereco;
	}

	// Getters e Setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}