package com.eddamghi.Application.DAO.repositories;
import com.eddamghi.Application.DAO.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,Long>{

Page<Subscription> findByClientId(Long clientId, Pageable pageable);

}
