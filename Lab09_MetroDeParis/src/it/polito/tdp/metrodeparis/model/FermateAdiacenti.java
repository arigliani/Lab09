package it.polito.tdp.metrodeparis.model;

public class FermateAdiacenti {
	private Fermata f1;
	private Fermata f2;
	private Linea l1;
	
	
	
	
	public FermateAdiacenti(Fermata f1, Fermata f2, Linea l1) {
		super();
		this.f1 = f1;
		this.f2 = f2;
		this.l1 = l1;
	}
	
	
	public Fermata getF1() {
		return f1;
	}
	public void setF1(Fermata f1) {
		this.f1 = f1;
	}
	public Fermata getF2() {
		return f2;
	}
	public void setF2(Fermata f2) {
		this.f2 = f2;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((f1 == null) ? 0 : f1.hashCode());
		result = prime * result + ((f2 == null) ? 0 : f2.hashCode());
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
		FermateAdiacenti other = (FermateAdiacenti) obj;
		if (f1 == null) {
			if (other.f1 != null)
				return false;
		} else if (!f1.equals(other.f1))
			return false;
		if (f2 == null) {
			if (other.f2 != null)
				return false;
		} else if (!f2.equals(other.f2))
			return false;
		return true;
	}
	
	public Linea getL1() {
		return l1;
	}
	public void setL1(Linea l1) {
		this.l1 = l1;
	}


	@Override
	public String toString() {
		return "FermateAdiacenti [f1=" + f1 + ", f2=" + f2 + ", l1=" + l1 + "]";
	}
	
	
	
	

}
