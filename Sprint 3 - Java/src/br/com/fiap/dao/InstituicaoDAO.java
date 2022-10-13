package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.model.Instituicao;

/**
 * Classe que representa o Data Access Object da classe Institui��o
 * 
 * @author Luisa Purifica��o
 * @version 1.0
 */
public class InstituicaoDAO {

	/**
	 * Cria uma conex�o com o banco de dados
	 */
	private Connection con;

	/**
	 * Construtor que recebe como par�metro uma conex�o com o banco de dados
	 * 
	 * @param con Conex�o com o banco de dados
	 */
	public InstituicaoDAO(Connection con) {
		this.con = con;
	}

	/**
	 * M�todo que armazena a institui��o recebida como par�metro no banco de dados
	 * 
	 * @param instituicao Institui��o que vai ser armazenada
	 * @throws SQLException
	 */
	public void create(Instituicao instituicao) throws SQLException {

		PreparedStatement stmt = con.prepareStatement(
				"insert into dtc_instituicao values (sq_dtc_instituicao.nextval, ?, ?, ?)",
				new String[] { "cd_instituicao" });

		stmt.setLong(1, instituicao.getCnpj());
		stmt.setString(2, instituicao.getNome());
		stmt.setString(3, instituicao.getEndereco());

		stmt.executeUpdate();

		ResultSet resultSet = stmt.getGeneratedKeys();

		if (resultSet.next()) {
			int id = resultSet.getInt(1);
			instituicao.setId(id);
		}
		con.close();
	}

	/**
	 * M�todo que l� as institui��es que est�o armazenadas no banco de dados
	 * 
	 * @return Lista de institui��es
	 * @throws SQLException
	 */
	public List<Instituicao> readAll() throws SQLException {

		PreparedStatement stmt = con.prepareStatement("select * from dtc_instituicao");

		ResultSet resultSet = stmt.executeQuery();

		return parseList(resultSet);
		con.close();
	}

	/**
	 * M�todo que l� a institui��o de acordo com seu c�digo no banco de dados
	 * 
	 * @param id C�digo da institui��o
	 * @return Institui��o
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public Instituicao readById(int id) throws SQLException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement("select * from dtc_instituicao where cd_instituicao = ?");

		stmt.setInt(1, id);

		ResultSet resultSet = stmt.executeQuery();

		if (!resultSet.next())
			throw new IdNotFoundException("O ID da institui��o n�o foi encontrado.");

		return parse(resultSet);
		con.close();
	}

	/**
	 * M�todo que atualiza as informa��es de uma institui��o que j� est� armazenada
	 * no banco de dados
	 * 
	 * @param instituicao Instituicao
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void update(Instituicao instituicao) throws SQLException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement(
				"update dtc_instituicao set nr_cnpj = ?, nm_instituicao = ?, ds_endereco = ? where cd_instituicao = ?");

		stmt.setLong(1, instituicao.getCnpj());
		stmt.setString(2, instituicao.getNome());
		stmt.setString(3, instituicao.getEndereco());
		stmt.setInt(4, instituicao.getId());

		int qtd = stmt.executeUpdate();

		if (qtd == 0)
			throw new IdNotFoundException("O ID da institui��o n�o foi encontrado.");
		con.close();
	}

	/**
	 * M�todo que remove uma institui��o do banco de dados
	 * 
	 * @param id C�digo da institui��o que vai ser removida
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void delete(int id) throws SQLException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement("delete from dtc_instituicao where cd_instituicao = ?");

		stmt.setInt(1, id);

		int qtd = stmt.executeUpdate();

		if (qtd == 0)
			throw new IdNotFoundException("O ID da institui��o n�o foi encontrado.");
		con.close();
	}

	/**
	 * M�todo que recebe um resultSet e retorna uma institui��o
	 * 
	 * @param resultSet resultSet
	 * @return Institui��o
	 * @throws SQLException
	 */
	private Instituicao parse(ResultSet resultSet) throws SQLException {

		int id = resultSet.getInt(1);
		Long cnpj = resultSet.getLong(2);
		String nome = resultSet.getString(3);
		String endereco = resultSet.getString(4);

		Instituicao instituicao = new Instituicao(id, cnpj, nome, endereco);

		return instituicao;

	}

	/**
	 * M�todo que recebe um resultSet e retorna uma lista de institui��es
	 * 
	 * @param resultSet resultSet
	 * @return Lista de Institui��es
	 * @throws SQLException
	 */
	private List<Instituicao> parseList(ResultSet resultSet) throws SQLException {

		List<Instituicao> lista = new ArrayList<Instituicao>();

		while (resultSet.next()) {
			Instituicao instituicao = parse(resultSet);
			lista.add(instituicao);
		}

		return lista;

	}

}
