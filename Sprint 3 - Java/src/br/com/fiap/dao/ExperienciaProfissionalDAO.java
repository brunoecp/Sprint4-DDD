package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.bo.CandidatoBO;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.model.Candidato;
import br.com.fiap.model.ExperienciaProfissional;

/**
 * Classe que representa o Data Access Object da classe Experi�ncia Profissional
 * 
 * @author Luisa Purifica��o
 * @version 1.0
 */
public class ExperienciaProfissionalDAO {

	/**
	 * Cria uma conex�o com o banco de dados
	 */
	private Connection con;

	/**
	 * Construtor que recebe como par�metro uma conex�o com o banco de dados
	 * 
	 * @param con Conex�o com o banco de dados
	 */
	public ExperienciaProfissionalDAO(Connection con) {
		this.con = con;
	}

	/**
	 * M�todo que armazena a experi�ncia profissional de um candidato recebida como
	 * par�metro no banco de dados
	 * 
	 * @param expProfissional Experi�ncia profissional de um candidato
	 * @throws SQLException
	 */
	public void create(ExperienciaProfissional expProfissional) throws SQLException {

		PreparedStatement stmt = con.prepareStatement(
				"insert into dtc_exp_profissional values (sq_dtc_exp_profissional.nextval, ?, ?, to_date(?, 'dd/mm/yyyy'), to_date(?, 'dd/mm/yyyy'), ?)",
				new String[] { "id_exp_profissional" });

		stmt.setInt(1, expProfissional.getCandidato().getId());
		stmt.setString(2, expProfissional.getEmpresa());
		stmt.setString(3, expProfissional.getDataInicio());
		stmt.setString(4, expProfissional.getDataTermino());
		stmt.setString(5, expProfissional.getAreaAtuacao());

		stmt.executeUpdate();

		ResultSet resultSet = stmt.getGeneratedKeys();

		if (resultSet.next()) {
			int id = resultSet.getInt(1);
			expProfissional.setId(id);
		}
		con.close();
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
	public ExperienciaProfissional readById(int id) throws SQLException, IdNotFoundException, ClassNotFoundException {

		PreparedStatement stmt = con.prepareStatement("select * from dtc_exp_profissional where id_exp_profissional = ?");

		stmt.setInt(1, id);

		ResultSet resultSet = stmt.executeQuery();

		if (!resultSet.next())
			throw new IdNotFoundException("O ID da experi�ncia profissional n�o foi encontrado.");

		return parse(resultSet);
		con.close();
	}

	/**
	 * M�todo que l� a experi�ncia profissional de acordo com o c�digo de seu
	 * candidato no banco de dados
	 * 
	 * @param id
	 * @return Lista de experi�ncias profissionais
	 * @throws SQLException
	 * @throws IdNotFoundException
	 * @throws ClassNotFoundException
	 */
	public List<ExperienciaProfissional> readByCandidatoId(int id)
			throws SQLException, IdNotFoundException, ClassNotFoundException {

		PreparedStatement stmt = con.prepareStatement("select * from dtc_exp_profissional where cd_candidato = ?");

		stmt.setInt(1, id);

		ResultSet resultSet = stmt.executeQuery();

		if (!resultSet.next())
			throw new IdNotFoundException("A experi�ncia profissional do candidato n�o foi encontrado.");

		return parseList(resultSet);
		con.close();
	}

	/**
	 * M�todo que atualiza as informa��es de uma experi�ncia profissional que j�
	 * est� armazenada no banco de dados
	 * 
	 * @param expProfissional ExperienciaProfissional
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void update(ExperienciaProfissional expProfissional) throws SQLException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement(
				"update dtc_exp_profissional set cd_candidato = ?, nm_empresa = ?, dt_inicio = ?, dt_termino = ?, ds_setor_atuacao = ? where id_exp_profissional = ?");

		stmt.setInt(1, expProfissional.getCandidato().getId());
		stmt.setString(2, expProfissional.getEmpresa());
		stmt.setString(3, expProfissional.getDataInicio());
		stmt.setString(4, expProfissional.getDataTermino());
		stmt.setString(5, expProfissional.getAreaAtuacao());
		stmt.setInt(6, expProfissional.getId());

		int qtd = stmt.executeUpdate();

		if (qtd == 0)
			throw new IdNotFoundException("O ID da experi�ncia profissional n�o foi encontrado.");
		con.close();
	}

	/**
	 * M�todo que remove uma experi�ncia profissional do banco de dados
	 * 
	 * @param id C�digo da empresa que vai ser removida
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void delete(int id) throws SQLException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement("delete from dtc_exp_profissional where id_exp_profissional = ?");

		stmt.setInt(1, id);

		int qtd = stmt.executeUpdate();

		if (qtd == 0)
			throw new IdNotFoundException("O ID da experi�ncia profissional n�o foi encontrado.");

	}
		con.close();
	/**
	 * M�todo que recebe um resultSet e retorna uma experi�ncia profissional
	 * 
	 * @param resultSet resultSet
	 * @return ExperienciaProfissional
	 * @throws SQLException
	 * @throws IdNotFoundException
	 * @throws ClassNotFoundException
	 */
	private ExperienciaProfissional parse(ResultSet resultSet)
			throws SQLException, ClassNotFoundException, IdNotFoundException {

		int id = resultSet.getInt(1);
		int idCandidato = resultSet.getInt(2);
		String nome = resultSet.getString(3);
		String dataInicio = resultSet.getString(4);
		String dataTermino = resultSet.getString(5);
		String areaAtuacao = resultSet.getString(6);

		CandidatoBO candidatoBO = new CandidatoBO();
		Candidato candidato = candidatoBO.listar(idCandidato);

		ExperienciaProfissional expProfissional = new ExperienciaProfissional(id, candidato, nome, dataInicio,
				dataTermino, areaAtuacao);

		return expProfissional;

	}

	/**
	 * M�todo que recebe um resultSet e retorna uma lista de experi�ncias
	 * profissionais
	 * 
	 * @param resultSet resultSet
	 * @return Lista de Experi�ncias profissionais
	 * @throws SQLException
	 * @throws IdNotFoundException
	 * @throws ClassNotFoundException
	 */
	private List<ExperienciaProfissional> parseList(ResultSet resultSet)
			throws SQLException, ClassNotFoundException, IdNotFoundException {

		List<ExperienciaProfissional> lista = new ArrayList<ExperienciaProfissional>();

		while (resultSet.next()) {
			ExperienciaProfissional expProfissional = parse(resultSet);
			lista.add(expProfissional);
		}

		return lista;

	}

}
