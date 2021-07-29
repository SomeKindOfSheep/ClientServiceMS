package com.pss.clientservice.service.controllers;

import com.pss.clientservice.service.dto.State;
import com.pss.clientservice.service.entity.WorkRequestPriority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkRequestDTO {

    private long id;
    private WorkRequestPriority workRequestPriority;
    private List<Long> callIds;
    private String descriptionOfProblem;
    private String descriptionOfSolution;
    private long estimatedTimeToCompletion;
    private List<Long> employeeId;
    private State state;
    private long clientId;
    private LocalDate dateCreated;
    private LocalDate dateResolved;

}
