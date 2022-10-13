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
import br.com.fiap.model.FormacaoComplementar;
import br.com.fiap.model.Instituicao;

/**
 * Classe que representa o Data Access Object da classe Forma��o Acad�mica
 * 
 * @author Luisa Purifica��o
 * @version 1.0
 */
public class FormacaoComplementarDAO {

	/**
	 * Cria uma conex�o com o banco de dados
	 */
	private Connection con;

	/**
	 * Construtor que recebe como par�metro uma conex�o com o banco de dados
	 * 
	 * @param con Conex�o com o banco de dados
	 */
	public FormacaoComplementarDAO(Connection con) {
		this.con = con;
	}

	/**
	 * M�todo que armazena a forma��o complementar de um candidato recebida como
	 * par�metro no banco de dados
	 * 
	 * @param formComp Forma��o complementar de um candidato
	 * @throws SQLException
	 */
	public void create(FormacaoComplementar formComp) throws SQLException {

		PreparedStatement stmt = con.prepareStatement(
				"insert into dtc_form_complementar values (sq_dtc_form_complementar.nextval, ?, ?, ?, to_date(?, 'dd/mm/yyyy'), to_date(?, 'dd/mm/yyyy'), ?)",
				new String[] { "id_curso_complemnt" });

		stmt.setInt(1, formComp.getInstituicao().getId());
		stmt.setInt(2, formComp.getCandidato().getId());
		stmt.setString(3, formComp.getCurso());
		stmt.setString(4, formComp.getDataInicio());
		stmt.setString(5, formComp.getDataConclusao());
		stmt.setString(6, formComp.getArea());

		stmt.executeUpdate();

		ResultSet resultSet = stmt.getGeneratedKeys();

		if (resultSet.next()) {
			int id = resultSet.getInt(1);
			formComp.setId(id);
		}
		con.close();
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
	public FormacaoComplementar readById(int id) throws SQLException, IdNotFoundException, ClassNotFoundException {

		PreparedStatement stmt = con
				.prepareStatement("select * from dtc_form_complementar where id_curso_complemnt = ?");

		stmt.setInt(1, id);

		ResultSet resultSet = stmt.executeQuery();

		if (!resultSet.next())
			throw new IdNotFoundException("O ID da forma��o complementar n�o foi encontrado.");

		return parse(resultSet);
		con.close();
	}

	/**
	 * M�todo que l� a forma��o complementar de acordo com o c�digo de seu candidato
	 * no banco de dados
	 * 
	 * @param id
	 * @return Lista de forma��es complementares
	 * @throws SQLException
	 * @throws IdNotFoundException
	 * @throws ClassNotFoundException
	 */
	public List<FormacaoComplementar> readByCandidatoId(int id)
			throws SQLException, IdNotFoundException, ClassNotFoundException {

		PreparedStatement stmt = con.prepareStatement("select * from dtc_form_complementar where cd_candidato = ?");

		stmt.setInt(1, id);

		ResultSet resultSet = stmt.executeQuery();

		if (!resultSet.next())
			throw new IdNotFoundException("A forma��o complementar do candidato n�o foi encontrado.");

		return parseList(resultSet);
		con.close();
	}

	/**
	 * M�todo que atualiza as informa��es de uma forma��o complementar que j� est�
	 * armazenada no banco de dados
	 * 
	 * @param formComp FormacaoComplementar
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void update(FormacaoComplementar formComp) throws SQLException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement(
				"update dtc_form_complementar set cd_instituicao = ?, cd_candidato = ?, nm_curso = ?, dt_inicio = ?, dt_conclusao = ?, ds_area_de_estudo = ? where id_curso_complemnt = ?");

		stmt.setInt(1, formComp.getInstituicao().getId());
		stmt.setInt(2, formComp.getCandidato().getId());
		stmt.setString(3, formComp.getCurso());
		stmt.setString(4, formComp.getDataInicio());
		stmt.setString(5, formComp.getDataConclusao());
		stmt.setString(6, formComp.getArea());
		stmt.setInt(8, formComp.getId());

		int qtd = stmt.executeUpdate();

		if (qtd == 0)
			throw new IdNotFoundException("O ID da forma��o complementar n�o foi encontrado.");
		con.close();
	}

	/**
	 * M�todo que remove uma forma��o complementar do banco de dados
	 * 
	 * @param id C�digo da forma��o complementar que vai ser removida
	 * @throws SQLException
	 * @throws IdNotFoundException
	 */
	public void delete(int id) throws SQLException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement("delete from dtc_form_complementar where id_curso_complemnt = ?");

		stmt.setInt(1, id);

		int qtd = stmt.executeUpdate();

		if (qtd == 0)
			throw new IdNotFoundException("O ID da forma��o complementar n�o foi encontrado.");
		con.close();
	}

	/**
	 * M�todo que recebe um resultSet e retorna uma forma��o complementar
	 * 
	 * @param resultSet resultSet
	 * @return FormacaoComplementar
	 * @throws SQLException
	 * @throws IdNotFoundException
	 * @throws ClassNotFoundException
	 */
	private FormacaoComplementar parse(ResultSet resultSet)
			throws SQLException, ClassNotFoundException, IdNotFoundException {

		int id = resultSet.getInt(1);
		int idInstituicao = resultSet.getInt(2);
		int idCandidato = resultSet.getInt(3);
		String curso = resultSet.getString(4);
		String dataInicio = resultSet.getString(5);
		String dataConclusao = resultSet.getString(6);
		String area = resultSet.getString(7);

		CandidatoBO candidatoBO = new CandidatoBO();
		Candidato candidato = candidatoBO.listar(idCandidato);

		InstituicaoBO instituicaoBO = new InstituicaoBO();
		Instituicao instituicao = instituicaoBO.listar(idInstituicao);

		FormacaoComplementar formComp = new FormacaoComplementar(id, instituicao, candidato, curso, dataInicio,
				dataConclusao, area);

		return formComp;

	}

	/**
	 * M�todo que recebe um resultSet e retorna uma lista de forma��es
	 * complementares
	 * 
	 * @param resultSet resultSet
	 * @return Lista de Forma��es complementares
	 * @throws SQLException
	 * @throws IdNotFoundException
	 * @throws ClassNotFoundException
	 */
	private List<FormacaoComplementar> parseList(ResultSet resultSet)
			throws SQLException, ClassNotFoundException, IdNotFoundException {

		List<FormacaoComplementar> lista = new ArrayList<FormacaoComplementar>();

		while (resultSet.next()) {
			FormacaoComplementar formComp = parse(resultSet);
			lista.add(formComp);
		}

		return lista;

	}

}
