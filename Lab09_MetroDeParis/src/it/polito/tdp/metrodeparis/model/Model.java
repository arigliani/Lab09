package it.polito.tdp.metrodeparis.model;

import java.util.ArrayList;
//qual' è la differneza se simette come arco defoult oppure link??
import java.util.Collection;
import java.util.List;
import java.util.Map;


import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;


import it.polito.tdp.metrodeparis.dao.MetroDAO;


public class Model {
	private WeightedMultigraph<Fermata, DefaultWeightedEdge> graph;
	private List<Fermata> fermate;
	private MetroDAO dao= new MetroDAO();
	
	
	public List<Fermata> getFermate() {
		if(fermate==null){
			fermate=dao.getAllFermate();
			return fermate;
		}
		else
			return fermate;
	}


	public WeightedMultigraph<Fermata, DefaultWeightedEdge> getGraph() {
		
			this.creaGrafo();
		
		return this.graph;
	}
	
	
	public List<Fermata> getPercorso(Fermata a, Fermata b){
		DijkstraShortestPath<Fermata, DefaultWeightedEdge> percorso= new DijkstraShortestPath<Fermata, DefaultWeightedEdge>(graph,a,b);
		List<DefaultWeightedEdge> listaMinomo= percorso.getPathEdgeList(); //ritorna una lista di archi
		for(DefaultWeightedEdge e: listaMinomo){
			g
		}
		if(listaMinomo==null){
			System.out.println("non esiste");
		}else{
			System.out.println(percorso.toString());

		}
		
		return null;
	}


	private void creaGrafo() {
		this.graph= new WeightedMultigraph<Fermata, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Graphs.addAllVertices( graph, this.getFermate());
		 DefaultWeightedEdge e;
		for(FermateAdiacenti fa: dao.listStazioniAdiacenti()){	
		
		  double tempo= calcolaTempo(fa);
		
		  e=graph.addEdge(fa.getF1(), fa.getF2());
		   
			graph.setEdgeWeight(e,tempo);
			
		}
		
		
	}


	private double calcolaTempo(FermateAdiacenti fa) {
		double a1=fa.getF1().getCoords().getLatitude();
	   double b1=fa.getF1().getCoords().getLongitude();
		double a2=fa.getF2().getCoords().getLatitude();
	  double b2=fa.getF2().getCoords().getLongitude();
	
				
		double metri=distance(a1,b1,a2,b2);
		double velocita=fa.getL1().getVelocita()*1000/60; //metri al minuto
		double tempo= metri/velocita;
		return tempo+0.5;
	}


	private double distance (double lat_a, double lng_a, double lat_b, double lng_b ) 
	{
	    double earthRadius = 3958.75;
	    double latDiff = Math.toRadians(lat_b-lat_a);
	    double lngDiff = Math.toRadians(lng_b-lng_a);
	    double a = Math.sin(latDiff /2) * Math.sin(latDiff /2) +
	    Math.cos(Math.toRadians(lat_a)) * Math.cos(Math.toRadians(lat_b)) *
	    Math.sin(lngDiff /2) * Math.sin(lngDiff /2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    double distance = earthRadius * c;

	    int meterConversion = 1609;

	    return (distance * meterConversion);
	}


	



	
	
	
	
	

}
