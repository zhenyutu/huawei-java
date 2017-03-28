package huawei.method;

public class Edge implements Comparable<Edge>{
    private int startPoint;
    private int endPoint;
    private int cost;
    private int capacity;

    public Edge(int startPoint,int endPoint,int cost,int capacity){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.cost = cost;
        this.capacity = capacity;
    }

    public Integer getCost(){
        return cost;
    }

    public Integer getCapacity(){
        return capacity;
    }

    public Integer getStartPoint(){
        return startPoint;
    }

    public Integer getEndPoint(){
        return endPoint;
    }

    public Integer otherPoint(Integer vertex){
        if (vertex == startPoint)
            return endPoint;
        else if (vertex == endPoint)
            return startPoint;
        else
            throw new RuntimeException("Inconsistent edge");
    }

    public int compareTo(Edge edge){
        if (this.cost < edge.cost)
            return -1;
        else if (this.cost>edge.cost)
            return 1;
        else
            return 0;
    }

    public String toString(){
        return String.format("(%d-%d %d %d)",startPoint,endPoint,cost,capacity);
    }
}
