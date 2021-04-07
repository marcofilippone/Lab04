package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;
	
	public Model() {
		corsoDao = new CorsoDAO();
		studenteDao = new StudenteDAO();
	}

	public List<Corso> getTuttiICorsi(){
		return corsoDao.getTuttiICorsi();
	}
	
	public Studente getStudentebyMatr(Integer matricola) {
		return studenteDao.getStudentebyMatr(matricola);
	}
	
	public List<Studente> getStudentiIscrittiCorso(String codice){
		return studenteDao.getStudentiIscrittiCorso(codice);
	}
	
	public List<Corso> getCorsiPerStudente(Integer matricola){
		return corsoDao.getCorsiPerStudente(matricola);
	}
	
	public boolean inscriviStudenteACorso(Integer matricola, String codins) {
		return corsoDao.inscriviStudenteACorso(matricola, codins);
	}
}
