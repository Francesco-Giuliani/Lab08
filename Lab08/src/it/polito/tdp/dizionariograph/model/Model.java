package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	
	WordDAO wordDAO;
	
	public Model() {
		this.wordDAO = new WordDAO();
	}

	public void createGraph(int numeroLettere) {

		Graph<String, DefaultEdge> grafo = new SimpleGraph<>(DefaultEdge.class);
		
		
		System.err.println("createGraph -- TODO");
	}

	public List<String> displayNeighbours(String parolaInserita) {

		System.err.println("displayNeighbours -- TODO");
		return new ArrayList<String>();
	}

	public int findMaxDegree() {
		System.err.println("findMaxDegree -- TODO");
		return -1;
	}
}
