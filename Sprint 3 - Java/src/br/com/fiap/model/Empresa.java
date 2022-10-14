package br.com.fiap.model;

import java.util.List;

public class Empresa extends Usuario {

	// Atributos
	private Long cnpj;
	private String areaProfissional;
	private String ceo;
	private String site;
	private String youtube;
	private List<Vaga> vagas;

	@Override
	public String toString() {
		return getId() + "- " + getNome() + "\nárea profissional: " + areaProfissional + "\nTelefone do RH: "
				+ getTelefone() + "\nE-mail: " + getEmail() + "\nCEO: " + ceo + "\nSite: " + site
				+ "\nCanal do youtube: " + youtube + "\nVagas abertas:\n" + vagas;
	}

	public Empresa() {
	}

	// Construtor com atributos obrigat�rios
	public Empresa(int id, String nome, Long cnpj, String areaProfissional, String senha, Long telefone, String email,
			String ceo, String site, String youtube) {
		super(id, nome, email, senha, telefone);
		this.cnpj = cnpj;
		this.areaProfissional = areaProfissional;
		this.ceo = ceo;
		this.site = site;
		this.youtube = youtube;
	}

	// Getters e Setters
	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	public String getAreaProfissional() {
		return areaProfissional;
	}

	public void setAreaProfissional(String areaProfissional) {
		this.areaProfissional = areaProfissional;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getCeo() {
		return ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
	}

	public String getYoutube() {
		return youtube;
	}

	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

	public List<Vaga> getVagas() {
		return vagas;
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}

}
