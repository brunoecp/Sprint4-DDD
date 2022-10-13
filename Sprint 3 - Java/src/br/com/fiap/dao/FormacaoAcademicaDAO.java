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
import br.com.fiap.model.FormacaoAcademica;
import br.com.fiap.model.Instituicao;

/**
 * Classe que representa o Data Access Object da classe Forma��o Acad�mica
 * 
 * @author Luisa Purifica��o
 * @version 1.0
 */
public class FormacaoAcademicaDAO {

	/**
	 * Cria uma conex�o com o banco de dados
	 */
	private Connection con;

	/**
	 * Construtor que recebe como par�metro uma conex�o com o banco de dados
	 * 
	 * @param con Conex�o com o banco de dados
	 */
	public FormacaoAcademicaDAO(Connection con) {
		this.con = con;
	}

	/**
	 * M�todo que armazena a forma��o acad�mica de um candidato recebida como
	 * par�metro no banco de dados
	 * 
	 * @param formAcad Forma��o acad�mica de um candidato
	 * @throws SQLException
	 */
	public void create(FormacaoAcademica formAcad) throws SQLException {

		PreparedStatement stmt = con.prepareStatement(
				"insert into dtc_form_academica values (sq_dtc_form_academica.nextval, ?, ?, ?, to_date(?, 'dd/mm/yyyy'), to_date(?, 'dd/mm/yyyy'), ?, ?)",
				new String[] { "id_curso" });

		stmt.setInt(1, formAcad.getCandidato().getId());
		stmt.setInt(2, formAcad.getInstituicao().getId());
		stmt.setString(3, formAcad.getCurso());
		stmt.setString(4, formAcad.getDataInicio());
		stmt.setString(5, formAcad.getDataConclusao());
		stmt.setString(6, formAcad.getArea());
		stmt.setString(7, formAcad.getInfoAdicional());

		stmt.executeUpdate();

		ResultSet resultSet = stmt.getGeneratedKeys();

		if (resultSet.next()) {
			int id = resultSet.getInt(1);
			formAcad.setId(id);
		}
		con.close();
	}

	/**
	 * M�todo que l� a forma��o acad�mica de acordo com seu c�digo no banco de dados
	 * 
	 * @param id ID da forma��o acad�mica
	 * @return Forma��o acad�mica
	 * @throws SQLException
	 * @throws IdNotFoundException
	 * @throws ClassNotFoundException
	 */
	public FormacaoAcademica readById(int id) throws SQLException, IdNotFoundException, ClassNotFoundException {

		PreparedStatement stmt = con.prepareStatement("select * from dtc_form_academica where id_curso = ?");

		stmt.setInt(1, id);

		ResultSet resultSet = stmt.executeQuery();

		if (!resultSet.next())
			throw new IdNotFoundException("O ID da forma��o acad�mica n�o foi encontrado.");

		return parse(resultSet);
		con.close();
	}

	/**
	 * M�todo que l� a forma��o acad�mica de acordo com o c�digo de seu candidato no
	 * banco de dados
	 * 
	 * @param id
	 * @return Lista de forma��es acad�micas
	 * @throws SQLException
	 * @throws IdNotFoundException
	 * @throws ClassNotFoundException
	 */
	public List<FormacaoAcademica> readByCandidatoId(int id)
			throws SQLException, IdNotFoundException, ClassNotFoundException {

		PreparedStatement stmt = con.prepareStatement("select * from dtc_form_academica where cd_candidato = ?");

		stmt.setInt(1, id);

		ResultSet resultSet = stmt.executeQuery();

		if (!resultSet.next())
			throw new IdNotFoundException("A forma��o acad�mica do candidato n�o foi encontrado.");

		return parseList(resultSet);
		con.close();
	}

	/**
	 * M�todo que atualiza as informa��es de uma forma��o acad�mica que j� est�
	 * armazenada no banco de dados
	 * 
	 * @param formAcad FormacaoAcademica
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void update(FormacaoAcademica formAcad) throws SQLException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement(
				"update dtc_form_academica set cd_candidato = ?, cd_instituicao = ?, ds_escolaridade = ?, dt_inicio = ?, dt_conclusao = ?, ds_area_estudo = ?, ds_informacao_adicional = ? where id_curso = ?");

		stmt.setInt(1, formAcad.getCandidato().getId());
		stmt.setInt(2, formAcad.getInstituicao().getId());
		stmt.setString(3, formAcad.getCurso());
		stmt.setString(4, formAcad.getDataInicio());
		stmt.setString(5, formAcad.getDataConclusao());
		stmt.setString(6, formAcad.getArea());
		stmt.setString(7, formAcad.getInfoAdicional());
		stmt.setInt(8, formAcad.getId());

		int qtd = stmt.executeUpdate();

		if (qtd == 0)
			throw new IdNotFoundException("O ID da forma��o acad�mica n�o foi encontrado.");
		con.close();
	}

	/**
	 * M�todo que remove uma forma��o acad�mica do banco de dados
	 * 
	 * @param id C�digo da forma��o acad�mica que vai ser removida
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void delete(int id) throws SQLException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement("delete from dtc_form_academica where id_curso = ?");

		stmt.setInt(1, id);

		int qtd = stmt.executeUpdate();

		if (qtd == 0)
			throw new IdNotFoundException("O ID da forma��o acad�mica n�o foi encontrado.");
		con.close();
	}

	/**
	 * M�todo que recebe um resultSet e retorna uma forma��o acad�mica
	 * 
	 * @param resultSet resultSet
	 * @return FormacaoAcademica
	 * @throws SQLException
	 * @throws IdNotFoundException
	 * @throws ClassNotFoundException
	 */
	private FormacaoAcademica parse(ResultSet resultSet)
			throws SQLException, ClassNotFoundException, IdNotFoundException {

		int id = resultSet.getInt(1);
		int idCandidato = resultSet.getInt(2);
		int idInstituicao = resultSet.getInt(3);
		String curso = resultSet.getString(4);
		String dataInicio = resultSet.getString(5);
		String dataConclusao = resultSet.getString(6);
		String area = resultSet.getString(7);
		String infoAdicional = resultSet.getString(8);

		CandidatoBO candidatoBO = new CandidatoBO();
		Candidato candidato = candidatoBO.listar(idCandidato);

		InstituicaoBO instituicaoBO = new InstituicaoBO();
		Instituicao instituicao = instituicaoBO.listar(idInstituicao);

		FormacaoAcademica formAcad = new FormacaoAcademica(id, candidato, instituicao, curso, dataInicio, dataConclusao,
				area, infoAdicional);

		return formAcad;

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
	private List<FormacaoAcademica> parseList(ResultSet resultSet)
			throws SQLException, ClassNotFoundException, IdNotFoundException {

		List<FormacaoAcademica> lista = new ArrayList<FormacaoAcademica>();

		while (resultSet.next()) {
			FormacaoAcademica formAcad = parse(resultSet);
			lista.add(formAcad);
		}

		return lista;

	}

}
