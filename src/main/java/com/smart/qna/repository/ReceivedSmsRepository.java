package com.smart.qna.repository;

import com.smart.qna.entity.TextMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceivedSmsRepository extends JpaRepository<TextMessage,Integer> {

    Page<TextMessage> findAllByStatus(String status,Pageable pageable);
}
