package it.polito.tdp.metrodeparis.model;

public class Linea {
private int id;
private String nome;
private double velocita;


public Linea(int id, String nome, double velocita) {
	super();
	this.id = id;
	this.nome = nome;
	this.velocita = velocita;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public double getVelocita() {
	return velocita;
}
public void setVelocita(double velocita) {
	this.velocita = velocita;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Linea other = (Linea) obj;
	if (id != other.id)
		return false;
	return true;
}
@Override
public String toString() {
	return "Linea [id=" + id + ", nome=" + nome + ", velocita=" + velocita + "]";
}



}
