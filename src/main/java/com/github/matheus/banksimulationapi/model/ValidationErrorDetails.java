package com.github.matheus.banksimulationapi.model;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationErrorDetails extends ErrorDetails {
        private Map<String, String> validationErrors;

        public ValidationErrorDetails(LocalDateTime timestamp, String message, String details, Map<String, String> validationErrors) {
            super(timestamp, message, details);
            this.validationErrors = validationErrors;
        }

        // Getters y setters
        public Map<String, String> getValidationErrors() {
            return validationErrors;
        }

        public void setValidationErrors(Map<String, String> validationErrors) {
            this.validationErrors = validationErrors;
        }
    }