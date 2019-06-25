package com.abc.bank.service;


import com.abc.bank.pojo.CardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public interface CardInfojapService extends JpaRepository<CardInfo, Integer> {

}
