package com.springapp.spring_api.response;

import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ValidationErrorUtil {

  public static Map<String, Object> formatErrors(BindingResult result) {
    List<Map<String, String>> errors = result.getFieldErrors().stream()
        .map(error -> Map.of(
            "field", error.getField(),
            "message", error.getDefaultMessage()
        ))
        .collect(Collectors.toList());

    return Map.of("errors", errors);
}
}
