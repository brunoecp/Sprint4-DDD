package br.com.fiap.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.dao.EmpresaDAO;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Empresa;

/**
 * Classe que representa o Business Object da classe Empresa
 * 
 * @author Luisa Purifica��o
 * @version 1.0
 */
public class EmpresaBO {

	/**
	 * Cria um Data Access Object da Empresa
	 */
	private EmpresaDAO dao;

	/**
	 * Cria uma conex�o com o banco de dados
	 */
	private Connection con;

	/**
	 * M�todo que armazena a empresa recebida como par�metro no banco de dados
	 * 
	 * @param empresa Empresa que vai ser criada
	 * @throws ClassNotFoundException 
	 * @throws SQLException
	 */
	public void cadastrar(Empresa empresa) throws ClassNotFoundException, SQLException {

		con = ConnectionFactory.getConnection();
		dao = new EmpresaDAO(con);

		// REGRAS DE NEGOCIO

		dao.create(empresa);

	}

	/**
	 * M�todo que l� as empresas que est�o armazenadas no banco de dados
	 * 
	 * @return Lista de empresas
	 * @throws ClassNotFoundException 
	 * @throws SQLException
	 */
	public List<Empresa> listar() throws ClassNotFoundException, SQLException {

		con = ConnectionFactory.getConnection();
		dao = new EmpresaDAO(con);

		// REGRAS DE NEGOCIO

		return dao.readAll();

	}

	/**
	 * M�todo que l� a empresa de acordo com seu c�digo no banco de dados
	 * 
	 * @param id C�digo da empresa
	 * @return Empresa com o c�digo recebido
	 * @throws ClassNotFoundException 
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public Empresa listar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new EmpresaDAO(con);

		// REGRAS DE NEGOCIO

		return dao.readById(id);

	}

	/**
	 * M�todo que l� a empresa de acordo com seu nome no banco de dados
	 * 
	 * @param nome Nome da empresa
	 * @return Empresa com o nome parecido com o que recebeu
	 * @throws ClassNotFoundException 
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public List<Empresa> listar(String nome) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new EmpresaDAO(con);

		// REGRAS DE NEGOCIO

		return dao.readByName(nome);

	}

	/**
	 * M�todo que atualiza as informa��es de uma empresa que j� est� armazenada no
	 * banco de dados
	 * 
	 * @param empresa Empresa
	 * @throws ClassNotFoundException 
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void atualizar(Empresa empresa) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new EmpresaDAO(con);

		// REGRAS DE NEGOCIO

		dao.update(empresa);

	}

	/**
	 * M�todo que remove uma empresa do banco de dados
	 * 
	 * @param id C�digo da empresa que vai ser removida
	 * @throws ClassNotFoundException 
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void remover(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new EmpresaDAO(con);

		// REGRAS DE NEGOCIO

		dao.delete(id);

	}

}
