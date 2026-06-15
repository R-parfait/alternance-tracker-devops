package com.devops.alternancetracker.dto;

import java.time.LocalDate;

import com.devops.alternancetracker.model.ApplicationStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JobApplicationRequest (

    @NotBlank(message = "le nom de l'entreprise est obligatoire")
    String company,

    @NotBlank(message = "l'intitulé du poste est obligatoire")
    String position,

    String city,

    @NotNull(message = "le status est obligatoire")
    ApplicationStatus status,

    @NotNull(message = "la date d'application est obligatoire")
    LocalDate appliedDate

){
    
}
