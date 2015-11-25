package algorithms.counting;

import edu.uci.ics.jung.graph.Graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class nodeIterator<Vertex,Edge> {

    //Keep a handle on the vertices and edges
    private Collection<Vertex >vertices;
    private Collection<Edge> edges;

    //The original graph
    private Graph<Vertex, Edge> graph;

    /**
     * Takes in a graph
     * @param graph the graph that we would like the number of triangles for.
     */
    public nodeIterator(Graph<Vertex,Edge> graph){
        this.edges = graph.getEdges();
        this.graph = graph;
        this.vertices = graph.getVertices();
    }

    /**
     * A pair of objects, the vertex and the degree of the vertex.
     */
    public class Pair implements Comparable<Pair>{
        private int degree;
        private Vertex vertex;

        public Pair(Vertex v, int d){
            this.degree = d;
            this.vertex = v;
        }
        public Vertex getVertex(){
            return this.vertex;
        }

        //Comparator so that we can sort
        public int compareTo(Pair p){
            //This ordering is used to have a reverse ordering. (highest degree first)
            return(p.degree-this.degree);
        }
    }

    /**
     * Gets the neighbors of a vertex where the neighbors degree is greater than that of v1
     * @param v1 the vertex for which the neighbors are needed.
     * @return an arraylist of pairs.
     */
    private ArrayList<Pair> getNeighbors(Vertex v1){
        ArrayList<Pair> orderings = new ArrayList<>();

        for(Vertex v : graph.getNeighbors(v1)){
            if(graph.degree(v1) < graph.degree(v)) {
                orderings.add(new Pair(v, graph.degree(v)));
            }
        }

        Collections.sort(orderings);
        return orderings;
    }

    /**
     * Finds the number of triangles in a graph using the node iterator method.
     * @return a double with the number of triangles in graph g.
     */
    public double getNumberOfTriangle(){
        double triangles = 0;
        for(Vertex v : vertices){
            Collection<Pair> neighbors = getNeighbors(v);
            for(Pair w : neighbors){
                for(Pair k : neighbors){
                    if(edges.contains(graph.findEdge(w.getVertex(),k.getVertex()))){
                        triangles += .5;
                    }
                }
            }
        }
    return triangles;
    }
}
