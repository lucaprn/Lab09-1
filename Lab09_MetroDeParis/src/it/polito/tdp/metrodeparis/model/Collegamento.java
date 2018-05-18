package it.polito.tdp.metrodeparis.model;

public class Collegamento {
	
	private int id_linea;
	private int id_stazP;
	private int id_stazA;
	public Collegamento(int id_linea, int id_stazP, int id_stazA) {
		super();
		this.id_linea = id_linea;
		this.id_stazP = id_stazP;
		this.id_stazA = id_stazA;
	}
	public int getId_linea() {
		return id_linea;
	}
	public void setId_linea(int id_linea) {
		this.id_linea = id_linea;
	}
	public int getId_stazP() {
		return id_stazP;
	}
	public void setId_stazP(int id_stazP) {
		this.id_stazP = id_stazP;
	}
	public int getId_stazA() {
		return id_stazA;
	}
	public void setId_stazA(int id_stazA) {
		this.id_stazA = id_stazA;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_stazA;
		result = prime * result + id_stazP;
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
		Collegamento other = (Collegamento) obj;
		//inserisco in ovveride il metodo equals in modo da poter invertire partenza e arrivo e considerare un'unico collegamento
		if((id_stazA==other.id_stazA && id_stazP==other.id_stazP)||(id_stazA==other.id_stazP && id_stazP==other.id_stazA)) {
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public String toString() {
		return "Collegamento [id_linea=" + id_linea + ", id_stazP=" + id_stazP + ", id_stazA=" + id_stazA + "]";
	}
	
	
	
	
	
	
}
