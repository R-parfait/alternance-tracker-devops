package com.devops.alternancetracker.controller;

import com.devops.alternancetracker.dto.JobApplicationRequest;
import com.devops.alternancetracker.dto.JobApplicationResponse;
import com.devops.alternancetracker.service.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    private final JobApplicationService applicationService;

    public JobApplicationController(JobApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public List<JobApplicationResponse> getAllApplications() {
        return this.applicationService.getAllApplications();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JobApplicationResponse createApplication(@Valid @RequestBody JobApplicationRequest request) {
        return this.applicationService.createApplication(request);
    }

    @GetMapping("/{id}")
    public JobApplicationResponse getApplicationById(@PathVariable Long id){
        return this.applicationService.getApplicationById(id);
    }

    @PutMapping("/{id}")
    public JobApplicationResponse updateApplication(
        @PathVariable Long id, 
        @Valid @RequestBody JobApplicationRequest request){
        return this.applicationService.updateApplication(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteApplication(@PathVariable Long id){
        this.applicationService.deleteApplication(id);
    }
}