package com.abc.bank.service;

import com.abc.bank.pojo.CardInfo;

public interface CardInfoService {
    public CardInfo getCardInfo(String username);
    public boolean insertCardinfo(CardInfo cardInfo);
}
