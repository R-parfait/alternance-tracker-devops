package com.devops.alternancetracker.service;

import com.devops.alternancetracker.dto.JobApplicationRequest;
import com.devops.alternancetracker.dto.JobApplicationResponse;
import com.devops.alternancetracker.mapper.JobApplicationMapper;
import com.devops.alternancetracker.model.JobApplication;
import com.devops.alternancetracker.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository applicationRepository;
    private final JobApplicationMapper applicationMapper;

    public JobApplicationService(JobApplicationRepository applicationRepository, JobApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
    }

    public List<JobApplicationResponse> getAllApplications() {
        return applicationRepository.findAll()
                .stream()
                .map(applicationMapper::toResponse)
                .toList();
    }

    public JobApplicationResponse createApplication(JobApplicationRequest request) {
        JobApplication application = applicationMapper.toEntity(request);
        JobApplication savedApplication = applicationRepository.save(application);

        return applicationMapper.toResponse(savedApplication);
    }
}