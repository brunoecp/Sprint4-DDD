package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.model.Empresa;
import br.com.fiap.model.Vaga;

/**
 * Classe que representa o Data Access Object da classe Vaga
 * 
 * @author Luisa Purifica��o
 * @version 1.0
 */
public class VagaDAO {

	/**
	 * Cria uma conex�o com o banco de dados
	 */
	private Connection con;

	/**
	 * Construtor que recebe como par�metro uma conex�o com o banco de dados
	 * 
	 * @param con Conex�o com o banco de dados
	 */
	public VagaDAO(Connection con) {
		this.con = con;
	}

	/**
	 * M�todo que armazena a vaga recebida como par�metro no banco de dados
	 * 
	 * @param vaga Vaga que vai ser armazenada
	 * @throws SQLException
	 */
	public void create(Vaga vaga) throws SQLException {

		PreparedStatement stmt = con.prepareStatement(
				"insert into dtc_vaga values (sq_dtc_vaga.nextval, ?, ?, ?, ?, ?, ?, ?)", new String[] { "id_vaga" });

		stmt.setInt(1, vaga.getEmpresa().getId());
		stmt.setString(2, vaga.getCargo());
		stmt.setString(3, vaga.getNivel());
		stmt.setString(4, vaga.getLocalizacao());
		stmt.setDouble(5, vaga.getSalario());
		stmt.setString(6, vaga.getRequisitos());
		stmt.setString(7, vaga.getTipo());

		stmt.executeUpdate();

		ResultSet resultSet = stmt.getGeneratedKeys();

		if (resultSet.next()) {
			int id = resultSet.getInt(1);
			vaga.setId(id);
		}
		con.close();
	}

	/**
	 * M�todo que l� as vagas que est�o armazenadas no banco de dados
	 * 
	 * @return Lista de vagas
	 * @throws SQLException
	 */
	public List<Vaga> readAll() throws SQLException {

		PreparedStatement stmt = con
				.prepareStatement("select * from dtc_vaga v inner join dtc_empresa e on v.cd_empresa = e.cd_empresa");

		ResultSet resultSet = stmt.executeQuery();

		return parseList(resultSet);
		con.close();
	}

	/**
	 * M�todo que l� a vaga de acordo com seu cargo no banco de dados
	 * 
	 * @param cargo Cargo da vaga
	 * @return Vaga com o cargo parecido com o que recebeu
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public List<Vaga> readByCargo(String cargo) throws SQLException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement(
				"select * from dtc_vaga v inner join dtc_empresa e on v.cd_empresa = e.cd_empresa where ds_cargo like ?");

		stmt.setString(1, "%" + cargo + "%");

		ResultSet resultSet = stmt.executeQuery();

		if (!resultSet.next())
			throw new IdNotFoundException("Nenhuma vaga com esse cargo foi encontrada.");

		return parseList(resultSet);
		con.close();
	}

	/**
	 * M�todo que l� a vaga de acordo com o ID da empresa no banco de dados
	 * 
	 * @param id Id da empresa
	 * @return Vaga com o cargo parecido com o que recebeu
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public List<Vaga> readByIdEmpresa(int id) throws SQLException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement(
				"select * from dtc_vaga v inner join dtc_empresa e on v.cd_empresa = e.cd_empresa where v.cd_empresa = ?");

		stmt.setInt(1, id);

		ResultSet resultSet = stmt.executeQuery();

		if (!resultSet.next())
			throw new IdNotFoundException("Nenhuma vaga dessa empresa foi encontrada.");

		return parseList(resultSet);
		con.close();
	}

	/**
	 * M�todo que atualiza as informa��es de uma vaga que j� est� armazenada no
	 * banco de dados
	 * 
	 * @param vaga Vaga
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void update(Vaga vaga) throws SQLException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement(
				"update dtc_vaga set cd_empresa = ?, ds_cargo = ?, ds_nivel = ?, ds_localizacao = ?, vl_salario = ?, ds_requisitos_vaga = ?, ds_tipo = ?");

		stmt.setInt(1, vaga.getEmpresa().getId());
		stmt.setString(2, vaga.getCargo());
		stmt.setString(3, vaga.getNivel());
		stmt.setString(4, vaga.getLocalizacao());
		stmt.setDouble(5, vaga.getSalario());
		stmt.setString(6, vaga.getRequisitos());
		stmt.setString(7, vaga.getTipo());

		int qtd = stmt.executeUpdate();

		if (qtd == 0)
			throw new IdNotFoundException("O ID da vaga n�o foi encontrado.");
		con.close();
	}

	/**
	 * M�todo que remove uma vaga do banco de dados
	 * 
	 * @param id C�digo da vaga que vai ser removida
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void delete(int id) throws SQLException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement("delete from dtc_vaga where id_vaga = ?");

		stmt.setInt(1, id);

		int qtd = stmt.executeUpdate();

		if (qtd == 0)
			throw new IdNotFoundException("O ID da vaga n�o foi encontrado.");
		con.close();
	}

	/**
	 * M�todo que recebe um resultSet e retorna uma vaga
	 * 
	 * @param resultSet resultSet
	 * @return Vaga vaga
	 * @throws SQLException
	 */
	private Vaga parse(ResultSet resultSet) throws SQLException {

		int id = resultSet.getInt(1);
		int idEmpresa = resultSet.getInt(2);
		String cargo = resultSet.getString(3);
		String nivel = resultSet.getString(4);
		String localizacao = resultSet.getString(5);
		double salario = resultSet.getDouble(6);
		String requisitos = resultSet.getString(7);
		String tipo = resultSet.getString(8);
		String nomeEmpresa = resultSet.getString(10);

		Empresa empresa = new Empresa();
		empresa.setId(idEmpresa);
		empresa.setNome(nomeEmpresa);

		Vaga vaga = new Vaga(id, empresa, cargo, nivel, localizacao, salario, requisitos, tipo);

		return vaga;

	}

	/**
	 * M�todo que recebe um resultSet e retorna uma lista de vagas
	 * 
	 * @param resultSet resultSet
	 * @return Lista de Vagas
	 * @throws SQLException
	 */
	private List<Vaga> parseList(ResultSet resultSet) throws SQLException {

		List<Vaga> lista = new ArrayList<Vaga>();

		while (resultSet.next()) {
			Vaga vaga = parse(resultSet);
			lista.add(vaga);
		}

		return lista;

	}

}
