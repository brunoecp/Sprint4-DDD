package br.com.fiap.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.dao.ExperienciaProfissionalDAO;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.ExperienciaProfissional;

/**
 * Classe que representa o Business Object da classe Experi�ncia Profissional
 * 
 * @author Luisa Purifica��o
 * @version 1.0
 */
public class ExperienciaProfissionalBO {

	/**
	 * Cria um Data Access Object da Experi�ncia profissional
	 */
	private ExperienciaProfissionalDAO dao;

	/**
	 * Cria uma conex�o com o banco de dados
	 */
	private Connection con;

	/**
	 * M�todo que armazena a experi�ncia profissional recebida como par�metro no
	 * banco de dados
	 * 
	 * @param expProfissional Experi�ncia profissional que vai ser criada
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
	 * M�todo que l� a experi�ncia profissional de acordo com seu c�digo no banco de dados
	 * 
	 * @param id ID da experi�ncia profissional
	 * @return Experi�ncia profissional
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
	 * M�todo que l� a experi�ncia profissional de acordo com o c�digo de seu
	 * candidato no banco de dados
	 * 
	 * @param id C�digo do candidato
	 * @return Lista de Experi�ncias Profissionais com o c�digo recebido
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
	 * M�todo que atualiza as informa��es de uma experi�ncia profissional que j�
	 * est� armazenada no banco de dados
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
	 * M�todo que remove uma experi�ncia profissional do banco de dados
	 * 
	 * @param id C�digo da empresa que vai ser removida
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
