package com.ibm.crud.repository;

import com.ibm.crud.domain.Card;
import com.ibm.crud.domain.Client;
import com.ibm.crud.domain.Module;
import com.ibm.crud.domain.PkId;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ClientRepositoryTest {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CardRepository cardRepository;

    /**
     * find by all cards and Consumption history by client id
     * @throws Exception
     */
    @Test
    public void findOneTest() throws Exception {
        PkId id = new PkId();
        id.setId(3L);
        id.setModuleId(2L);
        Client client = clientRepository.findById(id).orElseThrow();
        Module module = client.getModule();
        Set<Card> cards = client.getCards();
        log.info("card =>>>>"+ cards.toString());
        log.info("client =>>>>"+client);
        log.info("module =>>>>"+ module);
        Assert.assertNotNull(client);
        Assert.assertTrue(3 == client.getId());
    }

    @Test
    public void insertChild() throws Exception {
        PkId id = new PkId();
        id.setId(3L);
        id.setModuleId(2L);
        Client client = clientRepository.findById(id).orElseThrow();
        Card card = new Card();
        card.setType("child2");
        card.setCcv("child2");
        card.setModuleId(2L);
        card.setNumber("child2");
        card.setClientId(3L);
        cardRepository.saveAndFlush(card);
        client.getCards().add(card);
        clientRepository.saveAndFlush(client);
    }
}