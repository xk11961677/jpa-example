package com.ibm.crud.repository;

import com.ibm.crud.domain.Card;
import com.ibm.crud.domain.PkId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, PkId> {

//    List<Card> findById(Long id);
    Card findOneByIdAndModuleId(Long id,Long moduleId);
}
