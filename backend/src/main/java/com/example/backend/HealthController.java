package com.example.backend;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Health", description = "Health check endpoints.")
public class HealthController {

    @GetMapping("/health")
    @Operation(
            summary = "Service health check",
            description = "Returns the current service status.",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Service is available.",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = HealthResponse.class),
                                examples = @ExampleObject(
                                        name = "healthy",
                                        summary = "Healthy response",
                                        value = "{\"status\":\"UP\"}"
                                )
                        )
                )
            }
    )
    public HealthResponse health() {
        return new HealthResponse("UP");
    }
}
