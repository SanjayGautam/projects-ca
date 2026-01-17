package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Request payload for creating a widget")
public class CreateWidgetRequest {
  @Schema(description = "Display name for the widget", example = "Acme Alert Widget")
  @NotBlank
  @Size(max = 120)
  private String name;

  @Schema(description = "Short summary of what the widget does", example = "Sends alert notifications to the Acme platform")
  @Size(max = 280)
  private String description;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
