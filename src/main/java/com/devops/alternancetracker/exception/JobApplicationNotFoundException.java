package com.devops.alternancetracker.exception;

public class JobApplicationNotFoundException extends RuntimeException {
    
    public JobApplicationNotFoundException(Long id){
        super("Candidature introuvable avec l'identifiant: " +id);
    }
    
}
