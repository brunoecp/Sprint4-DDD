package br.com.fiap.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.dao.InstituicaoDAO;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Instituicao;

/**
 * Classe que representa o Business Object da classe Institui��o
 * 
 * @author Luisa Purifica��o
 * @version 1.0
 */
public class InstituicaoBO {

	/**
	 * Cria um Data Access Object da Empresa
	 */
	private InstituicaoDAO dao;

	/**
	 * Cria uma conex�o com o banco de dados
	 */
	private Connection con;

	/**
	 * M�todo que armazena a institui��o recebida como par�metro no banco de dados
	 * 
	 * @param instituicao Institui��o que vai ser armazenada
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void cadastrar(Instituicao instituicao) throws SQLException, ClassNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new InstituicaoDAO(con);

		// REGRAS DE NEGOCIO

		dao.create(instituicao);

	}

	/**
	 * M�todo que l� as institui��es que est�o armazenadas no banco de dados
	 * 
	 * @return Lista de institui��es
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Instituicao> listar() throws ClassNotFoundException, SQLException {

		con = ConnectionFactory.getConnection();
		dao = new InstituicaoDAO(con);

		// REGRAS DE NEGOCIO

		return dao.readAll();

	}

	/**
	 * M�todo que l� a institui��o de acordo com seu c�digo no banco de dados
	 * 
	 * @param id C�digo da institui��o
	 * @return Institui��o
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public Instituicao listar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new InstituicaoDAO(con);

		// REGRAS DE NEGOCIO

		return dao.readById(id);

	}

	/**
	 * M�todo que atualiza as informa��es de uma institui��o que j� est� armazenada
	 * no banco de dados
	 * 
	 * @param instituicao Instituicao
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void atualizar(Instituicao instituicao) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new InstituicaoDAO(con);

		// REGRAS DE NEGOCIO

		dao.update(instituicao);

	}

	/**
	 * M�todo que remove uma institui��o do banco de dados
	 * 
	 * @param id C�digo da institui��o que vai ser removida
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void remover(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new InstituicaoDAO(con);

		// REGRAS DE NEGOCIO

		dao.delete(id);

	}

}
