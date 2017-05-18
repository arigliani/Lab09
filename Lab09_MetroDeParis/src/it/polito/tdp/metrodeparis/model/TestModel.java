package it.polito.tdp.metrodeparis.model;

import java.util.ArrayList;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		System.out.println("tutte fermate"+model.getFermate());
		
		System.out.println("grafo "+model.getGraph());
		
		
		List<Fermata> list= model.getFermate();
		System.out.println(list.get(0)+" "+list.get(4));
		
		System.out.println();
		System.out.println(""+model.ritornaPercorso(list.get(0),list.get(4)));
	}

}
