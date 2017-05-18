package it.polito.tdp.metrodeparis.dao;

import java.util.List;

import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.FermateAdiacenti;

public class TestDAO {

	public static void main(String[] args) {
		
		MetroDAO metroDAO = new MetroDAO();
		
		System.out.println("Lista fermate");
		List<Fermata> fermate = metroDAO.getAllFermate();
		System.out.println(fermate);
		
		
		System.out.println("---------adiacenti-----");
		List<FermateAdiacenti> adiacenti= metroDAO.listStazioniAdiacenti();
		System.out.println(adiacenti);
		
		
		
	}

}
