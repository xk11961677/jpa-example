package com.ibm.crud.repository;

import com.ibm.crud.domain.Client;
import com.ibm.crud.domain.PkId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, PkId> {
}
