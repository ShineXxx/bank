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

    public CardInfo getPssword(String username){
        CardInfo cardInfo=cardInfoMapper.selectByPrimaryKey(username);
        return cardInfo;
    }
}
