package com.devops.alternancetracker.mapper;

import com.devops.alternancetracker.dto.JobApplicationRequest;
import com.devops.alternancetracker.dto.JobApplicationResponse;
import com.devops.alternancetracker.model.JobApplication;
import org.springframework.stereotype.Component;

@Component
public class JobApplicationMapper {

    public JobApplication toEntity(JobApplicationRequest request){
        return new JobApplication(
            request.company(),
            request.position(),
            request.city(),
            request.status(),
            request.appliedDate()
        );
    }

    public JobApplicationResponse toResponse(JobApplication application){
        return new JobApplicationResponse(
            application.getId(),
            application.getCompany(),
            application.getPosition(),
            application.getCity(),
            application.getStatus(),
            application.getAppliedDate()
        );
    }
    
}
