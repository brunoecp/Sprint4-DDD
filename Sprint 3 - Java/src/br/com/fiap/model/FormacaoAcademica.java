package br.com.fiap.model;

public class FormacaoAcademica {

	@Override
	public String toString() {
		return "\nID: " + id + "\nInstituição: " + instituicao.getNome() + "\nCurso: " + curso + "\nData de início: "
				+ dataInicio + "\nData de conclusão: " + dataConclusao + "\nÁrea: " + area + "\nInformação adicional: "
				+ infoAdicional;
	}

	// Atributos
	private int id;
	private Candidato candidato;
	private Instituicao instituicao;
	private String curso;
	private String dataInicio;
	private String dataConclusao;
	private String area;
	private String infoAdicional;

	// Construtor com atributos obrigat�rios
	public FormacaoAcademica(int id, Candidato candidato, Instituicao instituicao, String curso, String dataInicio,
			String dataConclusao, String area, String infoAdicional) {
		this.id = id;
		this.candidato = candidato;
		this.instituicao = instituicao;
		this.curso = curso;
		this.dataInicio = dataInicio;
		this.dataConclusao = dataConclusao;
		this.area = area;
		this.infoAdicional = infoAdicional;
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

	public String getInfoAdicional() {
		return infoAdicional;
	}

	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}

}