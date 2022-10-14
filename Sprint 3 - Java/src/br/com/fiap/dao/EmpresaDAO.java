package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.model.Empresa;

/**
 * Classe que representa o Data Access Object da classe Empresa
 * @author Luisa Purifica��o
 * @version 1.0
 */
public class EmpresaDAO {

	/**
	 * Cria uma conex�o com o banco de dados
	 */
	private Connection con;

	/**
	 * Construtor que recebe como par�metro uma conex�o com o banco de dados 
	 * @param con Conex�o com o banco de dados
	 */
	public EmpresaDAO(Connection con) {
		this.con = con;
	}

	/**
	 * M�todo que armazena a empresa recebida como par�metro no banco de dados
	 * @param empresa Empresa que vai ser armazenada
	 * @throws SQLException 
	 */
	public void create(Empresa empresa) throws SQLException {

		PreparedStatement stmt = con.prepareStatement(
				"insert into dtc_empresa values (sq_dtc_empresa.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				new String[] { "cd_empresa" });

		stmt.setString(1, empresa.getNome());
		stmt.setLong(2, empresa.getCnpj());
		stmt.setString(3, empresa.getAreaProfissional());
		stmt.setString(4, empresa.getSenha());
		stmt.setLong(5, empresa.getTelefone());
		stmt.setString(6, empresa.getEmail());
		stmt.setString(7, empresa.getCeo());
		stmt.setString(8, empresa.getSite());
		stmt.setString(9, empresa.getYoutube());

		stmt.executeUpdate();

		ResultSet resultSet = stmt.getGeneratedKeys();

		if (resultSet.next()) {
			int id = resultSet.getInt(1);
			empresa.setId(id);
		}
		con.close();
	}

	/**
	 * M�todo que l� as empresas que est�o armazenadas no banco de dados
	 * @return Lista de empresas
	 * @throws SQLException
	 */
	public List<Empresa> readAll() throws SQLException {

		PreparedStatement stmt = con.prepareStatement("select * from dtc_empresa");

		ResultSet resultSet = stmt.executeQuery();

		return parseList(resultSet);
		con.close();
	}

	/**
	 * M�todo que l� a empresa de acordo com seu c�digo no banco de dados
	 * @param id C�digo da empresa
	 * @return Empresa com o c�digo recebido
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public Empresa readById(int id) throws SQLException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement("select * from dtc_empresa where cd_empresa = ?");

		stmt.setInt(1, id);

		ResultSet resultSet = stmt.executeQuery();

		if (!resultSet.next())
			throw new IdNotFoundException("O ID da empresa não foi encontrado.");

		return parse(resultSet);
		con.close();
	}

	/**
	 * M�todo que l� a empresa de acordo com seu nome no banco de dados
	 * @param nome Nome da empresa
	 * @return Empresa com o nome parecido com o que recebeu
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public List<Empresa> readByName(String nome) throws SQLException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement("select * from dtc_empresa where nm_empresa like ?");

		stmt.setString(1, "%" + nome + "%");

		ResultSet resultSet = stmt.executeQuery();

		if (!resultSet.next())
			throw new IdNotFoundException("Nenhuma empresa com esse nome foi encontrada.");

		return parseList(resultSet);
		con.close();
	}

	/**
	 * M�todo que atualiza as informa��es de uma empresa que j� est� armazenada no banco de dados
	 * @param empresa Empresa
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void update(Empresa empresa) throws SQLException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement(
				"update dtc_empresa set nm_empresa = ?, nr_cnpj = ?, ds_area_trabalho = ?, ds_senha = ?, nr_telefone = ?, ds_email = ?, ds_ceo = ?, ds_site = ?, ds_youtube = ? where cd_empresa = ?");

		stmt.setString(1, empresa.getNome());
		stmt.setLong(2, empresa.getCnpj());
		stmt.setString(3, empresa.getAreaProfissional());
		stmt.setString(4, empresa.getSenha());
		stmt.setLong(5, empresa.getTelefone());
		stmt.setString(6, empresa.getEmail());
		stmt.setString(7, empresa.getCeo());
		stmt.setString(8, empresa.getSite());
		stmt.setString(9, empresa.getYoutube());
		stmt.setInt(10, empresa.getId());

		int qtd = stmt.executeUpdate();

		if (qtd == 0)
			throw new IdNotFoundException("O ID da empresa n�o foi encontrado.");
		con.close();
	}

	/**
	 * M�todo que remove uma empresa do banco de dados
	 * @param id C�digo da empresa que vai ser removida
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void delete(int id) throws SQLException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement("delete from dtc_empresa where cd_empresa = ?");

		stmt.setInt(1, id);

		int qtd = stmt.executeUpdate();

		if (qtd == 0)
			throw new IdNotFoundException("O ID da empresa n�o foi encontrado.");
		con.close();
	}

	/**
	 * M�todo que recebe um resultSet e retorna uma empresa
	 * @param resultSet resultSet
	 * @return Empresa
	 * @throws SQLException
	 */
	private Empresa parse(ResultSet resultSet) throws SQLException {

		int id = resultSet.getInt(1);
		String nome = resultSet.getString(2);
		Long cnpj = resultSet.getLong(3);
		String areaTrabalho = resultSet.getString(4);
		String senha = resultSet.getString(5);
		Long telefone = resultSet.getLong(6);
		String email = resultSet.getString(7);
		String ceo = resultSet.getString(8);
		String site = resultSet.getString(9);
		String youtube = resultSet.getString(10);

		Empresa empresa = new Empresa(id, nome, cnpj, areaTrabalho, senha, telefone, email, ceo, site, youtube);

		return empresa;
		
	}

	/**
	 * M�todo que recebe um resultSet e retorna uma lista de empresas
	 * @param resultSet resultSet
	 * @return Lista de Empresas
	 * @throws SQLException
	 */
	private List<Empresa> parseList(ResultSet resultSet) throws SQLException {

		List<Empresa> lista = new ArrayList<Empresa>();

		while (resultSet.next()) {
			Empresa empresa = parse(resultSet);
			lista.add(empresa);
		}

		return lista;

	}

}
