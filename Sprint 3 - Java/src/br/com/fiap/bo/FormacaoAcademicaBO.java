package br.com.fiap.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.dao.FormacaoAcademicaDAO;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.FormacaoAcademica;

/**
 * Classe que representa o Business Object da classe Forma��o Acad�mica
 * 
 * @author Luisa Purifica��o
 * @version 1.0
 */
public class FormacaoAcademicaBO {

	/**
	 * Cria um Data Access Object da Forma��o acad�mica
	 */
	private FormacaoAcademicaDAO dao;

	/**
	 * Cria uma conex�o com o banco de dados
	 */
	private Connection con;

	/**
	 * M�todo que armazena a forma��o acad�mica recebida como par�metro no banco de
	 * dados
	 * 
	 * @param formAcad Forma��o acad�mica que vai ser criada
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
	 * M�todo que l� a forma��o acad�mica de acordo com seu c�digo no banco de dados
	 * 
	 * @param id ID da forma��o acad�mica
	 * @return Forma��o acad�mica
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
	 * M�todo que l� a forma��o acad�mica de acordo com o c�digo de seu candidato no
	 * banco de dados
	 * 
	 * @param id C�digo do candidato
	 * @return Lista de Experi�ncias Profissionais com o c�digo recebido
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
	 * M�todo que atualiza as informa��es de uma forma��o acad�mica que j� est�
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
	 * M�todo que remove uma forma��o acad�mica do banco de dados
	 * 
	 * @param id C�digo da empresa que vai ser removida
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
