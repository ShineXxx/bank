package com.abc.bank.service;

import com.abc.bank.pojo.CardInfo;

public interface CardInfoService {
    CardInfo getCardInfo(String username);
    boolean insertCardinfo(CardInfo cardInfo);
    boolean updateCardPass(CardInfo cardinfo);
}
