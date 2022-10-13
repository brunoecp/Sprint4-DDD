package br.com.fiap.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.dao.VagaDAO;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Vaga;

/**
 * Classe que representa o Business Object da classe Vaga
 * 
 * @author Luisa Purificação
 * @version 1.0
 */
public class VagaBO {

	/**
	 * Cria um Data Access Object da Vaga
	 */
	private VagaDAO dao;

	/**
	 * Cria uma conexão com o banco de dados
	 */
	private Connection con;

	/**
	 * Método que armazena a vaga recebida como parâmetro no banco de dados
	 * 
	 * @param vaga Vaga que vai ser armazenada
	 * @throws ClassNotFoundException 
	 * @throws SQLException
	 */
	public void cadastrar(Vaga vaga) throws ClassNotFoundException, SQLException {

		con = ConnectionFactory.getConnection();
		dao = new VagaDAO(con);

		// REGRAS DE NEGOCIO

		dao.create(vaga);

	}

	/**
	 * Método que lê as vagas que estão armazenadas no banco de dados
	 * 
	 * @return Lista de vagas
	 * @throws ClassNotFoundException 
	 * @throws SQLException
	 */
	public List<Vaga> listar() throws ClassNotFoundException, SQLException {

		con = ConnectionFactory.getConnection();
		dao = new VagaDAO(con);

		// REGRAS DE NEGOCIO

		return dao.readAll();

	}

	/**
	 * Método que lê a vaga de acordo com seu cargo no banco de dados
	 * 
	 * @param cargo Cargo da vaga
	 * @return Vaga com o cargo parecido com o que recebeu
	 * @throws ClassNotFoundException 
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public List<Vaga> listar(String cargo) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new VagaDAO(con);

		// REGRAS DE NEGOCIO

		return dao.readByCargo(cargo);

	}
	
	/**
	 * Método que lê a vaga de acordo com o código da empresa no banco de dados
	 * 
	 * @param id Id da empresa
	 * @return Vaga com o id da empresa que recebeu
	 * @throws ClassNotFoundException 
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public List<Vaga> listar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new VagaDAO(con);

		// REGRAS DE NEGOCIO

		return dao.readByIdEmpresa(id);

	}

	/**
	 * Método que atualiza as informações de uma vaga que já está armazenada no
	 * banco de dados
	 * 
	 * @param vaga Vaga
	 * @throws ClassNotFoundException 
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void atualizar(Vaga vaga) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new VagaDAO(con);

		// REGRAS DE NEGOCIO

		dao.update(vaga);

	}

	/**
	 * Método que remove uma vaga do banco de dados
	 * 
	 * @param id Código da vaga que vai ser removida
	 * @throws ClassNotFoundException 
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void remover(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new VagaDAO(con);

		// REGRAS DE NEGOCIO

		dao.delete(id);

	}
	
}
