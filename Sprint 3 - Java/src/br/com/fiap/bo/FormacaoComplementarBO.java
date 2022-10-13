package br.com.fiap.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.dao.FormacaoComplementarDAO;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.FormacaoComplementar;

/**
 * Classe que representa o Business Object da classe Forma��o Complementar
 * 
 * @author Luisa Purifica��o
 * @version 1.0
 */
public class FormacaoComplementarBO {

	/**
	 * Cria um Data Access Object da Forma��o complementar
	 */
	private FormacaoComplementarDAO dao;

	/**
	 * Cria uma conex�o com o banco de dados
	 */
	private Connection con;

	/**
	 * M�todo que armazena a forma��o complementar recebida como par�metro no banco
	 * de dados
	 * 
	 * @param formComp Forma��o complementar que vai ser criada
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void cadastrar(FormacaoComplementar formComp) throws ClassNotFoundException, SQLException {

		con = ConnectionFactory.getConnection();
		dao = new FormacaoComplementarDAO(con);

		// REGRAS DE NEGOCIO

		dao.create(formComp);

	}

	/**
	 * M�todo que l� a forma��o complementar de acordo com seu c�digo no banco de
	 * dados
	 * 
	 * @param id ID da forma��o complementar
	 * @return Forma��o complementar
	 * @throws SQLException
	 * @throws IdNotFoundException
	 * @throws ClassNotFoundException
	 */
	public FormacaoComplementar listar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new FormacaoComplementarDAO(con);

		// REGRAS DE NEGOCIO

		return dao.readById(id);

	}

	/**
	 * M�todo que l� a forma��o complementar de acordo com o c�digo de seu candidato
	 * no banco de dados
	 * 
	 * @param id C�digo do candidato
	 * @return Lista de Experi�ncias Profissionais com o c�digo recebido
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public List<FormacaoComplementar> listarCandidato(int id)
			throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new FormacaoComplementarDAO(con);

		// REGRAS DE NEGOCIO

		return dao.readByCandidatoId(id);

	}

	/**
	 * M�todo que atualiza as informa��es de uma forma��o complementar que j� est�
	 * armazenada no banco de dados
	 * 
	 * @param formComp FormacaoComplementar
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void atualizar(FormacaoComplementar formComp)
			throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new FormacaoComplementarDAO(con);

		// REGRAS DE NEGOCIO

		dao.update(formComp);

	}

	/**
	 * M�todo que remove uma forma��o complementar do banco de dados
	 * 
	 * @param id C�digo da empresa que vai ser removida
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void remover(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new FormacaoComplementarDAO(con);

		// REGRAS DE NEGOCIO

		dao.delete(id);

	}

}
