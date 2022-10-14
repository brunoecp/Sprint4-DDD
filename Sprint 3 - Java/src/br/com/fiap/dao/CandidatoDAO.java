package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.bo.ExperienciaProfissionalBO;
import br.com.fiap.bo.FormacaoAcademicaBO;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.model.Candidato;
import br.com.fiap.model.ExperienciaProfissional;
import br.com.fiap.model.FormacaoAcademica;

public class CandidatoDAO {

	private Connection con;

	public CandidatoDAO(Connection con) {
		this.con = con;
	}

	public void create(Candidato candidato) throws SQLException {

		PreparedStatement stmt = con.prepareStatement(
				"insert into dtc_candidato values (sq_dtc_candidato.nextval, ?, ?, ?, ?, ?, ?, to_date(?, 'dd/mm/yyyy'), ?, ?, ?, ?, ?)",
				new String[] { "cd_candidato" });

		stmt.setString(1, candidato.getNome());
		stmt.setString(2, candidato.getEmail());
		stmt.setLong(3, candidato.getTelefone());
		stmt.setLong(4, candidato.getCpf());
		stmt.setString(5, candidato.getGenero());
		stmt.setString(6, candidato.getSenha());
		stmt.setString(7, candidato.getDataNascimento());
		stmt.setString(8, candidato.getAreaProfissional());
		stmt.setString(9, candidato.getNivelProfissional());
		stmt.setString(10, candidato.getInfoAdicional());
		stmt.setString(11, candidato.getEndereco());
		stmt.setString(12, candidato.getGithub());

		stmt.executeUpdate();

		ResultSet resultSet = stmt.getGeneratedKeys();

		if (resultSet.next()) {
			int id = resultSet.getInt(1);
			candidato.setId(id);
		}
		con.close();
	}

	public List<Candidato> readAll() throws SQLException, ClassNotFoundException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement(
				"select * from dtc_candidato c left join dtc_exp_profissional e on c.cd_candidato = e.cd_candidato left join dtc_form_academica fa on c.cd_candidato = fa.cd_candidato left join dtc_form_complementar fc on c.cd_candidato = fc.cd_candidato left join dtc_idioma i on c.cd_candidato = i.cd_candidato");

		ResultSet resultSet = stmt.executeQuery();

