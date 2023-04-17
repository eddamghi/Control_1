package com.eddamghi.Application.DAO.repositories;

import com.eddamghi.Application.DAO.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Page<Client> findByNameContains(String keyword, Pageable pageable);
}
