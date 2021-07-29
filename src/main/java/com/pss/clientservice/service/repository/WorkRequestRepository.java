package com.pss.clientservice.service.repository;


import com.pss.clientservice.service.entity.WorkRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRequestRepository extends JpaRepository<WorkRequest, Long> {

   @Query(
           value = "SELECT * FROM work_request wr WHERE wr.date_resolved IS NULL",
           nativeQuery = true)
   List<WorkRequest> findAllActiveWorkRequests();
}
