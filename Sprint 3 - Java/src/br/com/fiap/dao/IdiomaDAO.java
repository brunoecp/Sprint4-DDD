package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.bo.CandidatoBO;
import br.com.fiap.bo.InstituicaoBO;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.model.Candidato;
import br.com.fiap.model.Idioma;
import br.com.fiap.model.Instituicao;

/**
 * Classe que representa o Data Access Object da classe Idioma
 * 
 * @author Luisa Purifica��o
 * @version 1.0
 */
public class IdiomaDAO {

	/**
	 * Cria um conex�o com o banco de dados
	 */
	private Connection con;

	/**
	 * Construtor que recebe como par�metro um conex�o com o banco de dados
	 * 
	 * @param con Conex�o com o banco de dados
	 */
	public IdiomaDAO(Connection con) {
		this.con = con;
	}

	/**
	 * M�todo que armazena a idioma de um candidato recebida como par�metro no banco
	 * de dados
	 * 
	 * @param idioma Idioma de um candidato
	 * @throws SQLException
	 */
	public void create(Idioma idioma) throws SQLException {

		PreparedStatement stmt = con.prepareStatement(
				"insert into dtc_idioma values (sq_dtc_idioma.nextval, ?, ?, ?, ?)",
				new String[] { "id_curso_lingua" });

		stmt.setInt(1, idioma.getCandidato().getId());
		stmt.setInt(2, idioma.getInstituicao().getId());
		stmt.setString(3, idioma.getCurso());
		stmt.setString(4, idioma.getNivel());

		stmt.executeUpdate();

		ResultSet resultSet = stmt.getGeneratedKeys();

		if (resultSet.next()) {
			int id = resultSet.getInt(1);
			idioma.setId(id);
		}
		con.close();
	}

	/**
	 * M�todo que l� o idioma de acordo com seu c�digo no banco de dados
	 * 
	 * @param id ID do idioma
	 * @return Idioma
	 * @throws SQLException
	 * @throws IdNotFoundException
	 * @throws ClassNotFoundException
	 */
	public Idioma readById(int id) throws SQLException, IdNotFoundException, ClassNotFoundException {

		PreparedStatement stmt = con.prepareStatement("select * from dtc_idioma where id_curso_lingua = ?");

		stmt.setInt(1, id);

		ResultSet resultSet = stmt.executeQuery();

		if (!resultSet.next())
			throw new IdNotFoundException("O ID do idioma n�o foi encontrado.");

		return parse(resultSet);
		con.close();
	}

	/**
	 * M�todo que l� o idioma de acordo com o c�digo de seu candidato no banco de
	 * dados
	 * 
	 * @param id
	 * @return Lista de idiomas
	 * @throws SQLException
	 * @throws IdNotFoundException
	 * @throws ClassNotFoundException
	 */
	public List<Idioma> readByCandidatoId(int id) throws SQLException, IdNotFoundException, ClassNotFoundException {

		PreparedStatement stmt = con.prepareStatement("select * from dtc_idioma where cd_candidato = ?");

		stmt.setInt(1, id);

		ResultSet resultSet = stmt.executeQuery();

		if (!resultSet.next())
			throw new IdNotFoundException("O idioma do candidato n�o foi encontrado.");

		return parseList(resultSet);
		con.close();
	}

	/**
	 * M�todo que atualiza as informa��es de um idioma que j� est� armazenada no
	 * banco de dados
	 * 
	 * @param idioma Idioma
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void update(Idioma idioma) throws SQLException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement(
				"update dtc_idioma set cd_candidato = ?, cd_instituicao = ?, nm_idioma = ?, ds_nivel = ? where id_curso_lingua = ?");

		stmt.setInt(1, idioma.getCandidato().getId());
		stmt.setInt(2, idioma.getInstituicao().getId());
		stmt.setString(3, idioma.getCurso());
		stmt.setString(4, idioma.getNivel());
		stmt.setInt(5, idioma.getId());

		int qtd = stmt.executeUpdate();

		if (qtd == 0)
			throw new IdNotFoundException("O ID do idioma n�o foi encontrado.");
		con.close();
	}

	/**
	 * M�todo que remove um idioma do banco de dados
	 * 
	 * @param id C�digo do idioma que vai ser removido
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void delete(int id) throws SQLException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement("delete from dtc_idioma where id_curso_lingua = ?");

		stmt.setInt(1, id);

		int qtd = stmt.executeUpdate();

		if (qtd == 0)
			throw new IdNotFoundException("O ID do idioma n�o foi encontrado.");
		con.close();
	}

	/**
	 * M�todo que recebe um resultSet e retorna um idioma
	 * 
	 * @param resultSet resultSet
	 * @return Idioma
	 * @throws SQLException
	 * @throws IdNotFoundException
	 * @throws ClassNotFoundException
	 */
	private Idioma parse(ResultSet resultSet) throws SQLException, ClassNotFoundException, IdNotFoundException {

		int id = resultSet.getInt(1);
		int idCandidato = resultSet.getInt(2);
		int idInstituicao = resultSet.getInt(3);
		String curso = resultSet.getString(4);
		String nivel = resultSet.getString(5);

		CandidatoBO candidatoBO = new CandidatoBO();
		Candidato candidato = candidatoBO.listar(idCandidato);

		InstituicaoBO instituicaoBO = new InstituicaoBO();
		Instituicao instituicao = instituicaoBO.listar(idInstituicao);

		Idioma idioma = new Idioma(id, candidato, instituicao, curso, nivel);

		return idioma;

	}

	/**
	 * M�todo que recebe um resultSet e retorna uma lista de idiomas
	 * 
	 * @param resultSet resultSet
	 * @return Lista de Idiomas
	 * @throws SQLException
	 * @throws IdNotFoundException
	 * @throws ClassNotFoundException
	 */
	private List<Idioma> parseList(ResultSet resultSet)
			throws SQLException, ClassNotFoundException, IdNotFoundException {

		List<Idioma> lista = new ArrayList<Idioma>();

		while (resultSet.next()) {
			Idioma idioma = parse(resultSet);
			lista.add(idioma);
		}

		return lista;

	}

}
	