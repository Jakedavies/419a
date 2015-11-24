package algorithms.counting;

import cern.colt.matrix.DoubleFactory2D;
import cern.colt.matrix.DoubleMatrix2D;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;

import java.util.ArrayList;
import java.util.Collection;


public class pageRank<Vertex,Edge> {

    //Our adjacency matrix
    DoubleMatrix2D adjacencies;

    //Keep a handle on the vertices
    ArrayList<Vertex> vertices = new ArrayList<>();

    //The original graph
    Graph<Vertex, Edge> graph;

    /**
     * This is a class I had originally written for triangle counting, but may be helpful with bootstrapping the
     * pagerank algorithm. Currently takes in the graph and creates a matrix where a 1 represents an edge between 2 nodes.
     */
    public pageRank(Graph<Vertex, Edge> graph){

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

            Vertex right = endpoints.getSecond();

            if(!vertices.contains(left)) {
                vertices.add(left);
            }
            if(!vertices.contains(right)){
                vertices.add(right);
            }

            //We will use the positioning in the arrayList as our x,y unless we
            this.adjacencies.set(vertices.indexOf(left), vertices.indexOf(right), 1);
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