package algorithms.counting;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;

import java.util.Collection;
import java.util.LinkedHashSet;


public class nodeIterator<Vertex,Edge> {

     //Keep a handle on the vertices
     Collection<Vertex >vertices;
     LinkedHashSet<Pair<Vertex>> graphEdges = new LinkedHashSet<>();

    //The original graph
     Graph<Vertex, Edge> graph;

    /**
     * Takes in a graph
     * @param graph the graph that we would like the number of triangles for.
     */
    public nodeIterator(Graph<Vertex,Edge> graph){

        Collection<Edge> edges = graph.getEdges();
        this.graph = graph;
        this.vertices = graph.getVertices();


        //Break each edge apart. Add them to our set of vertices.
        for(Edge edge : edges){
            Pair<Vertex> endpoints = graph.getEndpoints(edge);
            if(!graphEdges.contains(endpoints)) {
                graphEdges.add(endpoints);
            }
        }


    }

    //TODO: Node Iteration Count
    public double getNumberOfTriangle(){
        double triangles = 0;


        for(Vertex v : vertices){
            Collection<Vertex> neighbors = graph.getNeighbors(v);

            for(Vertex w : neighbors){
                for(Vertex k : neighbors){

                    if(graphEdges.contains(new Pair<>(k,w))){
                        triangles += .5;
                    }
                }
            }
        }
        return triangles/3;
    }



    public void toDescriptiveString() {
        for(Pair p : graphEdges) {
            System.out.println(p.getFirst() + "," + p.getSecond());
        }
    }



}
