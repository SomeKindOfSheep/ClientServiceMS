package com.pss.clientservice.service.services;


import com.pss.clientservice.service.controllers.WorkRequestDTO;
import com.pss.clientservice.service.dto.Call;
import com.pss.clientservice.service.dto.Employee;
import com.pss.clientservice.service.dto.State;
import com.pss.clientservice.service.entity.*;
import com.pss.clientservice.service.repository.ClientRepository;
import com.pss.clientservice.service.repository.EmployeeRepository;
import com.pss.clientservice.service.repository.WorkRequestRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WorkRequestService {

    @Autowired
    WorkRequestRepository workRequestRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ClientRepository clientRepository;

    public WorkRequest addWorkRequest(WorkRequest workRequest, long clientId) {

        workRequest.setClient(clientRepository.findById(clientId).get());

        return workRequestRepository.saveAndFlush(workRequest);
    }

    public WorkRequestDTO viewWorkRequestById(long workRequestId) {

        return mapDTO(workRequestRepository.findById(workRequestId).get());
    }

    private Optional<WorkRequest> getWorkRequestById(long workRequestId) {

        return workRequestRepository.findById(workRequestId);
    }

    private WorkRequestDTO mapDTO(WorkRequest workRequest){
        WorkRequestDTO dto = new WorkRequestDTO();
        dto.setId(workRequest.getId());
        dto.setDescriptionOfProblem(workRequest.getDescriptionOfProblem());
        dto.setDescriptionOfSolution(workRequest.getDescriptionOfSolution());
        dto.setEstimatedTimeToCompletion(workRequest.getEstimatedTimeToCompletion());
        dto.setState(workRequest.getState());
        dto.setClientId(workRequest.getClient().getId());
        dto.setWorkRequestPriority(workRequest.getWorkRequestPriority());

        List<Long> employeeIds = new ArrayList<>();
        for (Employee employee: workRequest.getEmployee() ){
            employeeIds.add(employee.getId());
        }
        List<Long> callids = new ArrayList<>();
        for (Call calls: workRequest.getCalls() ){
            callids.add(calls.getId());
        }

        dto.setCallIds(callids);
        dto.setEmployeeId(employeeIds);
        dto.setDateCreated(workRequest.getDate_created());
        dto.setDateResolved(workRequest.getDate_resolved());

        return dto;
    }

    public WorkRequest addTechniciansToRequest(long workRequestId, long technicianId) {
        WorkRequest workRequest = getWorkRequestById(workRequestId).get();
        List<Employee> technicians = workRequest.getEmployee();
        technicians.add(employeeRepository.findById(technicianId).get());
        BeanUtils.copyProperties(workRequest, workRequest, "id");

        return workRequestRepository.saveAndFlush(workRequest);
    }

    public WorkRequest removeTechniciansFromRequest(long workRequestId, long technicianId) {
        WorkRequest workRequest = getWorkRequestById(workRequestId).get();
        List<Employee> technicians = workRequest.getEmployee();
        technicians.removeIf(employee -> employee.getId() == technicianId);
        BeanUtils.copyProperties(workRequest, workRequest, "id");

        return workRequestRepository.saveAndFlush(workRequest);
    }

    public WorkRequest changeState(State state, long workRequestId) {

        WorkRequest workRequest = getWorkRequestById(workRequestId).get();
        workRequest.setState(state);
        if (state == State.SERVICE_CLOSED){
            workRequest.setDate_resolved(LocalDate.now());
        }
        BeanUtils.copyProperties(workRequest, workRequest, "id");
        return workRequestRepository.saveAndFlush(workRequest);
    }

    public WorkRequest changePriority(WorkRequestPriority priority, long workRequestId ) {
        WorkRequest workRequest = getWorkRequestById(workRequestId).get();
        workRequest.setWorkRequestPriority(priority);
        BeanUtils.copyProperties(workRequest, workRequest, "id");
        return workRequestRepository.saveAndFlush(workRequest);
    }

}
