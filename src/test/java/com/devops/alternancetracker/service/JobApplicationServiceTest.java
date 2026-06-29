package com.devops.alternancetracker.service;

import com.devops.alternancetracker.dto.JobApplicationResponse;
import com.devops.alternancetracker.exception.JobApplicationNotFoundException;
import com.devops.alternancetracker.mapper.JobApplicationMapper;
import com.devops.alternancetracker.model.ApplicationStatus;
import com.devops.alternancetracker.model.JobApplication;
import com.devops.alternancetracker.repository.JobApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobApplicationServiceTest {

    @Mock
    private JobApplicationRepository applicationRepository;

    @Mock
    private JobApplicationMapper applicationMapper;

    @InjectMocks
    private JobApplicationService jobApplicationService;

    @Test
    void getApplicationById_ShouldReturnResponse_WhenApplicationExists() {
        // Arrange
        Long id = 1L;
        LocalDate appliedDate = LocalDate.of(2026, 6, 24);

        JobApplication application = new JobApplication(
                "Capgemini",
                "DevOps",
                "Paris",
                ApplicationStatus.PENDING,
                appliedDate
        );

        JobApplicationResponse expectedResponse = new JobApplicationResponse(
                id,
                "Capgemini",
                "DevOps",
                "Paris",
                ApplicationStatus.PENDING,
                appliedDate
        );

        when(applicationRepository.findById(id)).thenReturn(Optional.of(application));
        when(applicationMapper.toResponse(application)).thenReturn(expectedResponse);

        // Act
        JobApplicationResponse actualResponse = jobApplicationService.getApplicationById(id);

        // Assert
        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse.id()).isEqualTo(id);
        assertThat(actualResponse.company()).isEqualTo("Capgemini");
        assertThat(actualResponse.position()).isEqualTo("DevOps");
        assertThat(actualResponse.city()).isEqualTo("Paris");
        assertThat(actualResponse.status()).isEqualTo(ApplicationStatus.PENDING);
        assertThat(actualResponse.appliedDate()).isEqualTo(appliedDate);

        verify(applicationRepository, times(1)).findById(id);
        verify(applicationMapper, times(1)).toResponse(application);
        verifyNoMoreInteractions(applicationRepository, applicationMapper);
    }

    @Test
    void getApplicationById_ShouldThrowException_WhenApplicationDoesNotExist() {
        // Arrange
        Long id = 999L;

        when(applicationRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> jobApplicationService.getApplicationById(id))
                .isInstanceOf(JobApplicationNotFoundException.class)
                .hasMessageContaining("999");

        verify(applicationRepository, times(1)).findById(id);
        verifyNoInteractions(applicationMapper);
        verifyNoMoreInteractions(applicationRepository);
    }
}
