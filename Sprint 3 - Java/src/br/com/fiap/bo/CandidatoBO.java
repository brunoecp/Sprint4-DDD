package br.com.fiap.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.dao.CandidatoDAO;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Candidato;

public class CandidatoBO {

	private CandidatoDAO dao;
	private Connection con;

	public void cadastrar(Candidato candidato) throws SQLException, ClassNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new CandidatoDAO(con);

		// REGRAS DE NEGOCIO

		dao.create(candidato);

	}

	public List<Candidato> listar() throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new CandidatoDAO(con);

		// REGRAS DE NEGOCIO

		return dao.readAll();

	}

	public Candidato listar(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new CandidatoDAO(con);

		// REGRAS DE NEGOCIO

		return dao.readById(id);

	}

	public List<Candidato> listar(String nome) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new CandidatoDAO(con);

		// REGRAS DE NEGOCIO

		return dao.readByNome(nome);

	}

	public List<Candidato> listarArea(String areaProfissional)
			throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new CandidatoDAO(con);

		// REGRAS DE NEGOCIO

		return dao.readByAreaProfissional(areaProfissional);

	}

	public void atualizar(Candidato candidato) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new CandidatoDAO(con);

		// REGRAS DE NEGOCIO

		dao.update(candidato);

	}

	public void remover(int id) throws ClassNotFoundException, SQLException, IdNotFoundException {

		con = ConnectionFactory.getConnection();
		dao = new CandidatoDAO(con);

		// REGRAS DE NEGOCIO

		dao.delete(id);

	}

}
