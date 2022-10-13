package br.com.fiap.model;

public class ExperienciaProfissional {

	private int id;
	private Candidato candidato;
	private String empresa;
	private String dataInicio;
	private String dataTermino;
	private String areaAtuacao;

	// Construtor com atributos obrigatórios
	public ExperienciaProfissional(int id, Candidato candidato, String empresa, String dataInicio, String dataTermino,
			String areaAtuacao) {
		this.id = id;
		this.setCandidato(candidato);
		this.empresa = empresa;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.areaAtuacao = areaAtuacao;
	}

	@Override
	public String toString() {
		return "\nID: " + id + "\nEmpresa: " + empresa + "\nData de início: " + dataInicio + "\nData do término: "
				+ dataTermino + "\nÁrea de atuação: " + areaAtuacao;
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

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(String dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}

}