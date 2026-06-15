package com.devops.alternancetracker.service;

import com.devops.alternancetracker.dto.JobApplicationRequest;
import com.devops.alternancetracker.dto.JobApplicationResponse;
import com.devops.alternancetracker.exception.JobApplicationNotFoundException;
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

    public JobApplicationResponse getApplicationById(Long id){
        JobApplication application = applicationRepository.findById(id)
            .orElseThrow(() -> new JobApplicationNotFoundException(id));

        return applicationMapper.toResponse(application);
    }

    public JobApplicationResponse updateApplication(Long id, JobApplicationRequest request){
        JobApplication application = applicationRepository.findById(id)
            .orElseThrow(() -> new JobApplicationNotFoundException(id));

        applicationMapper.updateEntity(request, application);

        JobApplication updatedApplication = applicationRepository.save(application);

        return applicationMapper.toResponse(updatedApplication);
    }

    public void deleteApplication(Long id){
        if(!applicationRepository.existsById(id)){
            throw new JobApplicationNotFoundException(id);
        }

        applicationRepository.deleteById(id);
    }
}