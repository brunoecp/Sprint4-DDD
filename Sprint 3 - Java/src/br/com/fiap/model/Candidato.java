package br.com.fiap.model;

import java.util.ArrayList;
import java.util.List;

public class Candidato extends Usuario {

	@Override
	public String toString() {
		return "Candidato " + getId() + "- " + getNome() + "\nE-mail: " + getEmail() + "\nCelular: " + getTelefone()
				+ "\nGênero: " + genero + "\nData de nascimento: " + dataNascimento + "\nEndereço: " + endereco
				+ "\nGithub: " + github + "\nInformação adicional: " + infoAdicional + "\nÁrea profissional: "
				+ areaProfissional + "\nNível profissional: " + nivelProfissional + "\nExperiência profissional: "
				+ expProfissional.toString() + "\nFormação acadêmica: " + formAcademica.toString()
				+ "\nFormação complementar: " + formComplementar.toString() + "\nIdioma: " + idioma.toString();
	}

	// Atributos
	private Long cpf;
	private String genero;
	private String dataNascimento;
	private String endereco;
	private String github;
	private String infoAdicional;
	private String areaProfissional;
	private String nivelProfissional;
	List<ExperienciaProfissional> expProfissional = new ArrayList<>();
	List<FormacaoAcademica> formAcademica = new ArrayList<>();
	List<FormacaoComplementar> formComplementar = new ArrayList<>();
	List<Idioma> idioma = new ArrayList<>();

	// Construtor
	public Candidato() {
	}

	// Construtor com atributos
	public Candidato(int id, String nome, String email, Long telefone, Long cpf, String genero, String senha,
			String dataNascimento, String areaProfissional, String nivelProfissional, String infoAdicional,
			String endereco, String github) {
		super(id, nome, email, senha, telefone);
		this.cpf = cpf;
		this.genero = genero;
		this.dataNascimento = dataNascimento;
		this.areaProfissional = areaProfissional;
		this.nivelProfissional = nivelProfissional;
		this.infoAdicional = infoAdicional;
		this.endereco = endereco;
		this.github = github;
	}

	// Getters e Setters
	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	public String getInfoAdicional() {
		return infoAdicional;
	}

	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}

	public String getAreaProfissional() {
		return areaProfissional;
	}

	public void setAreaProfissional(String areaProfissional) {
		this.areaProfissional = areaProfissional;
	}

	public String getNivelProfissional() {
		return nivelProfissional;
	}

	public void setNivelProfissional(String nivelProfissional) {
		this.nivelProfissional = nivelProfissional;
	}

	public List<ExperienciaProfissional> getExpProfissional() {
		return expProfissional;
	}

	public void setExpProfissional(List<ExperienciaProfissional> expProfissional) {
		this.expProfissional = expProfissional;
	}

	public List<FormacaoAcademica> getFormAcademica() {
		return formAcademica;
	}

	public void setFormAcademica(List<FormacaoAcademica> formAcademica) {
		this.formAcademica = formAcademica;
	}

	public List<FormacaoComplementar> getFormComplementar() {
		return formComplementar;
	}

	public void setFormComplementar(List<FormacaoComplementar> formComplementar) {
		this.formComplementar = formComplementar;
	}

	public List<Idioma> getIdioma() {
		return idioma;
	}

	public void setIdioma(List<Idioma> idioma) {
		this.idioma = idioma;
	}

}
