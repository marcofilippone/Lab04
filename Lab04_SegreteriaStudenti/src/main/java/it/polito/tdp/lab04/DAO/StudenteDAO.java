package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Studente getStudentebyMatr(Integer matricola){
		String sql = "SELECT * FROM studente WHERE matricola = ?";
		Studente s;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			if(rs.first()) {
				s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
				rs.close();
				st.close();
				conn.close();
				return s;
			}
			else {
				rs.close();
				st.close();
				conn.close();
				return null;
			}
		} catch(SQLException e) {
			throw new RuntimeException();
		}
	}

	public List<Studente> getStudentiIscrittiCorso(String codice){
		String sql = "SELECT * "
				+"FROM studente s, iscrizione i "
				+"WHERE s.matricola=i.matricola AND i.codins=?";
		List<Studente> risultato = new ArrayList();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codice);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				risultato.add(new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS")));
			}
			rs.close();
			st.close();
			conn.close();
			return risultato;
		} catch(SQLException e) {
			throw new RuntimeException();
		}
	}
}
