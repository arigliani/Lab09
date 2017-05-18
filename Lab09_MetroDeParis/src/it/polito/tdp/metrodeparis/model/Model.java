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
	private WeightedMultigraph<Fermata, FermateAdiacenti> graph;
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


	public WeightedMultigraph<Fermata, FermateAdiacenti> getGraph() {
		
			this.creaGrafo();
		
		return this.graph;
	}
	public String ritornaPercorso(Fermata a, Fermata b){
		List<FermateAdiacenti> listaMinimo=this.getPercorso(a, b);
		Linea d=listaMinimo.get(0).getL1();
		String fine="prendo linea: "+d.getNome()+"\n";
		for(FermateAdiacenti c: listaMinimo){
			if(!c.getL1().equals(d)){
				fine+="\n"+"cambio linea "+c.getL1().getNome()+"\n";
				d.setNome(c.getL1().getNome());
				d.setId(c.getL1().getId());
				d.setVelocita(c.getL1().getVelocita());				
			}else{
				d.setNome(c.getL1().getNome());
				d.setId(c.getL1().getId());
				d.setVelocita(c.getL1().getVelocita());					
			}
			fine+="P: "+c.getF1().getNome()+ " A: "+c.getF2().getNome()+"\n";
			
		}
		double var=0;
		
		for(FermateAdiacenti c: listaMinimo){
			var+= c.getPercorrenza();
		}
	   fine+="\n"+var+" minuti";
		
		return fine.trim();
	}
	
	private List<FermateAdiacenti> getPercorso(Fermata a, Fermata b){
		    this.creaGrafo();
		
		
		DijkstraShortestPath<Fermata, FermateAdiacenti> percorso= new DijkstraShortestPath<Fermata, FermateAdiacenti>(graph,a,b);
		List<FermateAdiacenti> listaMinimo= percorso.getPathEdgeList(); //ritorna una lista di archi
		
		if(listaMinimo==null){
			return null;
		}else{
			return listaMinimo;

		}
	}


	private void creaGrafo() {
		this.graph= new WeightedMultigraph<Fermata, FermateAdiacenti>(FermateAdiacenti.class);
		Graphs.addAllVertices( graph, this.getFermate());
		
		
		//Graphs.addEdgeWithVertices(g, sourceVertex, targetVertex, weight)
		for(FermateAdiacenti fa: dao.listStazioniAdiacenti()){	
		
		  double tempo= calcolaTempo(fa);
		  FermateAdiacenti e= new FermateAdiacenti(fa.getF1(), fa.getF2());
		  graph.addEdge(fa.getF1(), fa.getF2(), e);
		   
			graph.setEdgeWeight(e,tempo);
			
			e.setL1(fa.getL1());
			e.setPercorrenza(tempo);
			
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
