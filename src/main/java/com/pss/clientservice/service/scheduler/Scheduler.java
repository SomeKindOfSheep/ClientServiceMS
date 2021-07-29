package com.pss.clientservice.service.scheduler;

import com.pss.clientservice.service.entity.WorkRequest;
import com.pss.clientservice.service.repository.WorkRequestRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@Component
@EnableAsync
public class Scheduler {

    @Autowired
    WorkRequestRepository workRequestRepository;


    @Async
    //this runs every 24 hours
    //@Scheduled(fixedRate = 86400000)
    @Scheduled(fixedRate = 5000)
    public void scheduledTask() {
        try{
            findAndResolveActiveRequests();
        }catch (ParseException e){
            e.printStackTrace();
        }

    }


    private void findAndResolveActiveRequests() throws ParseException {

        List<WorkRequest> workRequests = workRequestRepository.findAllActiveWorkRequests();
        for (WorkRequest request: workRequests){

            if (request.getDate_created().plusDays(7).isBefore(LocalDate.now())){
                request.setDate_resolved(LocalDate.now());
                BeanUtils.copyProperties(request, request, "id");
                workRequestRepository.saveAndFlush(request);
            }
        }
    }


}
