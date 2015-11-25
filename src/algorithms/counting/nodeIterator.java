package algorithms.counting;

import edu.uci.ics.jung.graph.Graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class nodeIterator<Vertex,Edge> {

    //Keep a handle on the vertices and edges
    private ArrayList<Vertex> vertices = new ArrayList<>();
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
        this.vertices.addAll(graph.getVertices());
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
            return(p.degree-this.degree);
        }
    }

    /**
     * Gets the neighbors of a vertex where the neighbors degree is greater than that of v1
     * Arbitrarily but consistently breaks ties with index of vertex in vertices arraylist
     * @param v1 the vertex for which the neighbors are needed.
     * @return an arraylist of pairs.
     */
    private ArrayList<Vertex> getSetGreater(Vertex v1, Collection<Vertex> set){
        ArrayList<Pair> orderings = new ArrayList<>();

        for(Vertex v : set){
            if(graph.degree(v1) < graph.degree(v)) {
                orderings.add(new Pair(v, graph.degree(v)));
            }
            else if(graph.degree(v1) == graph.degree(v)){
                if(vertices.indexOf(v1) > vertices.indexOf(v)){
                    orderings.add(new Pair(v, graph.degree(v)));
                }
                else{
                    orderings.add(new Pair(v1, graph.degree(v1)));
                }
            }
        }

        Collections.sort(orderings);
        ArrayList<Vertex> v = new ArrayList<>();

        //Reconstruct set
        for(Pair p : orderings){
            v.add(p.getVertex());
        }
        return v;
    }

    /**
     * Finds the number of triangles in a graph using the node iterator++ method.
     * @return a double with the number of triangles in graph g.
     */
    public double getNumberOfTriangles(){
        double triangles = 0;
        for(Vertex v : vertices){
            Collection<Vertex> neighbors = getSetGreater(v, graph.getNeighbors(v));
            for(Vertex u : neighbors){
                for(Vertex w : getSetGreater(u, neighbors)){
                    if(u == w){continue;}
                    if(edges.contains(graph.findEdge(u,w)) ){
                        triangles += .5;
                    }
                }
            }
        }
    return triangles;
    }
}
