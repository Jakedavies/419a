/**
 * Created by nolan on 2015-10-28.
 */

import algorithms.counting.nodeIterator;
import core.components.Edge;
import core.components.Vertex;
import core.visualizer.Visualizer;
import edu.uci.ics.jung.graph.Graph;
import generators.random.Barabasi;


public class main {

    public static void main(String[] args){

        Graph<Vertex, Edge> graph = new Barabasi<>().getGraph(3, 4);

        nodeIterator<Vertex, Edge> eit = new nodeIterator<>(graph);
        Visualizer.viewGraph(graph);
        System.out.println(eit.getNumberOfTriangles());
    }


}