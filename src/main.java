/**
 * Created by nolan on 2015-10-28.
 */

import algorithms.counting.degreeDistribution;
import algorithms.counting.nodeIterator;
import core.components.Edge;
import core.components.Vertex;
import core.visualizer.Visualizer;
import edu.uci.ics.jung.graph.Graph;
import generators.random.Barabasi;

import java.util.Arrays;


public class main {

    public static void main(String[] args){

        Graph<Vertex, Edge> graph = new Barabasi<>().getGraph(3, 4);

        nodeIterator<Vertex, Edge> eit = new nodeIterator<>(graph);
        Visualizer.viewGraph(graph);
        System.out.println(eit.getNumberOfTriangles());

        degreeDistribution deg = new degreeDistribution(graph);
        System.out.println(Arrays.toString(deg.getNodeRank()));
    }


}