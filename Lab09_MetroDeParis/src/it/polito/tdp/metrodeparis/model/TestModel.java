package it.polito.tdp.metrodeparis.model;

import java.util.ArrayList;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		System.out.println(""+model.getFermate());
		System.out.println(""+model.getGraph());
		
		List<Fermata> list= model.getFermate();
		
		
		model.getPercorso(list.get(0),list.get(4));
	}

}
