package huawei.biz.impl;

import huawei.exam.SubwayException;
import huawei.model.Card;
import org.junit.Test;

public class CardManagerImplTest{
    @Test
    public void test() throws SubwayException{
        CardManagerImpl cardManager = new CardManagerImpl();
        Card card = cardManager.buyCard("S1","S5");
        System.out.println(card.getCardId()+"-"+card.getCardType()+"-"+card.getMoney());
    }
}