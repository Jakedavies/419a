package algorithms.counting;
import edu.uci.ics.jung.graph.Graph;
import java.util.Collection;

public class nodeIterator<Vertex,Edge> {

    //Keep a handle on the vertices and edges
    Collection<Vertex >vertices;
    Collection<Edge> edges;

    //The original graph
    Graph<Vertex, Edge> graph;

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
     * Finds the number of triangles in a graph using the node iterator method.
     * @return a double with the number of triangles in graph g.
     */
    public double getNumberOfTriangle(){
        double triangles = 0;
        for(Vertex v : vertices){
            Collection<Vertex> neighbors = graph.getNeighbors(v);
            for(Vertex w : neighbors){
                for(Vertex k : neighbors){
                    if(edges.contains(graph.findEdge(w,k))){
                        triangles += .5;
                    }
                }
            }
        }
        return triangles/3;
    }
}
