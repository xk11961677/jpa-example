package com.ibm.crud.repository;

import com.ibm.crud.domain.Card;
import com.ibm.crud.domain.Client;
import com.ibm.crud.domain.PkId;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class CardRepositoryTest {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ClientRepository clientRepository;


    /**
     * find by all Consumption history by card id
     * @throws Exception
     */
    @Test
    public void findOneTest() throws Exception {
        PkId id = new PkId();
        id.setId(1L);
        id.setModuleId(1L);
        Card card = cardRepository.findById(id).orElseThrow();
        log.info(card.toString());
        Assert.assertNotNull(card);
        Assert.assertTrue(1 == card.getId());
    }

    @Test
    public void insertOneTest() throws Exception {
        Card card = new Card();
        card.setModuleId(1L);
        card.setCcv("33");
        card.setNumber("33");
        card.setType("33");
        card.setClientId(3L);
        card = cardRepository.save(card);
        Assert.assertNotNull(card.getId());
    }

    /**
     * 联合主键存在一个为空则 insert
     * 全部存在则认为 update
     * @throws Exception
     */
    @Test
    public void updateOneTest() throws Exception {
        PkId id = new PkId();
        id.setId(6L);
        id.setModuleId(2L);
        Card card = cardRepository.findById(id).orElseThrow();
        // 部分主键查询
//        List<Card> list = cardRepository.findById(6L);
//        Card card = cardRepository.findOneByIdAndModuleId(6L,2L);

//        card.setId(6L);
//        card.setModuleId(2L);
        card.setCcv("33111111");
        card.setNumber("bbbb");
        card.setType("33");
        Client client = card.getClient();
        client.setAddress("bbbb");
        card.setClient(client); // clientRepository不调用save, client不会变
        clientRepository.saveAndFlush(client);
        cardRepository.saveAndFlush(card);
    }

    /**
     * 联合主键存在一个为空则 insert
     * 全部存在则认为 update
     * @throws Exception
     */
    @Test
    public void copyOneTest() throws Exception {
        Card card = new Card();
        card.setId(7L);
        card.setModuleId(2L);
        card.setCcv("fffffff");
        card.setNumber("fffffff");
        card.setType("fffffff");
        card.setClientId(3L);
        cardRepository.save(card);
    }


    /**
     * 先删除在添加
     * @throws Exception
     */
    @Test
    public void deleteAndInsertTest() throws Exception {
        PkId id = new PkId();
        id.setId(7L);
        id.setModuleId(7L);
        Card card = cardRepository.findById(id).orElseThrow();
        cardRepository.deleteById(id);
        cardRepository.save(card);
    }
}