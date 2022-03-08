package com.ibm.crud.service;

import com.ibm.crud.domain.Card;
import com.ibm.crud.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void insert(Card card) {
        cardRepository.saveAndFlush(card);
    }
}
