package huawei.biz.impl;

import com.google.common.collect.Table;
import huawei.biz.CardManager;
import huawei.exam.CardEnum;
import huawei.exam.ReturnCodeEnum;
import huawei.exam.SubwayException;
import huawei.method.DijkstraSP;
import huawei.method.Graph;
import huawei.model.Card;
import huawei.model.ConsumeRecord;
import huawei.model.Subways;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: 待考生实现类</p>
 *
 * <p>Description: 卡票中心</p>
 *
 * <p>Copyright: Copyright (c) 2013</p>
 *
 * <p>Company: </p>
 *
 * @author
 * @version 1.0 OperationCenter V100R002C20, 2015/9/7]
 */
public class CardManagerImpl implements CardManager
{
    private static final int MAX_COUNT = 99;

    private int count = 0;
    private Map<String,Card> cards = new HashMap<>();
    private Map<String,List<ConsumeRecord>> consumeRecords = new HashMap<>();

    @Override
    public Card buyCard(String enterStation, String exitStation)
        throws SubwayException
    {
        Card card;
        if (count>MAX_COUNT)
            throw new SubwayException(ReturnCodeEnum.E08,null);
        else {
            CardManagerImpl cardManager = new CardManagerImpl();
            SubwayManagerImpl subwayManager =  new SubwayManagerImpl(cardManager);
            subwayManager.manageSubways();
            Table<String, String, Subways.DistanceInfo> distanceTable = subwayManager.getSubways().getStationDistances();

            Graph graph = new Graph(distanceTable);
            DijkstraSP dijkstraSP = new DijkstraSP(graph,Integer.parseInt(enterStation.substring(1)));
            int sumOfDistance = dijkstraSP.distTo(Integer.parseInt(exitStation.substring(1)));
            int money = SubwayManagerImpl.calculateCost(sumOfDistance);

            card = new Card();
            String cardId = String.valueOf(count);
            card.setCardId(cardId);
            card.setCardType(CardEnum.A);
            card.setMoney(money);
            cards.put(cardId,card);
            count++;
        }
        return card;
    }

    @Override
    public Card buyCard(CardEnum cardEnum, int money)
        throws SubwayException
    {
        Card card;
        if (count>MAX_COUNT)
            throw new SubwayException(ReturnCodeEnum.E08,null);
        else {
            card = new Card();
            String cardId = String.valueOf(count);
            card.setCardId(cardId);
            card.setCardType(cardEnum);
            card.setMoney(money);
            cards.put(cardId,card);
            count++;
        }
        return card;
    }

    @Override
    public Card recharge(String cardId, int money)
        throws SubwayException
    {
        Card card;
        if (!cards.keySet().contains(cardId))
            throw new SubwayException(ReturnCodeEnum.E06,null);
        else {
            card = cards.get(cardId);
            card.setMoney(card.getMoney()+money);
        }
        return card;
    }

    @Override
    public Card queryCard(String cardId) throws SubwayException
    {
        Card card;
        if (!cards.keySet().contains(cardId))
            throw new SubwayException(ReturnCodeEnum.E06,null);
        else {
            card = cards.get(cardId);
        }
        return card;
    }

    @Override
    public Card deleteCard(String cardId)
        throws SubwayException
    {
        Card card;
        if (!cards.keySet().contains(cardId))
            throw new SubwayException(ReturnCodeEnum.E06,null);
        else {
            card = cards.remove(cardId);
            count--;
        }
        return card;
    }

    @Override
    public Card consume(String cardId, int billing)
        throws SubwayException
    {
        Card card;
        if (!cards.keySet().contains(cardId))
            throw new SubwayException(ReturnCodeEnum.E06,null);
        else if (cards.get(cardId).getMoney()<billing) {
            card = cards.get(cardId);
            throw new SubwayException(ReturnCodeEnum.E02, card);
        }
        else {
            card = cards.get(cardId);
            card.setMoney(card.getMoney()- billing);
        }
        return card;
    }

    @Override
    public List<ConsumeRecord> queryConsumeRecord(String cardId)
        throws SubwayException
    {
        List<ConsumeRecord> consumeRecord;
        if (!cards.keySet().contains(cardId))
            throw new SubwayException(ReturnCodeEnum.E06,null);
        else {
            consumeRecord = consumeRecords.get(cardId);
        }
        return consumeRecord;
    }
}