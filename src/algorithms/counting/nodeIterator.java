package algorithms.counting;

import cern.colt.matrix.DoubleFactory2D;
import cern.colt.matrix.DoubleMatrix2D;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;


public class edgeIterator<Vertex,Edge> {

     //Our adjacency matrix
     DoubleMatrix2D adjacencies;

     //Keep a handle on the vertices
     ArrayList<Vertex> vertices = new ArrayList<>();

     //Tree map to keep sorted set, sorted by degree of vertices.
     TreeMap<Vertex, Integer> orderEdges = new TreeMap<>();

    //The original graph
     Graph<Vertex, Edge> graph;

    /**
     * Takes in a graph
     * @param graph the graph that we would like the number of triangles for.
     */
    public edgeIterator(Graph<Vertex,Edge> graph){

        /*
            We only care about the edges we have.
            Grab the edges and create a matrix size N^2 TODO: Make this smaller n(n-1)?
         */
        Collection<Edge> edges = graph.getEdges();
        this.adjacencies = DoubleFactory2D.dense.make(graph.getVertexCount(), graph.getVertexCount());
        this.graph = graph;

        //Break each edge apart. Add them to our set of vertices.
        for(Edge edge : edges){
            Pair<Vertex> endpoints = graph.getEndpoints(edge);

            Vertex left = endpoints.getFirst();
            int leftDegree = graph.degree(left);

            Vertex right = endpoints.getSecond();
            int rightDegree = graph.degree(right);

            if(!vertices.contains(left)) {
                vertices.add(left);
                orderEdges.put(left, leftDegree);
            }
            if(!vertices.contains(right)){
                vertices.add(right);
                orderEdges.put(right, rightDegree);
            }

            this.adjacencies.set(vertices.indexOf(left), vertices.indexOf(right), 1); //We will use the positioning in the arrayList as our x,y
        }

    }

    //TODO: Node Iteration Count
    public int getNumberOfTriangles(){


        return 0;
    }



    public void toStringMatrix() {
        System.out.println(adjacencies);
    }



}
