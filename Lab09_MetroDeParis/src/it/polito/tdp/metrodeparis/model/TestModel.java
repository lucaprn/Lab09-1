package it.polito.tdp.metrodeparis.model;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		//System.out.println(model.getPeso(model.getFermataFromID(85), model.getFermataFromID(87), model.getLineaFromID(1027)));
		model.creaGrafo();
		//Graph<Fermata,DefaultWeightedEdge> grafo = model.getGrafo();
		//System.out.println(grafo.getEdgeWeight(grafo.getEdge(model.getFermataFromID(85), model.getFermataFromID(87))));
		model.getMinimunPath(model.getFermataFromID(85),model.getFermataFromID(120));
		System.out.println(model.toStringCalcolaPercorso());
	}

}
