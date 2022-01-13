package com.smart.qna.repository;

import com.smart.qna.entity.TextMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReceivedSmsRepository extends JpaRepository<TextMessage,Integer> {

    Page<TextMessage> findAllByStatus(String status,Pageable pageable);

    @Modifying
    @Transactional
    @Query(nativeQuery=true, value= "UPDATE visitors SET status=?1 WHERE ID=?2")
    int updateStatus(String status,int id);
}
