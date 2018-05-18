package it.polito.tdp.metrodeparis.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.metrodeparis.dao.MetroDAO;

public class Model {
	
	private Graph<Fermata,DefaultWeightedEdge> grafo;
	private MetroDAO dao;
	private List<Fermata> fermate;
	private List<Linea> linee; 
	private List<Collegamento> collegamenti;
	List<DefaultWeightedEdge> shortestPathEdgeList;
	double shortestPathTempoTotale=-1;
	
	
	public Model(){
		this.dao=new MetroDAO();
		this.fermate = new ArrayList<>(dao.getAllFermate());
		this.linee = new ArrayList<>(dao.getAllLinee());
		this.collegamenti = new ArrayList<>(dao.getAllCollegamenti());
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
	}
	
	public void creaGrafo() {
		//aggiungo i vertici del grafo che sono le fermate della metro
		Graphs.addAllVertices(grafo, fermate);
		this.addVertex();
		
	}
	

	private void addVertex() {
		for(Collegamento c : this.collegamenti) {
			Fermata f1 = this.getFermataFromID(c.getId_stazA());
			Fermata f2 = this.getFermataFromID(c.getId_stazP());
			Linea l = this.getLineaFromID(c.getId_linea());
			Graphs.addEdge(this.grafo, f1, f2, this.getPeso(f1, f2, l));
			
		}
		
	}
	
	public double getPeso(Fermata f1, Fermata f2, Linea l) {
		LatLng l1 = new LatLng(f1.getCoords().getLatitude(), f1.getCoords().getLongitude());
		LatLng l2 = new LatLng(f2.getCoords().getLatitude(), f2.getCoords().getLongitude());
		double distanza = LatLngTool.distance(l1, l2, LengthUnit.KILOMETER);
		double tempo = (distanza/l.getVelocita());
		return tempo*60*60;
		
		
	}
	
	public Linea getLineaFromID(int id) {
		for(Linea l : this.linee) {
			if(l.getId()==id) {
				return l;
			}
		}
		return null;
	}

	public Fermata getFermataFromID(int id) {
		for(Fermata s : this.fermate) {
			if(s.getIdFermata()==id) {
				return s;
			}
		}
		return null;
	}

	public Graph<Fermata, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}
	
	public void getMinimunPath(Fermata f1,Fermata f2) {
		DijkstraShortestPath<Fermata,DefaultWeightedEdge > dijkstra = new DijkstraShortestPath<Fermata,DefaultWeightedEdge >(grafo,f1,f2);
		shortestPathEdgeList = dijkstra.getPathEdgeList();
		if (shortestPathEdgeList == null) {
			throw new RuntimeException("Non è stato possiible crare un percorso.");
		}

		if (shortestPathEdgeList.size() - 1 > 0) {
			shortestPathTempoTotale += (shortestPathEdgeList.size() - 1) * 30;
		
		}
		
	}
	
	public String toStringCalcolaPercorso() {
		if (shortestPathEdgeList == null)
			throw new RuntimeException("Non è stato creato alcun percorso.");

		StringBuilder risultato = new StringBuilder();
		risultato.append("Percorso: [ ");

		for ( DefaultWeightedEdge d : shortestPathEdgeList) {
			risultato.append(grafo.getEdgeTarget(d).getNome());
			risultato.append(",\n");
		}
		risultato.setLength(risultato.length() - 2);
		risultato.append("]");
		
		risultato.append(String.format("%nTempo necessario : %n%.2f secondi%n%.2f minuti%n%.2f ore", this.shortestPathTempoTotale,this.shortestPathTempoTotale/60,this.shortestPathTempoTotale/(60*60)));

		return risultato.toString();
		}
	

	

}
