package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Widget resource returned from the API")
public class Widget {
  @Schema(description = "Unique widget identifier", example = "widget-41d2")
  private String id;

  @Schema(description = "Display name for the widget", example = "Acme Alert Widget")
  private String name;

  @Schema(description = "Short summary of what the widget does", example = "Sends alert notifications to the Acme platform")
  private String description;

  public Widget() {
  }

  public Widget(String id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

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
