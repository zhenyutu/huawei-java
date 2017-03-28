package huawei.method;

public class DijkstraSP {
    private Integer[] distTo;
    private Edge[] edgeTo;
    private IndexMinPQ<Integer> pq;
    private Integer source;

    public DijkstraSP(Graph G, int s) {
        this.source = s;
        distTo = new Integer[G.getVertexNum()];
        edgeTo = new Edge[G.getVertexNum()];
        for (int v = 0; v < G.getVertexNum(); v++)
            distTo[v] = Integer.MAX_VALUE;
        distTo[s] = 0;

        pq = new IndexMinPQ<>(G.getVertexNum());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (Edge e : G.getAdj(v))
                relax(e,v);
        }
    }

    private void relax(Edge e,Integer start) {
        int v = start, w = e.otherPoint(start);
        if (distTo[w] > distTo[v] + e.getDistance()) {
            distTo[w] = distTo[v] + e.getDistance();
            edgeTo[w] = e;
            if (pq.contains(w))
                pq.decreaseKey(w, distTo[w]);
            else
                pq.insert(w, distTo[w]);
        }
    }

    public Integer distTo(int v) {
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        Stack<Integer> path = new Stack<>();
        int tmp =v;
        path.push(tmp);
        while (tmp != source){
            Edge e = edgeTo[tmp];
            tmp = e.otherPoint(tmp);
            path.push(tmp);
        }

        return path;
    }
}
