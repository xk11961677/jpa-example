package com.ibm.crud.repository;

import com.ibm.crud.domain.Module;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ModuleRepositoryTest {
    @Autowired
    private ModuleRepository moduleRepository;


    /**
     * find by all cards and Consumption history by client id
     * @throws Exception
     */
    @Test
    public void findOneTest() throws Exception {
        Module module = moduleRepository.findById(1L).orElseThrow();
        log.info(module.toString());
        module.getCards().forEach(card->{
            log.info("client===>>"+ card.getClient());
        });
        Assert.assertNotNull(module);
        Assert.assertTrue(1 == module.getId());
    }

    @Test
    public void insert() {
        Module module = new Module();
        module.setName("1");
        moduleRepository.save(module);
    }
}