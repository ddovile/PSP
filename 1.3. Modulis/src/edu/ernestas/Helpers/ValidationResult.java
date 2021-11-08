package edu.ernestas.Helpers;

public class ValidationResult {
    private String message;
    private boolean status;

    public ValidationResult() {
        this.message = "No validation errors were found";
        this.status = true;
    }

    public ValidationResult(String message, boolean status) {
        if (message == null || message.isEmpty()) {
            this.message = "Input contains some errors.";
        }
        else {
            this.message = message;
        }
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
