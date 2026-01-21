package com.example.backend;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Health status response payload.")
public class HealthResponse {

    @Schema(description = "Overall service status.", example = "UP")
    private String status;

    public HealthResponse() {
    }

    public HealthResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
