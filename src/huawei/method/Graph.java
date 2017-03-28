package huawei.method;

import java.util.Scanner;

public class Graph {
    private int vertexNum;
    private int edgeNum;
    private Bag<Edge>[] adj;

    public Graph(int vertexNum){
        this.vertexNum = vertexNum;
        this.edgeNum = 0;
        this.adj = (Bag<Edge>[]) new Bag[vertexNum];

        for (int i=0;i<vertexNum;i++){
            adj[i] = new Bag<>();
        }
    }

    public Graph(String[] graphContent){
        this(new Scanner(graphContent[0]).useDelimiter(" ").nextInt());
        Scanner scanner = new Scanner(graphContent[0]);
        scanner.useDelimiter(" ");
        int vertexNum = scanner.nextInt();
        int edgeNum = scanner.nextInt();
        for(int i=0;i<edgeNum;i++){
            Scanner tempScanner = new Scanner(graphContent[i+4]);
            tempScanner.useDelimiter(" ");
            int startPoint = tempScanner.nextInt();
            int endPoint = tempScanner.nextInt();
            int cost = tempScanner.nextInt();
            int capacity = tempScanner.nextInt();

            addEdge(new Edge(startPoint,endPoint,cost,capacity));
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
        int endPoint = edge.getEndPoint();
        adj[startPoint].add(edge);
        adj[endPoint].add(edge);
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
