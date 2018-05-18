package it.polito.tdp.metrodeparis.dao;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.metrodeparis.model.Collegamento;
import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.Linea;

public class TestDAO {

	public static void main(String[] args) {
		
		MetroDAO metroDAO = new MetroDAO();
		
		System.out.println("Lista fermate");
		List<Fermata> fermate = metroDAO.getAllFermate();
		System.out.println(fermate);
		
		System.out.println("Lista Linee");
		List<Linea> linee = new ArrayList<>(metroDAO.getAllLinee());
		for(Linea l : linee) {
			System.out.println(l);
		}
		
		System.out.println("Lista Collegamenti");
		List<Collegamento> collegamenti = new ArrayList<>(metroDAO.getAllCollegamenti());
		for(Collegamento c : collegamenti) {
			System.out.println(c);
		}
		
	}

}
