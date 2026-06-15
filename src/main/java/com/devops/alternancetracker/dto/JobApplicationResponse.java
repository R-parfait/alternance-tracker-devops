package com.devops.alternancetracker.dto;

import java.time.LocalDate;

import com.devops.alternancetracker.model.ApplicationStatus;

public record JobApplicationResponse (
    Long id,
    String company,
    String position,
    String city,
    ApplicationStatus status,
    LocalDate appliedDate
){
    
}