		return parseList(resultSet);
		con.close();
	}

	public Candidato readById(int id) throws SQLException, IdNotFoundException, ClassNotFoundException {

		PreparedStatement stmt = con.prepareStatement(
				"select * from dtc_candidato c left join dtc_exp_profissional e on c.cd_candidato = e.cd_candidato left join dtc_form_academica fa on c.cd_candidato = fa.cd_candidato left join dtc_form_complementar fc on c.cd_candidato = fc.cd_candidato left join dtc_idioma i on c.cd_candidato = i.cd_candidato where c.cd_candidato = ?");

		stmt.setInt(1, id);

		ResultSet resultSet = stmt.executeQuery();

		if (!resultSet.next())
			throw new IdNotFoundException("O ID do candidato não foi encontrado.");

		return parse(resultSet);
		con.close();
	}

	public List<Candidato> readByNome(String nome) throws SQLException, IdNotFoundException, ClassNotFoundException {

		PreparedStatement stmt = con.prepareStatement(
				"select * from dtc_candidato c left join dtc_exp_profissional e on c.cd_candidato = e.cd_candidato left join dtc_form_academica fa on c.cd_candidato = fa.cd_candidato left join dtc_form_complementar fc on c.cd_candidato = fc.cd_candidato left join dtc_idioma i on c.cd_candidato = i.cd_candidato where c.nm_candidato like ?");

		stmt.setString(1, "%" + nome + "%");

		ResultSet resultSet = stmt.executeQuery();

		if (!resultSet.next())
			throw new IdNotFoundException("O nome do candidato não foi encontrado.");

		return parseList(resultSet);
		con.close();
	}

	public List<Candidato> readByAreaProfissional(String areaProfissional)
			throws SQLException, IdNotFoundException, ClassNotFoundException {

		PreparedStatement stmt = con.prepareStatement(
				"select * from dtc_candidato c left join dtc_exp_profissional e on c.cd_candidato = e.cd_candidato left join dtc_form_academica fa on c.cd_candidato = fa.cd_candidato left join dtc_form_complementar fc on c.cd_candidato = fc.cd_candidato left join dtc_idioma i on c.cd_candidato = i.cd_candidato where c.ds_area_profissional like ?");

		stmt.setString(1, "%" + areaProfissional + "%");

		ResultSet resultSet = stmt.executeQuery();

		if (!resultSet.next())
			throw new IdNotFoundException("A Área profissional do candidato não foi encontrada.");

		return parseList(resultSet);
		con.close();
	}

	public void update(Candidato candidato) throws SQLException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement(
				"update dtc_candidato set nm_candidato = ?, ds_email = ?, nr_telefone = ?, nr_cpf = ?, ds_genero = ?, ds_senha = ?, dt_nascimento = ?, ds_area_profissional = ?, ds_nivel_profissional = ?, ds_informacao_adicional = ?, ds_endereco = ?, ds_github = ? where cd_candidato = ?");

		stmt.setString(1, candidato.getNome());
		stmt.setString(2, candidato.getEmail());
		stmt.setLong(3, candidato.getTelefone());
		stmt.setLong(4, candidato.getCpf());
		stmt.setString(5, candidato.getGenero());
		stmt.setString(6, candidato.getSenha());
		stmt.setString(7, candidato.getDataNascimento());
		stmt.setString(8, candidato.getAreaProfissional());
		stmt.setString(9, candidato.getNivelProfissional());
		stmt.setString(10, candidato.getInfoAdicional());
		stmt.setString(11, candidato.getEndereco());
		stmt.setString(12, candidato.getGithub());
		stmt.setInt(13, candidato.getId());

		int qtd = stmt.executeUpdate();

		if (qtd == 0)
			throw new IdNotFoundException("O ID do candidato não foi encontrado");
		con.close();
	}

	public void delete(int id) throws SQLException, IdNotFoundException {

		PreparedStatement stmt = con.prepareStatement("delete from dtc_candidato where cd_candidato = ?");

		stmt.setInt(1, id);

		int qtd = stmt.executeUpdate();

		if (qtd == 0)
			throw new IdNotFoundException("O ID do candidato não foi encontrado.");
		con.close();
	}

	private Candidato parse(ResultSet resultSet) throws SQLException, ClassNotFoundException, IdNotFoundException {

		int id = resultSet.getInt(1);
		String nome = resultSet.getString(2);
		String email = resultSet.getString(3);
		Long telefone = resultSet.getLong(4);
		Long cpf = resultSet.getLong(5);
		String genero = resultSet.getString(6);
		String senha = resultSet.getString(7);
		String dataNasc = resultSet.getString(8);
		String areaProf = resultSet.getString(9);
		String nivelProf = resultSet.getString(10);
		String infoAdic = resultSet.getString(11);
		String endereco = resultSet.getString(12);
		String github = resultSet.getString(13);
		int idExpProf = resultSet.getInt(14);
		int idFormAcad = resultSet.getInt(20);

		Candidato candidato = new Candidato(id, nome, email, telefone, cpf, genero, senha, dataNasc, areaProf,
				nivelProf, infoAdic, endereco, github);

		if (idExpProf != 0) {
			ExperienciaProfissionalBO expProfBO = new ExperienciaProfissionalBO();
			ExperienciaProfissional expProf = expProfBO.listar(idExpProf);
			List<ExperienciaProfissional> listaExpProf = expProfBO.listarCandidato(id);
			listaExpProf.add(expProf);
			candidato.setExpProfissional(listaExpProf);
		}

		if (idFormAcad != 0) {
			FormacaoAcademicaBO formAcadBO = new FormacaoAcademicaBO();
			FormacaoAcademica formAcad = formAcadBO.listar(idFormAcad);
			List<FormacaoAcademica> listaFormAcad = formAcadBO.listarCandidato(id);
			listaFormAcad.add(formAcad);
			candidato.setFormAcademica(listaFormAcad);
		}

		return candidato;

	}

	private List<Candidato> parseList(ResultSet resultSet)
			throws SQLException, ClassNotFoundException, IdNotFoundException {

		List<Candidato> lista = new ArrayList<Candidato>();

		while (resultSet.next()) {
			Candidato candidato = parse(resultSet);
			lista.add(candidato);
		}

		return lista;
	}

}
