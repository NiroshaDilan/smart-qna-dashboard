package com.smart.qna.repository;

import com.smart.qna.entity.ApprovedMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ApprovedSmsRepository extends JpaRepository<ApprovedMessage,Integer> {



    @Transactional
    @Modifying
    @Query(nativeQuery=true,value="INSERT INTO approved (phoneno, sendername, msg, hrbranch,approvedDateTime,priority_level_1,prioratizeddatetime) " +
            " SELECT uId, hrname, msg, hrbranch,?1,?2,?3 FROM visitors WHERE id=?4")
    int insertToApproved(String approvedDateTime,int priorityLevel_1,String prioratizeddatetime,  int id);
    }
