package com.github.matheus.banksimulationapi.model;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String details;

}