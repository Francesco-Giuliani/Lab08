package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	
	private WordDAO wordDAO;
	private List<String> parole;
	private Graph<String, DefaultEdge> grafo;
	private String maxDegreeVertex;
	private int maxDegree;
	
	public Model() {
		this.wordDAO= new WordDAO();
		this.parole = new ArrayList<>();
		this.grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
	}

	public void createGraph(int numeroLettere) {
		
		if(this.grafo ==null)
			this.grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		this.parole = this.wordDAO.getAllWordsFixedLength(numeroLettere);
		
		Graphs.addAllVertices(this.grafo, this.parole);
		
		System.out.println("Aggiunti vertici");
		
		for(String s : grafo.vertexSet()) {
			for(String t: grafo.vertexSet()) {
				if(!s.equals(t)) {
					int count=0;
					for(int k =0; k < s.length() && count <=2; k++)
						if(s.charAt(k) != t.charAt(k))
							count++;
					if(count == 1)
						grafo.addEdge(s, t);
				}
			}
		}
	}

	public List<String> displayNeighbours(String parolaInserita) {

		/*
 		Set<DefaultEdge> edges = this.grafo.outgoingEdgesOf(parolaInserita);

		List<String>res = new ArrayList<>();
		for(DefaultEdge e: edges)
			res.add(this.grafo.getEdgeTarget(e));
		*/
		List<String> res = Graphs.neighborListOf(this.grafo, parolaInserita);
		
		return res;
	}

	public int findMaxDegree() {
		
		String vertexMax = "_NONE_";
		int max =0;
		
		for(String s : this.grafo.vertexSet()) {
			int numEdges = this.grafo.degreeOf(s); //edgesOf(s).size();
			if(numEdges>max) {
				max = numEdges;
				vertexMax = s;
			}
		}
		
		this.maxDegreeVertex = vertexMax;
		this.maxDegree = max;
		
		return max;
	}

	public Graph<String, DefaultEdge> getGrafo() {
		return this.grafo;
	}

	public String getMaxDegreeVertex() {
		return maxDegreeVertex;
	}

	public int getMaxDegree() {
		return maxDegree;
	}

	public void setMaxDegreeVertex(String maxDegreeVertex) {
		this.maxDegreeVertex = maxDegreeVertex;
	}

	public void setMaxDegree(int maxDegree) {
		this.maxDegree = maxDegree;
	}

	public void setGrafo(Graph graph) {
		this.grafo = graph;	
	}
	
	
}
