/**
 * Created by nolan on 2015-10-28.
 */

import algorithms.counting.edgeIterator;
import core.components.Edge;
import core.components.Vertex;
import edu.uci.ics.jung.graph.Graph;
import generators.random.Barabasi;


public class main {

    public static void main(String[] args){

       Graph<Vertex, Edge> graph = new Barabasi<>().getGraph(3, 10);

        edgeIterator<Vertex, Edge> eit = new edgeIterator<>();
        eit.setMatrix(graph);
        eit.toStringMatrix();
    }


}
