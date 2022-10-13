package br.com.fiap.model;

public class Usuario {

	// Atributos da empresa e do candidato
	private int id;
	private String nome;
	private String email;
	private Long telefone;
	private String senha;

	// Construtor
	public Usuario() {
	}
	
	// Construtor com atributos obrigatórios
	public Usuario(int id, String nome, String email, String senha, Long telefone) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
	}
	
	// Getters e Setters
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}