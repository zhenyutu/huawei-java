package huawei.biz.impl;

import com.google.common.collect.Table;
import huawei.method.DijkstraSP;
import huawei.method.Graph;
import huawei.model.Subways;
import org.junit.Test;

public class SubwayManagerImplTest {
    @Test
    public void test(){
//        CardManagerImpl cardManager = new CardManagerImpl();
//        SubwayManagerImpl subwayManager =  new SubwayManagerImpl(cardManager);
//
//        subwayManager.manageSubways();
//        Table<String, String, Subways.DistanceInfo> distanceTable = subwayManager.getSubways().getStationDistances();
//
//        Graph graph = new Graph(distanceTable);
//        DijkstraSP dijkstraSP = new DijkstraSP(graph,1);
//        System.out.println(dijkstraSP.pathTo(5));
//        System.out.println(dijkstraSP.distTo(5));

        String str = "10:00";
        String str2 = "10:01";
        System.out.println(str2.compareTo(str));
    }
}