package algorithms.counting;

import core.components.Edge;
import core.components.Vertex;
import edu.uci.ics.jung.graph.Graph;

/**
 * Created by jakedavies on 15-11-24.
 */
public class DegreeDistribution {
    Graph<Vertex,Edge> g;
    public DegreeDistribution(Graph g){
        this.g = g;
    }
    public int[] getNodeRank(){
        int[] degrees = new int[g.getVertexCount()];

        for(Vertex v : g.getVertices()){
            degrees[g.degree(v)]++;
        }
        return degrees;
    }
}
