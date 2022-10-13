package br.com.fiap.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.dao.FormacaoAcademicaDAO;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.FormacaoAcademica;

/**
 * Classe que representa o Business Object da classe Formação Acadêmica
 * 
 * @author Luisa Purificação
 * @version 1.0
 */
public class FormacaoAcademicaBO {

	/**
	 * Cria um Data Access Object da Formação acadêmica
	 */
	private FormacaoAcademicaDAO dao;

	/**
	 * Cria uma conexão com o banco de dados
	 */
	private Connection con;

	/**
	 * Método que armazena a formação acadêmica recebida como parâmetro no banco de
	 * dados
	 * 
	 * @param formAcad Formação acadêmica que vai ser criada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void cadastrar(FormacaoAcademica formAcad) throws ClassNotFoundException, SQLException {

		con = ConnectionFactory.getConnection();
		dao = new FormacaoAcademicaDAO(con);

		// REGRAS DE NEGOCIO

		dao.create(formAcad);

	}

	/**
	 * Método que lê a formação acadêmica de acordo com seu código no banco de dados
	 * 
	 * @param id ID da formação acadêmica
	 * @return Formação acadêmica
	 * @throws SQLException
	 * @throws IdNotFoundException
	 * @throws ClassNotFoundException
	 */
	public FormacaoAcademica listar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new FormacaoAcademicaDAO(con);

		// REGRAS DE NEGOCIO

		return dao.readById(id);

	}

	/**
	 * Método que lê a formação acadêmica de acordo com o código de seu candidato no
	 * banco de dados
	 * 
	 * @param id Código do candidato
	 * @return Lista de Experiências Profissionais com o código recebido
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public List<FormacaoAcademica> listarCandidato(int id)
			throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new FormacaoAcademicaDAO(con);

		// REGRAS DE NEGOCIO

		return dao.readByCandidatoId(id);

	}

	/**
	 * Método que atualiza as informações de uma formação acadêmica que já está
	 * armazenada no banco de dados
	 * 
	 * @param formAcad FormacaoAcademica
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void atualizar(FormacaoAcademica formAcad) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new FormacaoAcademicaDAO(con);

		// REGRAS DE NEGOCIO

		dao.update(formAcad);

	}

	/**
	 * Método que remove uma formação acadêmica do banco de dados
	 * 
	 * @param id Código da empresa que vai ser removida
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void remover(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new FormacaoAcademicaDAO(con);

		// REGRAS DE NEGOCIO

		dao.delete(id);

	}

}
