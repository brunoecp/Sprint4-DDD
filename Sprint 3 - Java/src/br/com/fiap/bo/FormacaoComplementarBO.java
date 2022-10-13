package br.com.fiap.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.dao.FormacaoComplementarDAO;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.FormacaoComplementar;

/**
 * Classe que representa o Business Object da classe Formação Complementar
 * 
 * @author Luisa Purificação
 * @version 1.0
 */
public class FormacaoComplementarBO {

	/**
	 * Cria um Data Access Object da Formação complementar
	 */
	private FormacaoComplementarDAO dao;

	/**
	 * Cria uma conexão com o banco de dados
	 */
	private Connection con;

	/**
	 * Método que armazena a formação complementar recebida como parâmetro no banco
	 * de dados
	 * 
	 * @param formComp Formação complementar que vai ser criada
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
	 * Método que lê a formação complementar de acordo com seu código no banco de
	 * dados
	 * 
	 * @param id ID da formação complementar
	 * @return Formação complementar
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
	 * Método que lê a formação complementar de acordo com o código de seu candidato
	 * no banco de dados
	 * 
	 * @param id Código do candidato
	 * @return Lista de Experiências Profissionais com o código recebido
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
	 * Método que atualiza as informações de uma formação complementar que já está
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
	 * Método que remove uma formação complementar do banco de dados
	 * 
	 * @param id Código da empresa que vai ser removida
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
