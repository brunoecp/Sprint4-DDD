package br.com.fiap.model;

public class Vaga {

	// Atributos
	private int id;
	private String cargo;
	private String nivel;
	private String localizacao;
	private double salario;
	private String requisitos;
	private String tipo;
	private Empresa empresa;

	@Override
	public String toString() {
		return id + "- " + cargo + "\nEmpresa: " + empresa.getId() + "- " + empresa.getNome() + "\nNível: " + nivel + "\nLocalização: "
				+ localizacao + "\nNível salário: " + salario + "\nRequisitos: " + requisitos + "\nTipo: " + tipo
				+ "\n";
	}

	// Construtor com atributos obrigatórios
	public Vaga(int id, Empresa empresa, String cargo, String nivel, String localizacao, double salario,
			String requisitos, String tipo) {
		this.id = id;
		this.empresa = empresa;
		this.cargo = cargo;
		this.nivel = nivel;
		this.localizacao = localizacao;
		this.salario = salario;
		this.requisitos = requisitos;
		this.tipo = tipo;
	}

	// Getters e Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
