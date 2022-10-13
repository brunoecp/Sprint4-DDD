package br.com.fiap.model;

public class FormacaoComplementar {

	@Override
	public String toString() {
		return "\nID: " + id + "\nInstituição: " + instituicao.getNome() + "\nCurso: " + curso + "\nData de início: "
				+ dataInicio + "\nData de conclusão: " + dataConclusao + "\nÁrea: " + area;
	}

	// Atributos
	private int id;
	private Instituicao instituicao;
	private Candidato candidato;
	private String curso;
	private String dataInicio;
	private String dataConclusao;
	private String area;

	// Construtor com atributos obrigatórios
	public FormacaoComplementar(int id, Instituicao instituicao, Candidato candidato, String curso, String dataInicio,
			String dataConclusao, String area) {
		this.id = id;
		this.instituicao = instituicao;
		this.candidato = candidato;
		this.curso = curso;
		this.dataInicio = dataInicio;
		this.dataConclusao = dataConclusao;
		this.area = area;
	}

	// Getters e Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(String dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}