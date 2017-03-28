package huawei.method;

public class Edge implements Comparable<Edge>{
    private int startPoint;
    private int endPoint;
    private int distance;
    private String subwayName;

    public Edge(int startPoint,int endPoint,int distance,String subwayName){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.distance = distance;
        this.subwayName = subwayName;
    }


    public Integer getStartPoint(){
        return startPoint;
    }

    public Integer getEndPoint(){
        return endPoint;
    }

    public Integer getDistance(){
        return distance;
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
        if (this.distance < edge.distance)
            return -1;
        else if (this.distance>edge.distance)
            return 1;
        else
            return 0;
    }

    public String toString(){
        return String.format("(%d-%d %d %s)",startPoint,endPoint,distance,subwayName);
    }
}
