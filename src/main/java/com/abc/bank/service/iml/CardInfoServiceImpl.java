package com.abc.bank.service.iml;

import com.abc.bank.Repository.CardInfoMapper;
import com.abc.bank.pojo.CardInfo;
import com.abc.bank.service.CardInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardInfoServiceImpl implements CardInfoService {
    @Autowired
    CardInfoMapper cardInfoMapper;


    @Override
    public CardInfo getCardInfo(String username) {
        CardInfo cardInfo=cardInfoMapper.selectByPrimaryKey(username);
        return cardInfo;
    }

    @Override
    public boolean insertCardinfo(CardInfo cardInfo) {
        int i=cardInfoMapper.insert(cardInfo);
        if (i>0)return true;
        else
        return false;
    }

}
