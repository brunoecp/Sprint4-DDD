package br.com.fiap.view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import br.com.fiap.bo.*;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.model.*;

public class Menu {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		EmpresaBO boEmpresa = new EmpresaBO();
		VagaBO boVaga = new VagaBO();
		CandidatoBO boCandidato = new CandidatoBO();
		ExperienciaProfissionalBO boExpProf = new ExperienciaProfissionalBO();
		InstituicaoBO boInstituicao = new InstituicaoBO();
		FormacaoAcademicaBO boFormAcad = new FormacaoAcademicaBO();
		FormacaoComplementarBO boFormComp = new FormacaoComplementarBO();
		IdiomaBO boIdioma = new IdiomaBO();

		int opcao = 0;
		int opcao2 = 0;

		do {

			System.out.println("----------------------------------------------");
			System.out.println("Bem vindo ao software da equipe Data Cleans!\nEscolha o que voc� gostaria de fazer:");
			System.out.println("0- Sair\n1- Registrar\n2- Atualizar\n3- Visualizar\n4- Remover\n5- Pesquisar");
			opcao = scan.nextInt();
			System.out.println("----------------------------------------------");

			switch (opcao) {
			case 1:

				System.out.println("O que voc� gostaria de registrar?");
				System.out.println("Op��es para empresas:\n0- Sair\n1- Empresa\n2- Vagas");
				System.out.println(
						"Op��es para candidatos:\n3- Candidato\n4- Experi�ncia profissional\n5- Forma��o acad�mica"
								+ "\n6- Forma��o complementar\n7- Idioma\n8- Institui��o educacional");
				opcao2 = scan.nextInt();

				switch (opcao2) {
				case 0:
					break;

				case 1:

					System.out.println("Insira o nome da empresa:");
					String nome = scan.next() + scan.nextLine();
					System.out.println("Insira o CNPJ:");
					Long cnpj = scan.nextLong();
					System.out.println("Insira a �rea em que a empresa � focada:");
					String area = scan.next() + scan.nextLine();
					System.out.println("Insira sua senha:");
					String senha = scan.next() + scan.nextLine();
					System.out.println("Insira o n�mero de celular/ telefone do RH:");
					Long telefone = scan.nextLong();
					System.out.println("Insira o e-mail:");
					String email = scan.next() + scan.nextLine();
					System.out.println("Insira o CEO:");
					String ceo = scan.next() + scan.nextLine();
					System.out.println("Insira o site:");
					String site = scan.next() + scan.nextLine();
					System.out.println("Insira o canal do youtube:");
					String youtube = scan.next() + scan.nextLine();

					Empresa cadastro = new Empresa(0, nome, cnpj, area, senha, telefone, email, ceo, site, youtube);

					try {
						boEmpresa.cadastrar(cadastro);
						System.out.println("Empresa cadastrada com sucesso!");
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}

					break;

				case 2:

					System.out.println("Insira o c�digo da empresa que a vaga est� associada:");
					int idEmpresa = scan.nextInt();
					System.out.println("Insira o cargo da vaga:");
					String cargo = scan.next() + scan.nextLine();
					System.out.println("Insira o nivel de conhecimento necess�rio:");
					String nivel = scan.next() + scan.nextLine();
					System.out.println("Insira a localiza��o:");
					String localizacao = scan.next() + scan.nextLine();
					System.out.println("Insira o sal�rio:");
					double salario = scan.nextDouble();
					System.out.println("Insira os requisitos para essa vaga:");
					String requisitos = scan.next() + scan.nextLine();
					System.out.println("Insira o tipo (home-office, presencial, h�brido ou flex�vel):");
					String tipo = scan.next() + scan.nextLine();

					Empresa empresa = null;

					try {
						empresa = boEmpresa.listar(idEmpresa);
						List<Vaga> vagasDaEmpresa = boVaga.listar(idEmpresa);
						empresa.setVagas(vagasDaEmpresa);

					} catch (ClassNotFoundException | SQLException | IdNotFoundException e2) {
						e2.printStackTrace();
					}

					Vaga cadastroVaga = new Vaga(0, empresa, cargo, nivel, localizacao, salario, requisitos, tipo);

					try {
						boVaga.cadastrar(cadastroVaga);
						System.out.println("Vaga cadastrada com sucesso!");
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}

					break;

				case 3:

					System.out.println("Insira seu nome completo:");
					String nomeCand = scan.next() + scan.nextLine();
					System.out.println("Insira seu e-mail:");
					String emailCand = scan.next() + scan.nextLine();
					System.out.println("Insira o n�mero do seu celular:");
					Long telCand = scan.nextLong();
					System.out.println("Insira seu CPF:");
					Long cpf = scan.nextLong();
					System.out.println("Insira seu g�nero: (F - feminino, M - masculino, NB - n�o binarie)");
					String genero = scan.next() + scan.nextLine();
					System.out.println("Insira sua senha:");
					String senhaCand = scan.next() + scan.nextLine();
					System.out.println("Insira sua data de nascimento: (DD/MM/YYYY)");
					String dataNasc = scan.next() + scan.nextLine();
					System.out.println("Insira sua �rea profissional:");
					String areaProf = scan.next() + scan.nextLine();
					System.out.println(
							"Insira seu n�vel profissional: (Jovem aprendiz, estagi�rio, junior, senior ou pleno)");
					String nivelProf = scan.next() + scan.nextLine();
					System.out.println("Insira suas informa��es adicionais:");
					String infoAdic = scan.next() + scan.nextLine();
					System.out.println("Insira seu endere�o:");
					String endereco = scan.next() + scan.nextLine();
					System.out.println("Insira seu github:");
					String github = scan.next() + scan.nextLine();

					Candidato candidato = new Candidato(0, nomeCand, emailCand, telCand, cpf, genero, senhaCand,
							dataNasc, areaProf, nivelProf, infoAdic, endereco, github);

					try {
						boCandidato.cadastrar(candidato);
						System.out.println("Candidato cadastrado com sucesso!");
					} catch (ClassNotFoundException | SQLException e3) {
						e3.printStackTrace();
					}

					break;

				case 4:

					System.out.println("Insira o c�digo do candidato que a experi�ncia profissional est� associada:");
					int idCandidato = scan.nextInt();

					System.out.println("Insira o nome da empresa em qual voc� trabalhou:");
					String nomeEmp = scan.next() + scan.nextLine();
					System.out.println("Insira a data de in�cio:");
					String dataI = scan.next() + scan.nextLine();
					System.out.println("Insira a data de t�rmino do cargo:");
					String dataT = scan.next() + scan.nextLine();
					System.out.println("Insira a �rea em que voc� atuava:");
					String areaAtuacao = scan.next() + scan.nextLine();

					Candidato c1 = null;

					try {
						c1 = boCandidato.listar(idCandidato);
						List<ExperienciaProfissional> expProfis = boExpProf.listarCandidato(idCandidato);
						c1.setExpProfissional(expProfis);

					} catch (ClassNotFoundException | SQLException | IdNotFoundException e2) {
						e2.printStackTrace();
					}

					ExperienciaProfissional expProf = new ExperienciaProfissional(0, c1, nomeEmp, dataI, dataT,
							areaAtuacao);

					try {
						boExpProf.cadastrar(expProf);
						System.out.println("Experi�ncia profissional cadastrada com sucesso!");
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}

					break;

				case 5:

					System.out.println("Insira o c�digo do candidato que a forma��o acad�mica est� associada:");
					int idCandForm = scan.nextInt();
					System.out.println("Insira o c�digo da institui��o que a forma��o acad�mica est� associada:");
					int idInstForm = scan.nextInt();
					System.out.println("Insira o nome do curso:");
					String curso = scan.next() + scan.nextLine();
					System.out.println("Insira a data de in�cio: (DD/MM/YYYY)");
					String dataInicio = scan.next() + scan.nextLine();
					System.out.println("Insira a data de conclus�o: (DD/MM/YYYY)");
					String dataConclusao = scan.next() + scan.nextLine();
					System.out.println("Insira a �rea de estudo:");
					String areaForm = scan.next() + scan.nextLine();
					System.out.println("Informa��es adicionais: (O que voc� aprendeu, por exemplo)");
					String infoAdicForm = scan.next() + scan.nextLine();

					Candidato c2 = null;
					Instituicao i1 = null;

					try {
						c2 = boCandidato.listar(idCandForm);
						List<FormacaoAcademica> formsAcad = boFormAcad.listarCandidato(idCandForm);
						c2.setFormAcademica(formsAcad);
						i1 = boInstituicao.listar(idInstForm);

					} catch (ClassNotFoundException | SQLException | IdNotFoundException e2) {
						e2.printStackTrace();
					}

					FormacaoAcademica formAcad = new FormacaoAcademica(0, c2, i1, curso, dataInicio, dataConclusao,
							areaForm, infoAdicForm);

					try {
						boFormAcad.cadastrar(formAcad);
						System.out.println("Forma��o acad�mica registrada com sucesso!");
					} catch (ClassNotFoundException | SQLException e5) {
						e5.printStackTrace();
					}

					break;

				case 6:

					System.out.println("Insira o c�digo do candidato que a forma��o acad�mica est� associada:");
					int idCandFormComp = scan.nextInt();
					System.out.println("Insira o c�digo da institui��o que a forma��o acad�mica est� associada:");
					int idInstFormComp = scan.nextInt();
					System.out.println("Insira o nome do curso:");
					String cursoFC = scan.next() + scan.nextLine();
					System.out.println("Insira a data de in�cio: (DD/MM/YYYY)");
					String dataInicioFC = scan.next() + scan.nextLine();
					System.out.println("Insira a data de conclus�o: (DD/MM/YYYY)");
					String dataConclusaoFC = scan.next() + scan.nextLine();
					System.out.println("Insira a �rea de estudo:");
					String areaFormFC = scan.next() + scan.nextLine();

					Candidato c3 = null;
					Instituicao i2 = null;

					try {
						c3 = boCandidato.listar(idCandFormComp);
						List<FormacaoComplementar> formsComp = boFormComp.listarCandidato(idCandFormComp);
						c3.setFormComplementar(formsComp);
						i2 = boInstituicao.listar(idInstFormComp);

					} catch (ClassNotFoundException | SQLException | IdNotFoundException e2) {
						e2.printStackTrace();
					}

					FormacaoComplementar formComp = new FormacaoComplementar(0, i2, c3, cursoFC, dataInicioFC,
							dataConclusaoFC, areaFormFC);

					try {
						boFormComp.cadastrar(formComp);
						System.out.println("Forma��o complementar registrada com sucesso!");
					} catch (ClassNotFoundException | SQLException e6) {
						e6.printStackTrace();
					}

					break;

				case 7:

					System.out.println("Insira o c�digo do candidato que o idioma est� associado:");
					int idCandIdioma = scan.nextInt();
					System.out.println("Insira o c�digo da institui��o que o idioma est� associado:");
					int idInstIdioma = scan.nextInt();
					System.out.println("Insira qual � o idioma:");
					String nomeIdioma = scan.next() + scan.nextLine();
					System.out.println(
							"Insira seu n�vel de conhecimento desse idioma: (Iniciante, Intermedi�rio, Avan�ado ou Fluente)");
					String nivelIdioma = scan.next() + scan.nextLine();

					Candidato c4 = null;
					Instituicao i3 = null;

					try {
						c4 = boCandidato.listar(idCandIdioma);
						List<Idioma> idiomas = boIdioma.listarCandidato(idCandIdioma);
						c4.setIdioma(idiomas);
						i3 = boInstituicao.listar(idInstIdioma);

					} catch (ClassNotFoundException | SQLException | IdNotFoundException e2) {
						e2.printStackTrace();
					}

					Idioma idioma = new Idioma(0, c4, i3, nomeIdioma, nivelIdioma);

					try {
						boIdioma.cadastrar(idioma);
						System.out.println("Idioma registrado com sucesso!");
					} catch (ClassNotFoundException | SQLException e7) {
						e7.printStackTrace();
					}

					break;

				case 8:

					System.out.println("Insira o nome da institui��o:");
					String nomeInst = scan.next() + scan.nextLine();
					System.out.println("Insira o CNPJ:");
					Long cnpjInst = scan.nextLong();
					System.out.println("Insira o endere�o:");
					String enderecoInst = scan.next() + scan.nextLine();

					Instituicao instituicao = new Instituicao(0, cnpjInst, nomeInst, enderecoInst);

					try {
						boInstituicao.cadastrar(instituicao);
						System.out.println("Institui��o cadastrada com sucesso!");
					} catch (ClassNotFoundException | SQLException e4) {
						e4.printStackTrace();
					}

					break;

				default:
					System.out.println("Op��o inv�lida.");
					break;
				}

				break;

			case 2:

				System.out.println("O que voc� gostaria de atualizar?");
				System.out.println("Op��es para empresas:\n0- Sair\n1- Empresa\n2- Vagas");
				System.out.println(
						"Op��es para candidatos:\n3- Candidato\n4- Experi�ncia profissional\n5- Forma��o acad�mica"
								+ "\n6- Forma��o complementar\n7- Idioma\n8- Institui��o educacional");
				opcao2 = scan.nextInt();

				switch (opcao2) {
				case 0:
					break;

				case 1:

					System.out.println("Insira o ID da empresa que voc� gostaria de atualizar:");
					int id = scan.nextInt();

					System.out.println("Insira o nome da empresa:");
					String nome = scan.next() + scan.nextLine();
					System.out.println("Insira o CNPJ:");
					Long cnpj = scan.nextLong();
					System.out.println("Insira a �rea em que a empresa � focada:");
					String area = scan.next() + scan.nextLine();
					System.out.println("Insira sua senha:");
					String senha = scan.next() + scan.nextLine();
					System.out.println("Insira o n�mero de celular/ telefone do RH:");
					Long telefone = scan.nextLong();
					System.out.println("Insira o e-mail:");
					String email = scan.next() + scan.nextLine();
					System.out.println("Insira o CEO:");
					String ceo = scan.next() + scan.nextLine();
					System.out.println("Insira o site:");
					String site = scan.next() + scan.nextLine();
					System.out.println("Insira o canal do youtube:");
					String youtube = scan.next() + scan.nextLine();

					Empresa atualizada = new Empresa(id, nome, cnpj, area, senha, telefone, email, ceo, site, youtube);

					try {
						boEmpresa.atualizar(atualizada);
						System.out.println("Empresa atualizada com sucesso!");
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					} catch (IdNotFoundException e) {
						e.printStackTrace();
					}

					break;

				case 2:
					System.out.println("Insira o ID da vaga que voc� gostaria de atualizar:");
					int idVaga = scan.nextInt();

					System.out.println("Insira o c�digo da empresa que a vaga est� associada:");
					int idEmpresa = scan.nextInt();
					System.out.println("Insira o cargo da vaga:");
					String cargo = scan.next() + scan.nextLine();
					System.out.println("Insira o nivel de conhecimento necess�rio:");
					String nivel = scan.next() + scan.nextLine();
					System.out.println("Insira a localiza��o:");
					String localizacao = scan.next() + scan.nextLine();
					System.out.println("Insira o sal�rio:");
					double salario = scan.nextDouble();
					System.out.println("Insira os requisitos para essa vaga:");
					String requisitos = scan.next() + scan.nextLine();
					System.out.println("Insira o tipo (home-office, presencial, h�brido ou flex�vel):");
					String tipo = scan.next() + scan.nextLine();

					Empresa empresa = null;
					try {
						empresa = boEmpresa.listar(idEmpresa);
					} catch (ClassNotFoundException | SQLException | IdNotFoundException e2) {
						e2.printStackTrace();
					}

					Vaga atualizadaVaga = new Vaga(idVaga, empresa, cargo, nivel, localizacao, salario, requisitos,
							tipo);

					try {
						boVaga.atualizar(atualizadaVaga);
						System.out.println("Vaga atualizada com sucesso!");
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					} catch (IdNotFoundException e) {
						e.printStackTrace();
					}

					break;

				case 3:

					System.out.println("Insira o ID do candidato que voc� gostaria de atualizar:");
					int idCand = scan.nextInt();

					System.out.println("Insira seu nome completo:");
					String nomeCand = scan.next() + scan.nextLine();
					System.out.println("Insira seu e-mail:");
					String emailCand = scan.next() + scan.nextLine();
					System.out.println("Insira o n�mero do seu celular:");
					Long telCand = scan.nextLong();
					System.out.println("Insira seu CPF:");
					Long cpf = scan.nextLong();
					System.out.println("Insira seu g�nero: (F - feminino, M - masculino, NB - n�o binarie)");
					String genero = scan.next() + scan.nextLine();
					System.out.println("Insira sua senha:");
					String senhaCand = scan.next() + scan.nextLine();
					System.out.println("Insira sua data de nascimento: (DD/MM/YYYY)");
					String dataNasc = scan.next() + scan.nextLine();
					System.out.println("Insira sua �rea profissional:");
					String areaProf = scan.next() + scan.nextLine();
					System.out.println(
							"Insira seu n�vel profissional: (Jovem aprendiz, estagi�rio, junior, senior ou pleno)");
					String nivelProf = scan.next() + scan.nextLine();
					System.out.println("Insira suas informa��es adicionais:");
					String infoAdic = scan.next() + scan.nextLine();
					System.out.println("Insira seu endere�o:");
					String endereco = scan.next() + scan.nextLine();
					System.out.println("Insira seu github:");
					String github = scan.next() + scan.nextLine();

					Candidato candidato = new Candidato(idCand, nomeCand, emailCand, telCand, cpf, genero, senhaCand,
							dataNasc, areaProf, nivelProf, infoAdic, endereco, github);

					try {
						boCandidato.atualizar(candidato);
						System.out.println("Candidato atualizado com sucesso!");
					} catch (ClassNotFoundException | SQLException | IdNotFoundException e3) {
						e3.printStackTrace();
					}

					break;

				case 4:

					System.out.println("Insira o c�digo da experi�ncia profissional que voc� gostaria de atualizar:");
					int idExpProf = scan.nextInt();

					System.out.println("Insira o c�digo do candidato que a experi�ncia profissional est� associada:");
					int idCandidato = scan.nextInt();
					System.out.println("Insira o nome da empresa em qual voc� trabalhou:");
					String nomeEmp = scan.next() + scan.nextLine();
					System.out.println("Insira a data de in�cio:");
					String dataI = scan.next() + scan.nextLine();
					System.out.println("Insira a data de t�rmino do cargo:");
					String dataT = scan.next() + scan.nextLine();
					System.out.println("Insira a �rea em que voc� atuava:");
					String areaAtuacao = scan.next() + scan.nextLine();

					Candidato c1 = null;

					try {
						c1 = boCandidato.listar(idCandidato);
						List<ExperienciaProfissional> expProfis = boExpProf.listarCandidato(idCandidato);
						c1.setExpProfissional(expProfis);

					} catch (ClassNotFoundException | SQLException | IdNotFoundException e2) {
						e2.printStackTrace();
					}

					ExperienciaProfissional expProf = new ExperienciaProfissional(idExpProf, c1, nomeEmp, dataI, dataT,
							areaAtuacao);

					try {
						boExpProf.atualizar(expProf);
						System.out.println("Experi�ncia profissional atualizada com sucesso!");
					} catch (ClassNotFoundException | SQLException | IdNotFoundException e1) {
						e1.printStackTrace();
					}

					break;

				case 5:

					System.out.println("Insira o c�digo da forma��o acad�mica que voc� gostaria de atualizar:");
					int idFormAcad = scan.nextInt();

					System.out.println("Insira o c�digo do candidato que a forma��o acad�mica est� associada:");
					int idCandForm = scan.nextInt();
					System.out.println("Insira o c�digo da institui��o que a forma��o acad�mica est� associada:");
					int idInstForm = scan.nextInt();
					System.out.println("Insira o nome do curso:");
					String curso = scan.next() + scan.nextLine();
					System.out.println("Insira a data de in�cio: (DD/MM/YYYY)");
					String dataInicio = scan.next() + scan.nextLine();
					System.out.println("Insira a data de conclus�o: (DD/MM/YYYY)");
					String dataConclusao = scan.next() + scan.nextLine();
					System.out.println("Insira a �rea de estudo:");
					String areaForm = scan.next() + scan.nextLine();
					System.out.println("Informa��es adicionais: (O que voc� aprendeu, por exemplo)");
					String infoAdicForm = scan.next() + scan.nextLine();

					Candidato c2 = null;
					Instituicao i1 = null;

					try {
						c2 = boCandidato.listar(idCandForm);
						List<FormacaoAcademica> formsAcad = boFormAcad.listarCandidato(idCandForm);
						c2.setFormAcademica(formsAcad);
						i1 = boInstituicao.listar(idInstForm);

					} catch (ClassNotFoundException | SQLException | IdNotFoundException e2) {
						e2.printStackTrace();
					}

					FormacaoAcademica formAcad = new FormacaoAcademica(idFormAcad, c2, i1, curso, dataInicio,
							dataConclusao, areaForm, infoAdicForm);

					try {
						boFormAcad.atualizar(formAcad);
						System.out.println("Forma��o acad�mica atualizada com sucesso!");
					} catch (ClassNotFoundException | SQLException | IdNotFoundException e5) {
						e5.printStackTrace();
					}

					break;

				case 6:

					System.out.println("Insira o c�digo da forma��o acad�mica que voc� gostaria de atualizar:");
					int idFormComp = scan.nextInt();

					System.out.println("Insira o c�digo do candidato que a forma��o acad�mica est� associada:");
					int idCandFormComp = scan.nextInt();
					System.out.println("Insira o c�digo da institui��o que a forma��o acad�mica est� associada:");
					int idInstFormComp = scan.nextInt();
					System.out.println("Insira o nome do curso:");
					String cursoFC = scan.next() + scan.nextLine();
					System.out.println("Insira a data de in�cio: (DD/MM/YYYY)");
					String dataInicioFC = scan.next() + scan.nextLine();
					System.out.println("Insira a data de conclus�o: (DD/MM/YYYY)");
					String dataConclusaoFC = scan.next() + scan.nextLine();
					System.out.println("Insira a �rea de estudo:");
					String areaFormComp = scan.next() + scan.nextLine();

					Candidato c3 = null;
					Instituicao i2 = null;

					try {
						c3 = boCandidato.listar(idCandFormComp);
						List<FormacaoComplementar> formsComp = boFormComp.listarCandidato(idCandFormComp);
						c3.setFormComplementar(formsComp);
						i2 = boInstituicao.listar(idInstFormComp);

					} catch (ClassNotFoundException | SQLException | IdNotFoundException e2) {
						e2.printStackTrace();
					}

					FormacaoComplementar formComp = new FormacaoComplementar(idFormComp, i2, c3, cursoFC, dataInicioFC,
							dataConclusaoFC, areaFormComp);

					try {
						boFormComp.atualizar(formComp);
						System.out.println("Forma��o complementar atualizada com sucesso!");
					} catch (ClassNotFoundException | SQLException | IdNotFoundException e6) {
						e6.printStackTrace();
					}

					break;

				case 7:

					System.out.println("Insira o ID do idioma que voc� gostaria de atualizar:");
					int idIdioma = scan.nextInt();

					System.out.println("Insira o c�digo do candidato que o idioma est� associado:");
					int idCandIdioma = scan.nextInt();
					System.out.println("Insira o c�digo da institui��o que o idioma est� associado:");
					int idInstIdioma = scan.nextInt();
					System.out.println("Insira qual � o idioma:");
					String nomeIdioma = scan.next() + scan.nextLine();
					System.out.println(
							"Insira seu n�vel de conhecimento desse idioma: (Iniciante, Intermedi�rio, Avan�ado ou Fluente)");
					String nivelIdioma = scan.next() + scan.nextLine();

					Candidato c4 = null;
					Instituicao i3 = null;

					try {
						c4 = boCandidato.listar(idCandIdioma);
						List<Idioma> idiomas = boIdioma.listarCandidato(idCandIdioma);
						c4.setIdioma(idiomas);
						i3 = boInstituicao.listar(idInstIdioma);

					} catch (ClassNotFoundException | SQLException | IdNotFoundException e2) {
						e2.printStackTrace();
					}

					Idioma idioma = new Idioma(idIdioma, c4, i3, nomeIdioma, nivelIdioma);

					try {
						boIdioma.atualizar(idioma);
						System.out.println("Idioma atualizado com sucesso!");
					} catch (ClassNotFoundException | SQLException | IdNotFoundException e7) {
						e7.printStackTrace();
					}

					break;

				case 8:

					System.out.println("Insira o ID da institui��o que voc� gostaria de atualizar:");
					int idInst = scan.nextInt();

					System.out.println("Insira o nome da institui��o:");
					String nomeInst = scan.next() + scan.nextLine();
					System.out.println("Insira o CNPJ:");
					Long cnpjInst = scan.nextLong();
					System.out.println("Insira o endere�o:");
					String enderecoInst = scan.next() + scan.nextLine();

					Instituicao instituicao = new Instituicao(idInst, cnpjInst, nomeInst, enderecoInst);

					try {
						boInstituicao.atualizar(instituicao);
						System.out.println("Institui��o atualizada com sucesso!");
					} catch (ClassNotFoundException | SQLException | IdNotFoundException e4) {
						e4.printStackTrace();
					}

					break;

				default:
					System.out.println("Op��o inv�lida.");
					break;
				}

				break;

			case 3:

				System.out.println("O que voc� gostaria de visualizar?");
				System.out.println("0- Sair\n1- Empresas\n2- Vagas\n3- Candidatos\n4- Institui��es educacionais");
				opcao2 = scan.nextInt();

				switch (opcao2) {
				case 0:
					break;

				case 1:

					try {
						List<Empresa> empresas = boEmpresa.listar();
						for (Empresa empresa : empresas) {
							System.out.println(empresa.toString());
							System.out.println("");
						}
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}

					break;

				case 2:

					try {
						List<Vaga> vagas = boVaga.listar();
						for (Vaga vaga : vagas) {
							System.out.println(vaga.toString());
							System.out.println("");
						}
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}

					break;

				case 3:

					try {
						List<Candidato> candidatos = boCandidato.listar();
						for (Candidato candidato : candidatos) {
							System.out.println(candidato.toString());
							System.out.println("");
						}
					} catch (ClassNotFoundException | SQLException | IdNotFoundException e2) {
						e2.printStackTrace();
					}

					break;

				case 4:

					try {
						List<Instituicao> instituicoes = boInstituicao.listar();
						for (Instituicao instituicao : instituicoes) {
							System.out.println(instituicao.toString());
							System.out.println("");
						}
					} catch (ClassNotFoundException | SQLException e3) {
						e3.printStackTrace();
					}

					break;

				default:
					System.out.println("Op��o inv�lida.");
					break;
				}

				break;

			case 4:

				System.out.println("O que voc� gostaria de remover?");
				System.out.println("Op��es para empresas:\n0- Sair\n1- Empresa\n2- Vagas");
				System.out.println(
						"Op��es para candidatos:\n3- Candidato\n4- Experi�ncia profissional\n5- Forma��o acad�mica"
								+ "\n6- Forma��o complementar\n7- Idioma\n8- Institui��o educacional");
				opcao2 = scan.nextInt();

				switch (opcao2) {
				case 0:
					break;

				case 1:

					System.out.println("Insira o ID da empresa que voc� gostaria de remover:");
					int id = scan.nextInt();

					try {
						boEmpresa.remover(id);
						System.out.println("Empresa removida com sucesso!");
					} catch (ClassNotFoundException | SQLException | IdNotFoundException e) {
						e.printStackTrace();
					}

					break;

				case 2:

					System.out.println("Insira o ID da vaga que voc� gostaria de remover:");
					int idVaga = scan.nextInt();

					try {
						boVaga.remover(idVaga);
						System.out.println("Vaga removida com sucesso!");
					} catch (ClassNotFoundException | SQLException | IdNotFoundException e1) {
						e1.printStackTrace();
					}

					break;

				case 3:

					System.out.println("Insira o ID do candidato que voc� gostaria de remover:");
					int idCand = scan.nextInt();

					try {
						boCandidato.remover(idCand);
						System.out.println("Candidato removido com sucesso!");
					} catch (ClassNotFoundException | SQLException | IdNotFoundException e2) {
						e2.printStackTrace();
					}

					break;

				case 4:

					System.out.println("Insira o ID da experi�ncia profissional que voc� gostaria de remover:");
					int idExpProf = scan.nextInt();

					try {
						boExpProf.remover(idExpProf);
						System.out.println("Experi�ncia profissional removida com sucesso!");
					} catch (ClassNotFoundException | SQLException | IdNotFoundException e1) {
						e1.printStackTrace();
					}

					break;

				case 5:

					System.out.println("Insira o ID da forma��o acad�mica que voc� gostaria de remover:");
					int idFormAcad = scan.nextInt();

					try {
						boFormAcad.remover(idFormAcad);
						System.out.println("Forma��o acad�mica removida com sucesso!");
					} catch (ClassNotFoundException | SQLException | IdNotFoundException e2) {
						e2.printStackTrace();
					}

					break;

				case 6:

					System.out.println("Insira o ID da forma��o complementar que voc� gostaria de remover:");
					int idFormComp = scan.nextInt();

					try {
						boFormComp.remover(idFormComp);
						System.out.println("Forma��o complementar removida com sucesso!");
					} catch (ClassNotFoundException | SQLException | IdNotFoundException e2) {
						e2.printStackTrace();
					}

					break;

				case 7:

					System.out.println("Insira o ID do idioma que voc� gostaria de remover:");
					int idIdioma = scan.nextInt();

					try {
						boIdioma.remover(idIdioma);
						System.out.println("Idioma removido com sucesso!");
					} catch (ClassNotFoundException | SQLException | IdNotFoundException e2) {
						e2.printStackTrace();
					}

					break;

				case 8:

					System.out.println("Insira o ID da institui��o que voc� gostaria de remover:");
					int idInst = scan.nextInt();

					try {
						boInstituicao.remover(idInst);
						System.out.println("Institui��o removida com sucesso");
					} catch (ClassNotFoundException | SQLException | IdNotFoundException e1) {
						e1.printStackTrace();
					}

					break;

				default:
					System.out.println("Op��o inv�lida.");
					break;
				}

				break;

			case 5:

				System.out.println("O que voc� gostaria de pesquisar?");
				System.out.println(
						"\n0- Sair\n1- Empresas por ID\n2- Empresas por NOME\n3- Vagas por CARGO\n4- Vagas por ID DA EMPRESA\n5- Candidato por ID\n6- Candidato por NOME\n7- Candidato por �REA PROFISSIONAL");

				opcao2 = scan.nextInt();

				switch (opcao2) {
				case 0:
					break;

				case 1:

					System.out.println("Insira o ID da empresa:");
					int id = scan.nextInt();

					try {
						Empresa listar = boEmpresa.listar(id);
						System.out.println(listar.toString());
					} catch (ClassNotFoundException | SQLException | IdNotFoundException e) {
						e.printStackTrace();
					}

					break;

				case 2:

					System.out.println("Insira o nome da empresa:");
					String nome = scan.next() + scan.nextLine();

					List<Empresa> listaEmpresas = null;

					try {

						listaEmpresas = boEmpresa.listar(nome);

					} catch (ClassNotFoundException | SQLException | IdNotFoundException e) {
						e.printStackTrace();
					}

					for (Empresa empresa : listaEmpresas) {
						System.out.println(empresa.toString());
					}

					break;

				case 3:

					System.out.println("Insira o cargo da vaga:");
					String cargo = scan.next() + scan.nextLine();

					try {

						List<Vaga> listaVagas = boVaga.listar(cargo);
						for (Vaga vaga : listaVagas) {
							System.out.println(vaga.toString());
						}

					} catch (ClassNotFoundException | SQLException | IdNotFoundException e) {
						e.printStackTrace();
					}

					break;

				case 4:

					System.out.println("Insira o ID da empresa:");
					int idEmpresa = scan.nextInt();

					try {

						List<Vaga> listaVagas = boVaga.listar(idEmpresa);
						for (Vaga vaga : listaVagas) {
							System.out.println(vaga.toString());
						}

					} catch (ClassNotFoundException | SQLException | IdNotFoundException e) {
						e.printStackTrace();
					}

					break;

				case 5:

					System.out.println("Insira o ID do candidato:");
					int idCand = scan.nextInt();

					try {
						Candidato candidato = boCandidato.listar(idCand);
						System.out.println(candidato.toString());
					} catch (ClassNotFoundException | SQLException | IdNotFoundException e) {
						e.printStackTrace();
					}

					break;

				case 6:

					System.out.println("Insira o nome do candidato:");
					String nomeCand = scan.next() + scan.nextLine();

					try {
						List<Candidato> listaCandidatosNome = boCandidato.listar(nomeCand);
						for (Candidato candidato : listaCandidatosNome) {
							System.out.println(candidato.toString());
							System.out.println();
						}
					} catch (ClassNotFoundException | SQLException | IdNotFoundException e) {
						e.printStackTrace();
					}

					break;

				case 7:

					System.out.println("Insira a �rea profissional do candidato:");
					String area = scan.next() + scan.nextLine();

					try {
						List<Candidato> listaCandidatosArea = boCandidato.listarArea(area);
						for (Candidato candidato : listaCandidatosArea) {
							System.out.println(candidato.toString());
							System.out.println();
						}
					} catch (ClassNotFoundException | SQLException | IdNotFoundException e) {
						e.printStackTrace();
					}

					break;

				default:
					System.out.println("Op��o inv�lida. Por favor digite um n�mero de 0 a 6.");
					break;
				}

				break;

			case 0:
				System.out.println("Obrigada por utilizar o nosso software!");
				break;

			default:
				System.out.println("Op��o inv�lida. Por favor escolha do n�mero 0 ao 5.");
				break;
			}

			System.out.println("");

		} while (opcao != 0);

		scan.close();

	}

}
