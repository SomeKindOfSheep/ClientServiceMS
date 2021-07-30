package com.pss.clientservice.service.controllers;

import com.pss.clientservice.service.dto.State;
import com.pss.clientservice.service.entity.WorkRequest;
import com.pss.clientservice.service.entity.WorkRequestPriority;
import com.pss.clientservice.service.entity.exception.ResourceNotFoundException;
import com.pss.clientservice.service.exception.MessagingAPIException;
import com.pss.clientservice.service.exception.MessagingAPIExceptionMessage;
import com.pss.clientservice.service.exception.MessagingAPII18nMessageResolver;
import com.pss.clientservice.service.services.WorkRequestService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/workrequest")
public class WorkRequestController {

    @Autowired
    WorkRequestService workRequestService;

    @Autowired
    MessagingAPII18nMessageResolver messagingAPII18nMessageResolver;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON)
    public WorkRequestDTO viewWorkRequestById(@PathVariable String id) throws ResourceNotFoundException {
        if (StringUtils.isEmpty(id)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }
        return workRequestService.viewWorkRequestById(Long.parseLong(id));
    }

    @PostMapping(path = "/{clientId}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public WorkRequest addWorkRequest(@RequestBody WorkRequest workRequest, @PathVariable String clientId) throws ResourceNotFoundException {
        if (StringUtils.isEmpty(clientId)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }
        return workRequestService.addWorkRequest(workRequest, Long.parseLong(clientId.trim()));
    }

    @PutMapping(path = "/addtechnician/{workRequestId}/{technicianId}", produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public WorkRequest addTechniciansToRequest(@PathVariable String workRequestId, @PathVariable String technicianId) throws ResourceNotFoundException {
        if (StringUtils.isEmpty(workRequestId) || StringUtils.isEmpty(technicianId)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }
        return workRequestService.addTechniciansToRequest(Long.parseLong(workRequestId.trim()), Long.parseLong(technicianId.trim()));
    }

    @DeleteMapping(path = "/removetechnician/{workRequestId}/{technicianId}", produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.OK)
    public WorkRequest removeTechniciansFromRequest(@PathVariable String workRequestId, @PathVariable String technicianId) throws ResourceNotFoundException {
        if (StringUtils.isEmpty(workRequestId) || StringUtils.isEmpty(technicianId)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }
        return workRequestService.removeTechniciansFromRequest(Long.parseLong(workRequestId.trim()), Long.parseLong(technicianId.trim()));
    }

    @PutMapping(path = "/changestate/{state}/{workrequestId}", produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public WorkRequest changeState(@PathVariable State state, @PathVariable String workrequestId) throws ResourceNotFoundException {
        if (StringUtils.isEmpty(workrequestId)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }
        return workRequestService.changeState(state, Long.parseLong(workrequestId.trim()));
    }

    @PutMapping(path = "/changepriority/{priority}/{workrequestId}", produces = MediaType.APPLICATION_JSON)
    @ResponseStatus(HttpStatus.CREATED)
    public WorkRequest changePriority(@PathVariable WorkRequestPriority priority, @PathVariable String workrequestId) throws ResourceNotFoundException {
        if (StringUtils.isEmpty(workrequestId)){
            throw MessagingAPIException.throwException(MessagingAPIExceptionMessage.BAD_MESSAGE_400,
                    messagingAPII18nMessageResolver);
        }
        return workRequestService.changePriority(priority, Long.parseLong(workrequestId.trim()));
    }


}
