package br.com.fiap.model;

public class Idioma {

	// Atributos
	private int id;
	private Candidato candidato;
	private Instituicao instituicao;
	private String curso;
	private String nivel;

	// Construtor com atributos obrigat�rios
	public Idioma(int id, Candidato candidato, Instituicao instituicao, String curso, String nivel) {
		this.id = id;
		this.candidato = candidato;
		this.instituicao = instituicao;
		this.curso = curso;
		this.nivel = nivel;
	}
	
	@Override
	public String toString() {
		return "\nID: " + id + "\nCandidato: " + candidato + "\nInstituição: " + instituicao.getNome() + "\nCurso: " + curso
				+ "\nNível: " + nivel;
	}
	
	// Getters e Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
}