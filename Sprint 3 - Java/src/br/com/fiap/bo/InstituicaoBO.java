package br.com.fiap.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.dao.InstituicaoDAO;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Instituicao;

/**
 * Classe que representa o Business Object da classe Instituição
 * 
 * @author Luisa Purificação
 * @version 1.0
 */
public class InstituicaoBO {

	/**
	 * Cria um Data Access Object da Empresa
	 */
	private InstituicaoDAO dao;

	/**
	 * Cria uma conexão com o banco de dados
	 */
	private Connection con;

	/**
	 * Método que armazena a instituição recebida como parâmetro no banco de dados
	 * 
	 * @param instituicao Instituição que vai ser armazenada
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
	 * Método que lê as instituições que estão armazenadas no banco de dados
	 * 
	 * @return Lista de instituições
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
	 * Método que lê a instituição de acordo com seu código no banco de dados
	 * 
	 * @param id Código da instituição
	 * @return Instituição
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
	 * Método que atualiza as informações de uma instituição que já está armazenada
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
	 * Método que remove uma instituição do banco de dados
	 * 
	 * @param id Código da instituição que vai ser removida
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
