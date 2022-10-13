package br.com.fiap.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.dao.IdiomaDAO;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Idioma;

/**
 * Classe que representa o Business Object da classe Idioma
 * 
 * @author Luisa Purificação
 * @version 1.0
 */
public class IdiomaBO {

	/**
	 * Cria um Data Access Object do Idioma
	 */
	private IdiomaDAO dao;

	/**
	 * Cria uma conexão com o banco de dados
	 */
	private Connection con;

	/**
	 * Método que armazena o idioma recebida como parâmetro no banco de dados
	 * 
	 * @param idioma Idioma que vai ser armazenado
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void cadastrar(Idioma idioma) throws ClassNotFoundException, SQLException {

		con = ConnectionFactory.getConnection();
		dao = new IdiomaDAO(con);

		// REGRAS DE NEGOCIO

		dao.create(idioma);

	}

	/**
	 * Método que lê o idioma de acordo com seu código no banco de dados
	 * 
	 * @param id ID do idioma
	 * @return Idioma
	 * @throws SQLException
	 * @throws IdNotFoundException
	 * @throws ClassNotFoundException
	 */
	public Idioma listar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new IdiomaDAO(con);

		// REGRAS DE NEGOCIO

		return dao.readById(id);

	}

	/**
	 * Método que lê o idioma de acordo com o código de seu candidato no banco de
	 * dados
	 * 
	 * @param id Código do candidato
	 * @return Lista de Idiomas com o código recebido
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public List<Idioma> listarCandidato(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new IdiomaDAO(con);

		// REGRAS DE NEGOCIO

		return dao.readByCandidatoId(id);

	}

	/**
	 * Método que atualiza as informações de um idioma que já está armazenada no
	 * banco de dados
	 * 
	 * @param idioma Idioma
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void atualizar(Idioma idioma) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new IdiomaDAO(con);

		// REGRAS DE NEGOCIO

		dao.update(idioma);

	}

	/**
	 * Método que remove um idioma do banco de dados
	 * 
	 * @param id Código do idioma que vai ser removido
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void remover(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new IdiomaDAO(con);

		// REGRAS DE NEGOCIO

		dao.delete(id);

	}

}
