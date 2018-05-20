package it.polito.tdp.dizionariograph.model;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class TestModel {

	public static void main(String[] args) {
	
	
		Model model = new Model();
		
		/*Graph<String, DefaultEdge> grafo = new SimpleGraph<>(DefaultEdge.class);
		
		grafo.addVertex("abaco");
		grafo.addVertex("abaci");
		grafo.addVertex("acaci");
		grafo.addVertex("acini");
		grafo.addVertex("acino");
		grafo.addVertex("acine");
		*/
//		for(String s : grafo.vertexSet()) {
//			for(String t: grafo.vertexSet()) {
//				if(!s.equals(t)) {
//					
//					int count=0;
//					for(int k =0; k < s.length(); k++)
//						if(s.charAt(k) != t.charAt(k))
//							count++;
//					if(count == 1)
//						grafo.addEdge(s, t);
//				}
//			}
//		}
//		
		
		model.createGraph(4);
		System.out.println(String.format("**Grafo creato**\n"));
		System.out.println(model.getGrafo());
		
		List<String> vicini =  model.displayNeighbours("casa"); // Graphs.neighborListOf(grafo, "abaco"); 
		System.out.println("Neighbours di casa: " + vicini + "\n");
		
		System.out.println("Cerco il vertice con grado massimo...");
		System.out.println(model.findMaxDegree());
		System.out.println(model.getMaxDegreeVertex());
		
	}

}
