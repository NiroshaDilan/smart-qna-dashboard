package com.smart.qna.repository;

import com.smart.qna.entity.ApprovedMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ApprovedSmsRepository extends JpaRepository<ApprovedMessage,Integer> {

    Page<ApprovedMessage> findAllByReadStatus(String readStatus, Pageable pageable);

    @Transactional
    @Modifying
    @Query(nativeQuery=true,value="INSERT INTO approved (phoneno, sendername, msg, hrbranch,approvedDateTime,priority1,prioratizeddatetime) " +
            " SELECT uId, hrname, msg, hrbranch,?1,?2,?3 FROM visitors WHERE id=?4")
    int insertToApproved(String approvedDateTime,int priority1,String prioratizeddatetime,  int id);

    @Transactional
    @Modifying
    @Query(nativeQuery=true,value="UPDATE approved SET priority1=?1,priority2=?2,prioratizeddatetime=?3 WHERE id=?4")
    int prioritizeApproved(int priority1,int priority2,String prioratizeddatetime, int id);

    @Transactional
    @Modifying
    @Query(nativeQuery=true,value="UPDATE approved SET readstatus=?1 WHERE id=?2")
    int updateAnswered(String readStatus, int id);

}
