package it.polito.tdp.metrodeparis.model;

import java.util.HashMap;
import java.util.Map;

import com.javadocmd.simplelatlng.LatLng;

public class Fermata {

	private int idFermata;
	private String nome;
	private LatLng coords;
	private Map<Key, FermataSuLinea> fermateSuLinea;

	public Fermata(int idFermata, String nome, LatLng coords) {
		this.idFermata = idFermata;
		this.nome = nome;
		this.coords = coords;
		this.fermateSuLinea = new HashMap<Key, FermataSuLinea>();
	}

	public Fermata(int idFermata) {
		this.idFermata = idFermata;
	}

	public Map<Key, FermataSuLinea> getFermateSuLinea() {
		return this.fermateSuLinea;
	}

	public void addFermataSuLinea(Key key, FermataSuLinea fermataSuLinea) {
		this.fermateSuLinea.put(key, fermataSuLinea);
	}

	public int getIdFermata() {
		return idFermata;
	}

	public void setIdFermata(int idFermata) {
		this.idFermata = idFermata;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LatLng getCoords() {
		return coords;
	}

	public void setCoords(LatLng coords) {
		this.coords = coords;
	}

	@Override
	public int hashCode() {
		return ((Integer) idFermata).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fermata other = (Fermata) obj;
		if (idFermata != other.idFermata)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return nome;
	}
}