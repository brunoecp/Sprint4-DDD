package br.com.fiap.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.dao.ExperienciaProfissionalDAO;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.ExperienciaProfissional;

/**
 * Classe que representa o Business Object da classe Experiência Profissional
 * 
 * @author Luisa Purificação
 * @version 1.0
 */
public class ExperienciaProfissionalBO {

	/**
	 * Cria um Data Access Object da Experiência profissional
	 */
	private ExperienciaProfissionalDAO dao;

	/**
	 * Cria uma conexão com o banco de dados
	 */
	private Connection con;

	/**
	 * Método que armazena a experiência profissional recebida como parâmetro no
	 * banco de dados
	 * 
	 * @param expProfissional Experiência profissional que vai ser criada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void cadastrar(ExperienciaProfissional expProfissional) throws ClassNotFoundException, SQLException {

		con = ConnectionFactory.getConnection();
		dao = new ExperienciaProfissionalDAO(con);

		// REGRAS DE NEGOCIO

		dao.create(expProfissional);

	}
	
	/**
	 * Método que lê a experiência profissional de acordo com seu código no banco de dados
	 * 
	 * @param id ID da experiência profissional
	 * @return Experiência profissional
	 * @throws SQLException
	 * @throws IdNotFoundException
	 * @throws ClassNotFoundException
	 */
	public ExperienciaProfissional listar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {
		
		con = ConnectionFactory.getConnection();
		dao = new ExperienciaProfissionalDAO(con);

		// REGRAS DE NEGOCIO

		return dao.readById(id);
		
	}

	/**
	 * Método que lê a experiência profissional de acordo com o código de seu
	 * candidato no banco de dados
	 * 
	 * @param id Código do candidato
	 * @return Lista de Experiências Profissionais com o código recebido
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public List<ExperienciaProfissional> listarCandidato(int id)
			throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new ExperienciaProfissionalDAO(con);

		// REGRAS DE NEGOCIO

		return dao.readByCandidatoId(id);

	}

	/**
	 * Método que atualiza as informações de uma experiência profissional que já
	 * está armazenada no banco de dados
	 * 
	 * @param expProfissional ExperienciaProfissional
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void atualizar(ExperienciaProfissional expProfissional)
			throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new ExperienciaProfissionalDAO(con);

		// REGRAS DE NEGOCIO

		dao.update(expProfissional);

	}

	/**
	 * Método que remove uma experiência profissional do banco de dados
	 * 
	 * @param id Código da empresa que vai ser removida
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void remover(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new ExperienciaProfissionalDAO(con);

		// REGRAS DE NEGOCIO

		dao.delete(id);

	}

}
