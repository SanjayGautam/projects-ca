package com.example.demo.controller;

import com.example.demo.model.CreateWidgetRequest;
import com.example.demo.model.Widget;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/widgets")
@Tag(name = "Widgets", description = "Operations for managing widgets")
public class WidgetController {
  private final List<Widget> widgets = new ArrayList<>();

  @Operation(
      summary = "List all widgets",
      description = "Returns every widget currently stored in the in-memory catalog.")
  @ApiResponse(
      responseCode = "200",
      description = "List of widgets",
      content = @Content(
          mediaType = MediaType.APPLICATION_JSON_VALUE,
          schema = @Schema(implementation = Widget.class),
          examples = @ExampleObject(
              name = "widgetList",
              summary = "Sample widget list",
              value = "[{\"id\":\"widget-41d2\",\"name\":\"Acme Alert Widget\",\"description\":\"Sends alert notifications to the Acme platform\"}]")
      ))
  @GetMapping
  public List<Widget> listWidgets() {
    if (widgets.isEmpty()) {
      widgets.add(new Widget("widget-41d2", "Acme Alert Widget", "Sends alert notifications to the Acme platform"));
    }
    return widgets;
  }

  @Operation(
      summary = "Create a widget",
      description = "Creates a new widget and returns the stored representation.")
  @RequestBody(
      required = true,
      description = "Widget creation payload",
      content = @Content(
          mediaType = MediaType.APPLICATION_JSON_VALUE,
          schema = @Schema(implementation = CreateWidgetRequest.class),
          examples = @ExampleObject(
              name = "widgetCreateRequest",
              summary = "Sample create request",
              value = "{\\\"name\\\":\\\"Acme Alert Widget\\\",\\\"description\\\":\\\"Sends alert notifications to the Acme platform\\\"}")))
  @ApiResponse(
      responseCode = "201",
      description = "Widget created",
      content = @Content(
          mediaType = MediaType.APPLICATION_JSON_VALUE,
          schema = @Schema(implementation = Widget.class),
          examples = @ExampleObject(
              name = "widgetCreated",
              summary = "Sample created widget",
              value = "{\"id\":\"widget-8f93\",\"name\":\"Acme Alert Widget\",\"description\":\"Sends alert notifications to the Acme platform\"}")))
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Widget createWidget(@Valid @RequestBody CreateWidgetRequest request) {
    String id = "widget-" + UUID.randomUUID().toString().substring(0, 4);
    Widget widget = new Widget(id, request.getName(), request.getDescription());
    widgets.add(widget);
    return widget;
  }
}
