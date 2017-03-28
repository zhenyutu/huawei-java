package huawei.method;

import com.google.common.collect.Table;
import huawei.model.Subways;

public class Graph {
    private int vertexNum;
    private int edgeNum;
    private Bag<Edge>[] adj;

    public Graph(int vertexNum){
        this.vertexNum = vertexNum;
        this.adj = (Bag<Edge>[]) new Bag[vertexNum];

        for (int i=0;i<vertexNum;i++){
            adj[i] = new Bag<>();
        }
    }

    public Graph(Table<String, String, Subways.DistanceInfo> distanceTable){
        this(40);
        for (Table.Cell<String, String, Subways.DistanceInfo> row : distanceTable.cellSet()){
            int startPoint = Integer.parseInt(row.getRowKey().substring(1));
            int endPoint = Integer.parseInt(row.getColumnKey().substring(1));
            int distance = row.getValue().getDistance();
            String subwayName = row.getValue().getSubwayName();

            addEdge(new Edge(startPoint,endPoint,distance,subwayName));
        }
    }

    public int getVertexNum(){
        return vertexNum;
    }

    public int getEdgeNum(){
        return edgeNum;
    }

    public Bag<Edge> getEdges(){
        Bag<Edge> bag = new Bag<>();
        for (int vertex=0;vertex<vertexNum;vertex++){
            for (Edge edge:adj[vertex]){
                if (edge.otherPoint(vertex)>vertex)
                    bag.add(edge);
            }
        }
        return bag;
    }

    public Iterable<Edge> getAdj(int vertex){
        return adj[vertex];
    }

    public void addEdge(Edge edge){
        int startPoint = edge.getStartPoint();
        adj[startPoint].add(edge);
        edgeNum++;
    }

    public String toString(){
        String s = vertexNum + " vertex," + edgeNum + " edge\n";
        for (int i=0;i<vertexNum;i++){
            s+= i+": ";
            for (Edge adj : this.adj[i]){
                s+=adj.toString() + " ";
            }
            s+="\n";
        }
        return s;
    }

}
